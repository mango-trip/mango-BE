package com.mango.trip.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.mango.trip.entity.Plan
import java.time.LocalDateTime

class GetPlanResponse(
    val id: Long,
    val title: String,
    val planCityList: List<String>,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    val tripStartDate: LocalDateTime,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    val tripEndDate: LocalDateTime,
    val totalAmount: Int,
    val fileList: List<FileDto>,
    val content: String?,
) {
    constructor(plan: Plan): this(
        id = plan.id!!,
        title = plan.title,
        planCityList = plan.planCityList.map{ it.city.name },
        tripStartDate = plan.tripStartDate,
        tripEndDate = plan.tripEndDate,
        totalAmount = plan.totalAmount,
        fileList = plan.fileList.map(::FileDto),
        content = plan.content
    )
}