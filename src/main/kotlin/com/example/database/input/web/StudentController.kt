package com.example.database.input.web

import com.example.database.dao.StudentDAO
import com.example.database.input.web.dto.StudentRequestDto
import com.example.database.input.web.dto.StudentResponseDto
import com.example.database.input.web.dto.StudentUpdateRequestDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/students")
class StudentController(
    private val studentRepository: StudentDAO
) {

    @GetMapping
    fun findAll(@RequestParam("lastName") lastName: String?): List<StudentResponseDto> {
        val students = if (lastName != null)
            studentRepository.findByLastName(lastName)
        else
            studentRepository.findAll()

        return students.map { it.toStudentResponseDto() }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Int): StudentResponseDto? {
        val student = studentRepository.getById(id)
        return student?.toStudentResponseDto()
    }

    @PostMapping
    fun createStudent(@RequestBody student: StudentRequestDto) {
        studentRepository.save(student)
    }

    @PatchMapping
    fun updateStudent(@RequestBody student: StudentUpdateRequestDto) {
        studentRepository.update(student)
    }
}
