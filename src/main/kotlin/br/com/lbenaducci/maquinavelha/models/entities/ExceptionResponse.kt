package br.com.lbenaducci.maquinavelha.models.entities

import java.time.LocalDateTime

data class ExceptionResponse(
    val message: String,
    val path: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)