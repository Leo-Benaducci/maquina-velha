package br.com.lbenaducci.maquinavelha.models.entities

import br.com.lbenaducci.maquinavelha.models.enums.Player
import br.com.lbenaducci.maquinavelha.models.enums.Result
import jakarta.persistence.*
import java.util.*

@Entity
class Session(
        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        val board: Board = Board(),
        @Enumerated(value = EnumType.STRING)
        var result: Result = Result.NONE,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        var history: MutableList<Move> = mutableListOf(),
        @ElementCollection(fetch = FetchType.EAGER)
        var players: MutableSet<Player> = mutableSetOf(),
        var ready: Boolean = false,
        id: UUID = UUID.randomUUID()
) : AbstractEntity(id)
