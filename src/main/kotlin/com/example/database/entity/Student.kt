package com.example.database.entity

import com.example.database.input.web.dto.StudentResponseDto
import jakarta.persistence.*

@Entity
@Table(name = "student")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var firstName: String?,
    var lastName: String?,
    var email: String?,
) {
    fun toStudentResponseDto(): StudentResponseDto =
        StudentResponseDto(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email
        )
}
