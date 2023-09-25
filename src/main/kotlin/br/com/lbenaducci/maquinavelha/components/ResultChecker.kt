package br.com.lbenaducci.maquinavelha.components

import br.com.lbenaducci.maquinavelha.models.entities.Board
import br.com.lbenaducci.maquinavelha.models.enums.Piece
import br.com.lbenaducci.maquinavelha.models.enums.Position
import br.com.lbenaducci.maquinavelha.models.enums.Result
import org.springframework.stereotype.Component

@Component
class ResultChecker {
    fun checkResult(board: Board): Result {
        for (i in 0 until 3) {
            checkRow(board, i)?.let { return it }
            checkColumn(board, i)?.let { return it }
        }
        return checkDiagonal(board) ?: checkDraw(board) ?: Result.NONE
    }

    private fun checkRow(board: Board, row: Int): Result? {
        val rowPositions = board.positions.filter { it.key.row == row }.toList()
        return checkLine(rowPositions)
    }

    private fun checkColumn(board: Board, column: Int): Result? {
        val columnPositions = board.positions.filter { it.key.column == column }.toList()
        return checkLine(columnPositions)
    }

    private fun checkLine(positions: List<Pair<Position, Piece>>): Result? {
        val allOrange = positions.all { it.second == Piece.ORANGE }
        val allBlack = positions.all { it.second == Piece.BLACK }

        if (allOrange) {
            return Result.ORANGE_WIN
        }
        if (allBlack) {
            return Result.BLACK_WIN
        }
        return null
    }

    private fun checkDiagonal(board: Board): Result? {
        val positions = board.positions
        val diagonals = listOf(
            listOf(Position.A1, Position.B2, Position.C3),
            listOf(Position.A3, Position.B2, Position.C1)
        )

        for (diagonal in diagonals) {
            val allOrange = diagonal.all { positions[it] == Piece.ORANGE }
            val allBlack = diagonal.all { positions[it] == Piece.BLACK }

            if (allOrange) {
                return Result.ORANGE_WIN
            }
            if (allBlack) {
                return Result.BLACK_WIN
            }
        }
        return null
    }

    private fun checkDraw(board: Board): Result? {
        if (board.positions.all { it.value != Piece.NONE }) {
            return Result.DRAW
        }
        return null
    }

}