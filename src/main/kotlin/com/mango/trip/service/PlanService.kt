package com.mango.trip.service

import com.mango.trip.model.CreatePlanRequest
import com.mango.trip.model.GetPlanResponse
import com.mango.trip.security.dto.MemberInfoDto
import com.mango.trip.service.dao.MemberDao
import com.mango.trip.service.dao.PlanDao
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlanService(
    private val planDao: PlanDao,
    private val memberDao: MemberDao,
) {
    @Transactional(readOnly = true)
    fun getPlan(planId: Long): GetPlanResponse {
        val plan = planDao.getById(planId)
        return GetPlanResponse(plan)
    }

    @Transactional
    fun createPlan(request: CreatePlanRequest, memberInfoDto: MemberInfoDto) {
        // TODO 도시 검증
        val member = memberDao.getById(memberInfoDto.id)
        planDao.create(request, member)
    }
}