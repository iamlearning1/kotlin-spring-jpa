package com.example.database.service

import com.example.database.entity.Student
import com.example.database.input.web.dto.StudentRequestDto
import com.example.database.repository.StudentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class StudentService(
    private val studentRepository: StudentRepository
) {
    fun findAll(): List<Student> = studentRepository.findAll()

    fun findByLastName(lastName: String): List<Student> = studentRepository.findAllByLastName(lastName)

    fun findById(id: UUID): Student? {
        val student = studentRepository.findById(id)
        if (student.isPresent) return student.get()
        return null
    }

    fun save(student: StudentRequestDto) {
        studentRepository.save(
            Student(
                firstName = student.firstName,
                lastName = student.lastName,
                email = student.email,
            )
        )
    }

//    fun update(student: StudentUpdateRequestDto): Student? {
//        return studentRepository.updateByIdEquals(student.id, Student(
//                id = student.id,
//                firstName = student.firstName,
//                lastName = student.lastName,
//                email = student.email
//            )
//        )
//    }

    fun delete(id: UUID) {
        studentRepository.deleteById(id)
    }
}
