package com.mango.trip.controller

import com.mango.trip.service.PlanService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/front/v1/plan")
class PlanController(
    private val planService: PlanService,
) {

}