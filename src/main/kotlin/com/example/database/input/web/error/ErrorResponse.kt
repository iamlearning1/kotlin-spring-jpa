package com.example.database.input.web.error

data class ErrorResponse(
    val status: Int,
    val message: String,
    val timestamp: Long
)
