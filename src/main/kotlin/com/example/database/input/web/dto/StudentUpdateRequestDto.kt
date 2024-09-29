package com.example.database.input.web.dto

data class StudentUpdateRequestDto(
    val id: Long,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
)
