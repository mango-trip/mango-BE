package com.mango.trip.exception

import org.springframework.http.HttpStatus

open class DefaultException : RuntimeException {
    open val status = HttpStatus.INTERNAL_SERVER_ERROR

    constructor(message: String = "Unknown exception") : super(message)
}