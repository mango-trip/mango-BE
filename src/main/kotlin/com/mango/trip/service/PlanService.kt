package com.mango.trip.service

import com.mango.trip.entity.Plan
import com.mango.trip.model.CreatePlanRequest
import com.mango.trip.model.GetPlanResponse
import com.mango.trip.service.dao.PlanDao
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlanService(
    private val planDao: PlanDao,
) {
    @Transactional(readOnly = true)
    fun getPlan(planId: Long): GetPlanResponse {
        val plan = planDao.getById(planId)
        return GetPlanResponse(plan)
    }

    @Transactional
    fun createPlan(request: CreatePlanRequest) {
        // TODO 도시 검증
        planDao.create(request)
    }
}