package com.mango.trip.model

import com.mango.trip.entity.Country
import com.mango.trip.model.wrapper.CursorInterface

class SearchCountryResponse(
    val id: Long,
    val name: String,
): CursorInterface {

    constructor(country: Country): this(
        id = country.id!!,
        name = country.name
    )

    override fun getCursor(): String {
        return this.id.toString()
    }
}