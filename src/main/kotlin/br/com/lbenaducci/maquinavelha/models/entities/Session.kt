package br.com.lbenaducci.maquinavelha.models.entities

import br.com.lbenaducci.maquinavelha.models.enums.Result
import java.util.*

class Session(
    val board: Board = Board(),
    var result: Result = Result.NONE,
    var history: MutableList<Move> = mutableListOf(),
    id: UUID = UUID.randomUUID()
) : AbstractEntity(id)
