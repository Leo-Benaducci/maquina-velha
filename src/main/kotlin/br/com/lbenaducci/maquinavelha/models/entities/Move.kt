package br.com.lbenaducci.maquinavelha.models.entities

import br.com.lbenaducci.maquinavelha.models.enums.Piece
import br.com.lbenaducci.maquinavelha.models.enums.Player
import br.com.lbenaducci.maquinavelha.models.enums.Position
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.util.*

@Entity
class Move(
    @Enumerated(value = EnumType.STRING)
    val player: Player,
    @Enumerated(value = EnumType.STRING)
    val position: Position,
    @Enumerated(value = EnumType.STRING)
    val piece: Piece,
    val inverted: Boolean = false,
    id: UUID = UUID.randomUUID(),
) : AbstractEntity(id) {
    fun copy(inverted: Boolean): Move {
        return Move(player, position, piece, inverted)
    }
}
