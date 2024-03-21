package com.mango.trip.controller

import com.mango.trip.model.CreatePlanRequest
import com.mango.trip.model.GetPlanResponse
import com.mango.trip.security.dto.MemberInfoDto
import com.mango.trip.service.PlanService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/front/api/v1/plan")
class PlanController(
    private val planService: PlanService,
) {
    @GetMapping("/{planId}")
    fun getPlan(
        @PathVariable planId: Long
    ): ResponseEntity<GetPlanResponse> {
        val response = planService.getPlan(planId)
        return ResponseEntity.ok(response)
    }

    @PostMapping("")
    fun createPlan(
        @Valid @RequestBody request: CreatePlanRequest,
        @AuthenticationPrincipal memberInfoDto: MemberInfoDto,
    ): ResponseEntity<Any> {
        planService.createPlan(request, memberInfoDto)
        return ResponseEntity(HttpStatus.OK)
    }
}