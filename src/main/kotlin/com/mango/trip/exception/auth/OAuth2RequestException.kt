package com.mango.trip.exception.auth

import com.mango.trip.exception.DefaultException

class OAuth2RequestException : DefaultException {
    constructor(msg: String) : super(msg)
}