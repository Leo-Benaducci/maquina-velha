package br.com.lbenaducci.maquinavelha.exceptions

class BlockedException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)