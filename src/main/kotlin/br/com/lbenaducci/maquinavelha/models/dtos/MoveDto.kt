package br.com.lbenaducci.maquinavelha.models.dtos

import br.com.lbenaducci.maquinavelha.models.entities.Move
import br.com.lbenaducci.maquinavelha.models.enums.Player
import java.util.*

data class MoveDto(
    val id: UUID,
    val player: Player,
    val position: String,
    val piece: String,
    val inverted: Boolean
) {
    constructor(move: Move, pieceCount: Int) : this(
        id = move.id,
        player = move.player,
        position = move.position.alias,
        piece = "P${pieceCount - 1}",
        inverted = move.inverted
    )
}
