package br.com.lbenaducci.maquinavelha.models.entities

import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import java.util.*

@MappedSuperclass
abstract class AbstractEntity(
    @Id
    open val id: UUID
)