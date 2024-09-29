package com.example.database.entity

import com.example.database.input.web.dto.StudentResponseDto
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "student")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,
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
