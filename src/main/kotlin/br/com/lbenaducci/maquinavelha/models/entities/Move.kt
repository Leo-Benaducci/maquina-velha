package br.com.lbenaducci.maquinavelha.models.entities

import br.com.lbenaducci.maquinavelha.models.enums.Piece
import br.com.lbenaducci.maquinavelha.models.enums.Player
import br.com.lbenaducci.maquinavelha.models.enums.Position
import java.util.*

data class Move(
    val player: Player,
    val position: Position,
    val piece: Piece,
    val inverted: Boolean = false,
    override val id: UUID = UUID.randomUUID(),
) : AbstractEntity(id)
