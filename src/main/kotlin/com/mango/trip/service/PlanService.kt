package com.mango.trip.service

import com.mango.trip.repository.PlanRepository
import org.springframework.stereotype.Service

@Service
class PlanService(
    private val planRepository: PlanRepository,
) {
}