package com.mango.trip.model

import org.springframework.data.domain.Pageable

class SearchCityDto(
    val keyword: String?,
    val cursor: String?,
    val pageable: Pageable,
)