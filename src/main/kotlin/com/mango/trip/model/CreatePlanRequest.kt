package com.mango.trip.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class CreatePlanRequest(
    val title: String,
    val cityIdList: List<Long>,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    val tripStartDate: LocalDateTime,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    val tripEndDate: LocalDateTime,
    val totalAmount: Int,
    val fileIdList: List<Long>,
    val content: String?,
)