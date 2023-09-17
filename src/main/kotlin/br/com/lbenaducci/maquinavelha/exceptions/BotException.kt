package br.com.lbenaducci.maquinavelha.exceptions

class BotException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)