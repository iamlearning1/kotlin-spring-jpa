package com.example.database.dao

import com.example.database.entity.Student
import com.example.database.input.web.dto.StudentRequestDto
import com.example.database.input.web.dto.StudentUpdateRequestDto
import java.util.UUID

interface StudentDAO {

    fun save(student: StudentRequestDto)

    fun getById(id: UUID): Student?

    fun findAll(): List<Student>

    fun findByLastName(lastName: String): List<Student>

    fun update(student: StudentUpdateRequestDto): Student

    fun delete(id: UUID)
}
