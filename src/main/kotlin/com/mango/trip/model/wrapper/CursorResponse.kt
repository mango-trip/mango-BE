package com.mango.trip.model.wrapper

import org.springframework.util.CollectionUtils

data class CursorResponse<T : CursorInterface>(
    val cursor: String?,
    val content: List<T>
) {
    constructor(result: List<T>) : this(
        if (CollectionUtils.isEmpty(result)) null else result[result.lastIndex].getCursor(),
        result
    )
}
