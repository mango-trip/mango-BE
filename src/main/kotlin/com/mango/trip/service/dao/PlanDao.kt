package com.mango.trip.service.dao

import com.mango.trip.entity.Plan
import com.mango.trip.exception.DataNotFoundException
import com.mango.trip.model.CreatePlanRequest
import com.mango.trip.repository.PlanRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlanDao(
    private val repository: PlanRepository,
) {
    @Transactional(readOnly = true)
    fun getById(id: Long): Plan {
        return repository.findByIdOrNull(id)
            ?: throw DataNotFoundException("일정 정보를 찾지 못했습니다 -id:$id")
    }

    @Transactional
    fun create(request: CreatePlanRequest): Plan {
        val plan = Plan(request)
        return repository.save(plan)
    }
}