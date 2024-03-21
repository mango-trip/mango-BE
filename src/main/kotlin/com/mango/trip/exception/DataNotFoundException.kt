package com.mango.trip.exception

open class DataNotFoundException(
    error: ErrorCode,
    description: String,
    throwable: Throwable = Throwable()
): DefaultException(error, description, throwable) {

    constructor(error: ErrorCode) : this(error, error.description)

    constructor(description: String) : this(ErrorCode.NOT_FOUND, description)
    constructor() : this(ErrorCode.NOT_FOUND, ErrorCode.NOT_FOUND.description)
}