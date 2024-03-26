package com.mango.trip.model

import com.mango.trip.entity.City
import com.mango.trip.model.wrapper.CursorInterface

class SearchCityResponse(
    val cityId: Long,
    val cityName: String,
    val countryName: String
): CursorInterface {

    constructor(city: City): this(
        cityId = city.id!!,
        cityName = city.name,
        countryName = city.country.name,
    )

    override fun getCursor(): String {
        return this.cityId.toString()
    }
}