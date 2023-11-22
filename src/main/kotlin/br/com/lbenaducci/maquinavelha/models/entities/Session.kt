package br.com.lbenaducci.maquinavelha.models.entities

import br.com.lbenaducci.maquinavelha.models.enums.Player
import br.com.lbenaducci.maquinavelha.models.enums.Result
import jakarta.persistence.*
import java.util.*

@Entity
class Session(
    @OneToOne(cascade = [CascadeType.ALL])
    val board: Board = Board(),
    @Enumerated(value = EnumType.STRING)
    var result: Result = Result.NONE,
    @OneToMany(cascade = [CascadeType.ALL])
    var history: MutableList<Move> = mutableListOf(),
    @ElementCollection(fetch = FetchType.EAGER)
    var players: MutableList<Player> = mutableListOf(),
    id: UUID = UUID.randomUUID()
) : AbstractEntity(id)
