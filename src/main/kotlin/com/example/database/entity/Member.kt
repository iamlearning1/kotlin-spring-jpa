package com.example.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "members")
data class Member(
    @Id
    val userId: String,
    val pw: String,
    val active: Int,
    @OneToMany(
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val roles: Set<Role> = emptySet(),
)
