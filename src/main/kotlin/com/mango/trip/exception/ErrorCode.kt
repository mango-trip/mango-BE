package com.mango.trip.exception

enum class ErrorCode(val description: String) {
    // Common
    DEFAULT_EXCEPTION("정의되지 않은 에러"),
    BAD_REQUEST("잘못된 요청"),
    NOT_FOUND("찾을 수 없는 요청"),
    FORBIDDEN("인증 오류"),

    // BAD_REQUEST

    // Forbidden

    // 500!!

}