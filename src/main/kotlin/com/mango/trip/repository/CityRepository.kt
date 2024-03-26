package com.mango.trip.repository

import com.mango.trip.entity.City
import com.mango.trip.repository.custom.CustomCityRepository
import org.springframework.data.jpa.repository.JpaRepository

interface CityRepository : JpaRepository<City, Long>, CustomCityRepository {
}