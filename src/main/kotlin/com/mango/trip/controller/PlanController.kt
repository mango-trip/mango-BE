package com.mango.trip.controller

import com.mango.trip.model.GetPlanResponse
import com.mango.trip.service.PlanService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/front/v1/plan")
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
}