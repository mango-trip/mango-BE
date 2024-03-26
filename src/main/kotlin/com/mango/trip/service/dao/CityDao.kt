package com.mango.trip.service.dao

import com.mango.trip.entity.City
import com.mango.trip.model.SearchCityDto
import com.mango.trip.repository.CityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CityDao(
    private val repository: CityRepository,
) {
    @Transactional(readOnly = true)
    fun search(searchCityDto: SearchCityDto): List<City> {
        return repository.search(searchCityDto)
    }
}