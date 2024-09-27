package com.example.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "student")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    var firstName: String?,
    var lastName: String?,
    var email: String?,
)
