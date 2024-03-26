package com.mango.trip.exception.auth

import com.mango.trip.exception.DefaultException
import com.mango.trip.exception.ErrorCode

class OAuth2RequestException(
    error: ErrorCode,
    description: String,
    throwable: Throwable = Throwable()
) : DefaultException(error, description, throwable) {
    constructor(error: ErrorCode) : this(error, error.description)

    constructor(description: String) : this(ErrorCode.FORBIDDEN, description)
    constructor() : this(ErrorCode.FORBIDDEN, ErrorCode.FORBIDDEN.description)
}