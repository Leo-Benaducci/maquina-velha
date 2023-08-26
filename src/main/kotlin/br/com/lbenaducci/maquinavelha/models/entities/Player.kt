package br.com.lbenaducci.maquinavelha.models.entities

import java.util.*

class Player(
    val name: String? = null,
    id: UUID = UUID.randomUUID()
) : AbstractEntity(id)
