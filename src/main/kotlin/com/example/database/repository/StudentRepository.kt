package com.example.database.repository

import com.example.database.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.stereotype.Repository
import java.util.UUID

@EnableJpaRepositories
@Repository
interface StudentRepository : JpaRepository<Student, UUID> {
    fun findAllByLastName(lastName: String): List<Student>
}
