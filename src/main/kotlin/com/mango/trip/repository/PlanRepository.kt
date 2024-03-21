package com.mango.trip.repository

import com.mango.trip.entity.Plan
import org.springframework.data.jpa.repository.JpaRepository

interface PlanRepository : JpaRepository<Plan, Long> {
    fun findByIdAndIsActive(id: Long, isActive: Boolean): Plan?
}