package br.com.lbenaducci.maquinavelha.services

import br.com.lbenaducci.maquinavelha.components.MoveQueue
import br.com.lbenaducci.maquinavelha.components.MoveValidator
import br.com.lbenaducci.maquinavelha.components.ResultChecker
import br.com.lbenaducci.maquinavelha.configs.properties.AppProperties
import br.com.lbenaducci.maquinavelha.exceptions.BlockedException
import br.com.lbenaducci.maquinavelha.exceptions.NotFoundException
import br.com.lbenaducci.maquinavelha.models.entities.Move
import br.com.lbenaducci.maquinavelha.models.entities.Session
import br.com.lbenaducci.maquinavelha.models.enums.Piece
import br.com.lbenaducci.maquinavelha.models.enums.Result
import br.com.lbenaducci.maquinavelha.repositories.SessionRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.*

@Service
class SessionService(
    private val repository: SessionRepository,
    private val moveQueue: MoveQueue,
    private val resultChecker: ResultChecker,
    private val moveValidator: MoveValidator,
    private val publisher: ApplicationEventPublisher,
    private val properties: AppProperties
) {
    fun create(): Session {
        if (repository.findFirstByResult(Result.NONE) == null) {
            return repository.save(Session())
        }
        throw BlockedException("Existed sessions not finished")
    }

    fun findById(id: UUID): Session {
        return repository.findById(id).orElseThrow { throw NotFoundException("Session $id not found") }
    }

    fun findAll(): List<Session> {
        return repository.findAll()
    }

    fun move(sessionId: UUID, move: Move): Session {
        val session = findById(sessionId)

        moveValidator.validateMove(move, session)
        publisher.publishEvent(move)
        updateSession(session, move)

        session.result = resultChecker.checkResult(session.board)
        return repository.save(session)
    }

    fun finish(sessionId: UUID): Session {
        val session = findById(sessionId)
        session.result = Result.FINISHED
        moveQueue.clear()
        session.history.forEach { moveBot(it.copy(inverted = true)) }
        return repository.save(session)
    }

    private fun updateSession(session: Session, move: Move) {
        session.history.add(move)
        session.board.positions[move.position] = move.piece
    }

    @Async
    @EventListener
    fun moveBot(move: Move) {
        moveQueue.add(move)
        for (i in 0 until properties.tries) {
            if (moveQueue.isExecuted(move)) {
                successMove(move)
            }
            Thread.sleep(properties.millisWait)
        }
        failMove(move)
    }

    private fun failMove(move: Move) {
        moveQueue.remove(move.id)
        val session = repository.findFirstByHistoryContaining(move) ?: throw NotFoundException("Session not found")
        session.history.remove(move)
        session.board.positions[move.position] = Piece.NONE
        if (session.result != Result.FINISHED) {
            session.result = Result.NONE
        }
        repository.save(session)
        //todo send websocket event
    }

    private fun successMove(move: Move) {
        // todo send websocket event
    }

}
