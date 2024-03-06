package com.mango.trip.exception

import org.springframework.http.HttpStatus

open class DataNotFoundException : RuntimeException {
    open val status = HttpStatus.NOT_FOUND

    constructor(message: String = "Not Found") : super(message)
}