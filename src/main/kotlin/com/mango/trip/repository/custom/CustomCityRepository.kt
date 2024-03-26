package com.mango.trip.repository.custom

import com.mango.trip.entity.City
import com.mango.trip.model.SearchCityDto

interface CustomCityRepository {
    fun search(searchCityDto: SearchCityDto): List<City>
}