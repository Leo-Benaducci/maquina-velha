package br.com.lbenaducci.maquinavelha.exceptions

import br.com.lbenaducci.maquinavelha.models.dtos.ExceptionResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(exception: NotFoundException, servlet: HttpServletRequest): ExceptionResponse {
        return ExceptionResponse(exception.message ?: "Not found", servlet.requestURI)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentException(exception: IllegalArgumentException, servlet: HttpServletRequest): ExceptionResponse {
        return ExceptionResponse(exception.message ?: "Illegal argument", servlet.requestURI)
    }

    @ExceptionHandler(BlockedException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleBotException(exception: BotException, servlet: HttpServletRequest): ExceptionResponse {
        return ExceptionResponse(exception.message ?: "Blocked", servlet.requestURI)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(exception: Exception, servlet: HttpServletRequest): ExceptionResponse {
        return ExceptionResponse(exception.message ?: "Internal server error", servlet.requestURI)
    }
}