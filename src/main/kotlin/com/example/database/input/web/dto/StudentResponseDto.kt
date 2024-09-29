package com.example.database.input.web.dto

import java.util.UUID

data class StudentResponseDto(
    val id: UUID,
    var firstName: String?,
    var lastName: String?,
    var email: String?,
)
