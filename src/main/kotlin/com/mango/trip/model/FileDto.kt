package com.mango.trip.model

import com.mango.trip.entity.File

class FileDto(
    val id: Long,
    val fileName: String,
    val saveUrl: String,
) {
    constructor(file: File): this(
        id = file.id!!,
        fileName = file.fileName,
        saveUrl = file.saveUrl,
    )
}