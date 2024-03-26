package com.mango.trip.service

import com.mango.trip.model.SearchCityDto
import com.mango.trip.model.SearchCityResponse
import com.mango.trip.model.wrapper.CursorResponse
import com.mango.trip.service.dao.CityDao
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CityService(
    private val cityDao: CityDao,
) {
    @Transactional(readOnly = true)
    fun searchCity(searchCityDto: SearchCityDto): CursorResponse<SearchCityResponse> {
        val result = cityDao.search(searchCityDto)
        return CursorResponse(result.map(::SearchCityResponse))
    }
}