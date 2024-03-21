package com.mango.trip.controller

import com.mango.trip.model.SearchCountryResponse
import com.mango.trip.model.wrapper.CursorResponse
import com.mango.trip.service.CountryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/front/api/v1/country")
class CountryController(
    private val service: CountryService,
) {
    @GetMapping("")
    fun searchCountry(
        @RequestParam keyword: String? = null,
    ): ResponseEntity<CursorResponse<SearchCountryResponse>> {
        return ResponseEntity.ok(service.searchCountry(keyword))
    }
}
