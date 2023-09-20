package br.com.lbenaducci.maquinavelha.components

import br.com.lbenaducci.maquinavelha.models.entities.Move
import br.com.lbenaducci.maquinavelha.models.enums.Piece
import org.springframework.stereotype.Component
import java.util.*

@Component
class MoveQueue {
    private val queue = mutableListOf<Move>()

    fun add(move: Move) {
        if(move.piece == Piece.NONE) {
            return
        }
        queue.add(move)
    }

    fun isExecuted(move: Move): Boolean {
        return !queue.contains(move)
    }

    fun get(piece: Piece): Move? {
        val first = queue.first()
        if (first.piece != piece) {
            return null
        }
        return first
    }

    fun remove(moveId: UUID) {
        queue.removeIf { it.id == moveId }
    }

    fun clear() {
        queue.clear()
    }
}