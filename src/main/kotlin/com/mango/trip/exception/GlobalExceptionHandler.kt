package com.mango.trip.exception

import com.mango.trip.model.wrapper.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = KotlinLogging.logger { }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleUnwoundException(request: HttpServletRequest, exception: Exception): ErrorResponse {
        log.error(exception.stackTraceToString())
        return ErrorResponse(ErrorCode.DEFAULT_EXCEPTION, exception.message ?: "INTERNAL_SERVER_ERROR")
    }

    @ExceptionHandler(DataNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleBadRequest(exception: DataNotFoundException): ErrorResponse {
        return ErrorResponse(exception.error, exception.description)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ErrorResponse {
        return ErrorResponse(ErrorCode.BAD_REQUEST, exception.message)
    }
}