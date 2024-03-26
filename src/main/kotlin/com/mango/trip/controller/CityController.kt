package com.mango.trip.controller

import com.mango.trip.model.SearchCityDto
import com.mango.trip.model.SearchCityResponse
import com.mango.trip.model.wrapper.CursorResponse
import com.mango.trip.service.CityService
import jakarta.validation.constraints.Max
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/front/api/v1/city")
class CityController(
    private val service: CityService
) {
    @GetMapping("")
    fun searchCity(
        @RequestParam keyword: String?,
        @RequestParam(required = false) cursor : String?,
        @RequestParam(required = false, defaultValue = "20") @Max(value = 100) size : Int,
    ): ResponseEntity<CursorResponse<SearchCityResponse>> {
        val searchCityDto = SearchCityDto(
            keyword = keyword,
            cursor = cursor,
            pageable = PageRequest.of(0, size)
        )
        return ResponseEntity.ok(service.searchCity(searchCityDto))
    }
}