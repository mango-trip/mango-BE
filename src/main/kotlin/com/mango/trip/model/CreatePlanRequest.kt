package com.mango.trip.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.NotEmpty
import java.time.LocalDateTime

class CreatePlanRequest(
    // 논의: 제목 몇글자 제한?
    val title: String,
    val cityIdList: List<Long>,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    val tripStartDate: LocalDateTime,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    val tripEndDate: LocalDateTime,
    val totalAmount: Int,
    @NotEmpty(message = "일정 파일은 필수값입니다.")
    val fileIdList: List<Long>,
    val content: String?,
)