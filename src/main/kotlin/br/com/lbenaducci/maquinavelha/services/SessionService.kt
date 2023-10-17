package br.com.lbenaducci.maquinavelha.services

import br.com.lbenaducci.maquinavelha.components.MoveQueue
import br.com.lbenaducci.maquinavelha.components.MoveValidator
import br.com.lbenaducci.maquinavelha.components.ResultChecker
import br.com.lbenaducci.maquinavelha.exceptions.BlockedException
import br.com.lbenaducci.maquinavelha.exceptions.BotException
import br.com.lbenaducci.maquinavelha.exceptions.NotFoundException
import br.com.lbenaducci.maquinavelha.models.entities.Move
import br.com.lbenaducci.maquinavelha.models.entities.Session
import br.com.lbenaducci.maquinavelha.models.enums.Result
import br.com.lbenaducci.maquinavelha.repositories.SessionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SessionService(
    private val repository: SessionRepository,
    private val moveQueue: MoveQueue,
    private val resultChecker: ResultChecker,
    private val moveValidator: MoveValidator
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
        moveBot(move)
        updateSession(session, move)

        session.result = resultChecker.checkResult(session.board)
        return repository.save(session)
    }

    fun finish(sessionId: UUID): Session {
        val session = findById(sessionId)
        session.result = Result.FINISHED
        moveQueue.clear()
//        session.history.forEach { moveBot(it.copy(inverted = true)) }
        return repository.save(session)
    }

    private fun updateSession(session: Session, move: Move) {
        session.history.add(move)
        session.board.positions[move.position] = move.piece
    }

    private fun moveBot(move: Move) {
        moveQueue.add(move)
        for (i in 0 until 10) {
            if (moveQueue.isExecuted(move)) {
                return
            }
            Thread.sleep(1000)
        }
        moveQueue.remove(move.id)
        throw BotException("Bot move failed")
    }

}
