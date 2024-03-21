package com.mango.trip.model.wrapper

import com.fasterxml.jackson.annotation.JsonIgnore

interface CursorInterface {

    @JsonIgnore
    fun getCursor(): String
}