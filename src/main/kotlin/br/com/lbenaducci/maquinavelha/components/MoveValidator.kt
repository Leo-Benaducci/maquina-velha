package br.com.lbenaducci.maquinavelha.components

import br.com.lbenaducci.maquinavelha.models.entities.Move
import br.com.lbenaducci.maquinavelha.models.entities.Session
import br.com.lbenaducci.maquinavelha.models.enums.Piece
import br.com.lbenaducci.maquinavelha.models.enums.Player
import br.com.lbenaducci.maquinavelha.models.enums.Position
import br.com.lbenaducci.maquinavelha.models.enums.Result
import org.springframework.stereotype.Component

@Component
class MoveValidator {
    fun validateMove(move: Move, session: Session) {
        require(move.piece != Piece.NONE) { "Invalid piece" }

        validateSessionFinished(session)
        validatePosition(session, move.position)
        validateLastMove(session, move.player, move.piece)
    }

    private fun validateSessionFinished(session: Session) {
        require(session.result == Result.NONE) { "Session ${session.id} finished" }
    }

    private fun validatePosition(session: Session, position: Position) {
        require(session.board.positions[position] == Piece.NONE) { "Position $position already occupied" }
    }

    private fun validateLastMove(session: Session, player: Player, piece: Piece) {
        if (session.history.isEmpty()) {
            return
        }
        val lastMove = session.history.last()
        require(lastMove.piece != piece) { "Invalid piece move" }
        require(lastMove.player != player) { "Invalid player move" }
    }
}