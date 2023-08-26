package br.com.lbenaducci.maquinavelha.models.entities

import br.com.lbenaducci.maquinavelha.models.enums.Piece
import br.com.lbenaducci.maquinavelha.models.enums.Position
import java.util.*

class Move(
    val player: Player,
    val position: Position,
    val piece: Piece,
    id: UUID = UUID.randomUUID(),
) : AbstractEntity(id)
