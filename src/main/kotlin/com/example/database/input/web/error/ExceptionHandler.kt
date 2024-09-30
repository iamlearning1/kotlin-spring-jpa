package com.example.database.input.web.error

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(StudentNotFoundException::class)
    fun handleException(e: StudentNotFoundException): ResponseEntity<StudentErrorResponse> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            StudentErrorResponse(
                status = HttpStatus.NOT_FOUND.value(),
                message = e.message.toString(),
                timestamp = System.currentTimeMillis()
            )
        )

    @ExceptionHandler
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = e.message.toString(),
                timestamp = System.currentTimeMillis()
            )
        )
}
