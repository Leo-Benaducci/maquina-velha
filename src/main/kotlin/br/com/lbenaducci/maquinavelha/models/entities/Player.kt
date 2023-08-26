package br.com.lbenaducci.maquinavelha.models.entities

import java.util.*

data class Player(
    val name: String? = null,
    val id: UUID = UUID.randomUUID()
)
