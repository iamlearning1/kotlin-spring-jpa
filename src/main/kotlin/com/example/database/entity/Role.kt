package com.example.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "roles")
data class Role(
    @Id
    val userId: String,
    val role: String,
)
