package br.com.lbenaducci.maquinavelha.models.entities

import br.com.lbenaducci.maquinavelha.models.enums.Piece
import br.com.lbenaducci.maquinavelha.models.enums.Position
import java.util.*

data class Board(
    val positions: MutableMap<Position, Piece> = mutableMapOf(
        Position.A1 to Piece.NONE,
        Position.A2 to Piece.NONE,
        Position.A3 to Piece.NONE,
        Position.B1 to Piece.NONE,
        Position.B2 to Piece.NONE,
        Position.B3 to Piece.NONE,
        Position.C1 to Piece.NONE,
        Position.C2 to Piece.NONE,
        Position.C3 to Piece.NONE
    ),
    val id: UUID = UUID.randomUUID()
)