package com.mango.trip.service

import com.mango.trip.entity.Plan
import com.mango.trip.exception.DataNotFoundException
import com.mango.trip.model.CreatePlanRequest
import com.mango.trip.model.GetPlanResponse
import com.mango.trip.repository.PlanRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlanService(
    private val planRepository: PlanRepository,
) {
    @Transactional(readOnly = true)
    fun getPlan(planId: Long): GetPlanResponse {
        val plan = get(planId)
        return GetPlanResponse(plan)
    }

    @Transactional
    fun createPlan(request: CreatePlanRequest) {
        TODO("일정 생성 로직 구현 필요")
    }

    private fun get(planId: Long): Plan {
        return planRepository.findByIdAndIsActive(true)
            ?: throw DataNotFoundException("일정을 찾을 수 없습니다.")
    }
}