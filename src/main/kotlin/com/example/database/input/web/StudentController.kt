package com.example.database.input.web

import com.example.database.dao.StudentDAO
import com.example.database.input.web.dto.StudentRequestDto
import com.example.database.input.web.dto.StudentResponseDto
import com.example.database.input.web.dto.StudentUpdateRequestDto
import com.example.database.input.web.error.ErrorResponse
import com.example.database.input.web.error.StudentErrorResponse
import com.example.database.input.web.error.StudentNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/students")
class StudentController(
    private val studentRepository: StudentDAO
) {

    @ExceptionHandler(StudentNotFoundException::class)
    fun handleException(e: StudentNotFoundException): ResponseEntity<StudentErrorResponse> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            StudentErrorResponse(
                status = HttpStatus.NOT_FOUND.value(),
                message = e.message.toString(),
                timestamp = System.currentTimeMillis()
            )
        )

    @ExceptionHandler
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ErrorResponse(
                status = HttpStatus.BAD_REQUEST.value(),
                message = e.message.toString(),
                timestamp = System.currentTimeMillis()
            )
        )

    @GetMapping
    fun findAll(@RequestParam("lastName") lastName: String?): List<StudentResponseDto> {
        val students = if (lastName != null)
            studentRepository.findByLastName(lastName)
        else
            studentRepository.findAll()

        return students.map { it.toStudentResponseDto() }
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: UUID): StudentResponseDto? {
        val student = studentRepository.getById(id)
            ?: throw StudentNotFoundException("Student with id $id not found")
        return student.toStudentResponseDto()
    }

    @PostMapping
    fun createStudent(@RequestBody student: StudentRequestDto) {
        studentRepository.save(student)
    }

    @PatchMapping
    fun updateStudent(@RequestBody student: StudentUpdateRequestDto): StudentResponseDto {
        return studentRepository.update(student).toStudentResponseDto()
    }

    @DeleteMapping("/{id}")
    fun deleteStudent(@PathVariable id: Int) {
        return studentRepository.delete(id)
    }
}
