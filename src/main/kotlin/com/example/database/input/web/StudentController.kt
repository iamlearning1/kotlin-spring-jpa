package com.example.database.input.web

import com.example.database.input.web.dto.StudentRequestDto
import com.example.database.input.web.dto.StudentResponseDto
import com.example.database.input.web.dto.StudentUpdateRequestDto
import com.example.database.input.web.error.StudentNotFoundException
import com.example.database.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/students")
class StudentController(
    private val studentService: StudentService
) {

    @GetMapping
    fun findAll(@RequestParam("lastName") lastName: String?): List<StudentResponseDto> {
        val students = if (lastName != null)
            studentService.findByLastName(lastName)
        else
            studentService.findAll()

        return students.map { it.toStudentResponseDto() }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: UUID): ResponseEntity<StudentResponseDto> {
        val student = studentService.findById(id)?.toStudentResponseDto()
            ?: throw StudentNotFoundException("Student with id $id not found")
        return ResponseEntity.status(HttpStatus.OK).body(student)
    }

    @PostMapping
    fun createStudent(@RequestBody student: StudentRequestDto) {
        studentService.save(student)
        ResponseEntity.status(HttpStatus.CREATED)
    }

    @PatchMapping
    fun updateStudent(@RequestBody student: StudentUpdateRequestDto): ResponseEntity<StudentResponseDto> {
        val result = studentService.update(student)?.toStudentResponseDto() ?: throw StudentNotFoundException("Student with id ${student.id} not found")
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: UUID) {
        return studentService.delete(id)
    }
}
