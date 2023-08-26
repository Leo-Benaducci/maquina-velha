package br.com.lbenaducci.maquinavelha.models.entities

import br.com.lbenaducci.maquinavelha.models.enums.Piece
import br.com.lbenaducci.maquinavelha.models.enums.Position
import java.util.UUID

data class Move(
    val player: Player,
    val position: Position,
    val piece: Piece,
    val id: UUID = UUID.randomUUID(),
)
