package br.com.lbenaducci.maquinavelha.services

import br.com.lbenaducci.maquinavelha.exceptions.NotFoundException
import br.com.lbenaducci.maquinavelha.models.entities.Move
import br.com.lbenaducci.maquinavelha.models.entities.Session
import br.com.lbenaducci.maquinavelha.models.enums.Piece
import br.com.lbenaducci.maquinavelha.models.enums.Player
import br.com.lbenaducci.maquinavelha.models.enums.Position
import br.com.lbenaducci.maquinavelha.models.enums.Result
import br.com.lbenaducci.maquinavelha.repositories.SessionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SessionService(
    private val repository: SessionRepository,
) {
    fun create(): Session {
        return repository.save(Session())
    }

    fun findById(id: UUID): Session {
        return repository.findById(id) ?: throw NotFoundException("Session $id not found")
    }

    fun move(sessionId: UUID, player: Player, position: Position, piece: Piece): Session {
        require(piece != Piece.NONE) { "Invalid piece" }

        val session = findById(sessionId)

        validateSessionFinished(session)
        validatePosition(session, position)
        validateLastMove(session, player, piece)

        val move = Move(player, position, piece)
        updateSession(session, move)

        checkResult(session)

        return repository.save(session)
    }

    private fun validateSessionFinished(session: Session) {
        require(session.result == Result.NONE) { "Session ${session.id} finished" }
    }

    private fun validatePosition(session: Session, position: Position) {
        require(session.board.positions[position] == Piece.NONE) { "Position $position already occupied" }
    }

    private fun validateLastMove(session: Session, player: Player, piece: Piece) {
        val lastMove = session.history.last()
        require(lastMove.piece != piece) { "Invalid piece move" }
        require(lastMove.player != player) { "Invalid player move" }
    }

    private fun updateSession(session: Session, move: Move) {
        session.history.add(move)
        session.board.positions[move.position] = move.piece
    }

    private fun checkResult(session: Session) {
        for (i in 0 until 3) {
            checkRow(session, i)
            checkColumn(session, i)
        }
        checkDiagonal(session)
        checkDraw(session)
    }

    private fun checkLine(session: Session, positions: List<Pair<Position, Piece>>) {
        val allRed = positions.all { it.second == Piece.RED }
        val allBlue = positions.all { it.second == Piece.BLUE }

        if (allRed) session.result = Result.RED_WIN
        if (allBlue) session.result = Result.BLUE_WIN
    }

    private fun checkRow(session: Session, row: Int) {
        val rowPositions = session.board.positions.filter { it.key.row == row }.toList()
        checkLine(session, rowPositions)
    }

    private fun checkColumn(session: Session, column: Int) {
        val columnPositions = session.board.positions.filter { it.key.column == column }.toList()
        checkLine(session, columnPositions)
    }

    private fun checkDiagonal(session: Session) {
        val positions = session.board.positions
        val diagonals = listOf(
            listOf(Position.A1, Position.B2, Position.C3),
            listOf(Position.A3, Position.B2, Position.C1)
        )

        for (diagonal in diagonals) {
            val allRed = diagonal.all { positions[it] == Piece.RED }
            val allBlue = diagonal.all { positions[it] == Piece.BLUE }

            if (allRed) session.result = Result.RED_WIN
            if (allBlue) session.result = Result.BLUE_WIN
        }
    }

    private fun checkDraw(session: Session) {
        if (session.history.size == 9) {
            session.result = Result.DRAW
        }
    }
}
