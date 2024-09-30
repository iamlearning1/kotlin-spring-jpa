package com.example.database.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "student")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID = UUID.randomUUID(),
    var firstName: String?,
    var lastName: String?,
    var email: String?,
)
