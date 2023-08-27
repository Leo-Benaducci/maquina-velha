package br.com.lbenaducci.maquinavelha.exceptions

class NotFoundException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)