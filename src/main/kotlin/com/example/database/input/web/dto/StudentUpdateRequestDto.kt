package com.example.database.input.web.dto

import java.util.UUID

data class StudentUpdateRequestDto(
    val id: UUID,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
)
