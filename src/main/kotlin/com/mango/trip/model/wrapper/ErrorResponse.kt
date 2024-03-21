package com.mango.trip.model.wrapper

import com.mango.trip.exception.ErrorCode

data class ErrorResponse(val error: ErrorCode, val description: String) {

}