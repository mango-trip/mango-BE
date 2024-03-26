package com.mango.trip.repository

import com.mango.trip.entity.Country
import org.springframework.data.jpa.repository.JpaRepository

interface CountryRepository : JpaRepository<Country, Long> {
}