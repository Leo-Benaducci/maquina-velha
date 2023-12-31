package br.com.lbenaducci.maquinavelha.components

import br.com.lbenaducci.maquinavelha.models.dtos.MoveDto
import br.com.lbenaducci.maquinavelha.models.entities.Move
import br.com.lbenaducci.maquinavelha.models.enums.Piece
import br.com.lbenaducci.maquinavelha.repositories.SessionRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class MoveQueue(
        private val sessionRepository: SessionRepository
) {
    private val queue = mutableListOf<MoveDto>()
    var currentSessionId: UUID? = null

    fun add(move: Move, pieceCount: Int) {
        if (move.piece == Piece.NONE) {
            return
        }
        queue.add(MoveDto(move, pieceCount))
    }

    fun isExecuted(move: Move): Boolean {
        return !queue.contains(MoveDto(move, 1))
    }

    fun get(): MoveDto? {
        if (queue.isEmpty()) {
            return null
        }
        return queue.first()
    }

    fun readSession(sessionId: UUID) {
        sessionRepository.findById(sessionId).ifPresent {
            it.ready = true
            sessionRepository.save(it)
        }
    }

    fun remove(moveId: UUID) {
        queue.removeIf { it.id == moveId }
    }

    fun clear() {
        queue.clear()
    }
}