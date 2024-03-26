package com.mango.trip.exception

open class DefaultException(
    val error: ErrorCode,
    val description: String,
    throwable: Throwable?
) : RuntimeException(error.name, throwable) {
    constructor() : this(ErrorCode.DEFAULT_EXCEPTION, ErrorCode.DEFAULT_EXCEPTION.description, null)
    constructor(error: ErrorCode) : this(error, error.description, null)
    constructor(error: ErrorCode, description: String) : this(error, description, null)
}