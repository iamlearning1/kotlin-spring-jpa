package com.example.database.service

import com.example.database.dao.StudentDAO
import com.example.database.entity.Student
import com.example.database.input.web.dto.StudentRequestDto
import com.example.database.input.web.dto.StudentUpdateRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class StudentService(
    private val studentRepository: StudentDAO
) {
    fun findAll(): List<Student> = studentRepository.findAll()

    fun findByLastName(lastName: String): List<Student> = studentRepository.findByLastName(lastName)

    fun findById(id: UUID): Student? {
        return studentRepository.getById(id)
    }

    @Transactional
    fun save(student: StudentRequestDto) {
        studentRepository.save(student)
    }

    @Transactional
    fun update(student: StudentUpdateRequestDto): Student? {
        return studentRepository.update(student)
    }

    @Transactional
    fun delete(id: UUID) {
        studentRepository.delete(id)
    }
}
