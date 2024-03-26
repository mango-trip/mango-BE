package com.mango.trip.service

import com.mango.trip.model.SearchCountryResponse
import com.mango.trip.model.wrapper.CursorResponse
import com.mango.trip.repository.CountryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CountryService(
    private val repository: CountryRepository,
) {
    @Transactional(readOnly = true)
    fun searchCountry(keyword: String?): CursorResponse<SearchCountryResponse> {
        // TODO("keyword search query dsl")
        val countryList = repository.findAll()
        return CursorResponse(
            countryList.map(::SearchCountryResponse)
        )
    }
}