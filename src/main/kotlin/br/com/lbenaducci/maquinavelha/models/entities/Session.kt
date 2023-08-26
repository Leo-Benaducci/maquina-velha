package br.com.lbenaducci.maquinavelha.models.entities

import br.com.lbenaducci.maquinavelha.models.enums.Result
import java.util.*

data class Session(
    val player1: Player = Player(),
    val player2: Player = Player(),
    val board: Board = Board(),
    var result: Result = Result.NONE,
    var history: MutableList<Move> = mutableListOf(),
    val id: UUID = UUID.randomUUID()
)
