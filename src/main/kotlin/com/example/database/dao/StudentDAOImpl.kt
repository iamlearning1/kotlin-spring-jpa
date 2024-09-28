package com.example.database.dao

import com.example.database.entity.Student
import com.example.database.input.web.dto.StudentRequestDto
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class StudentDAOImpl(private val entityManager: EntityManager) : StudentDAO {

    @Transactional
    override fun save(student: StudentRequestDto) {
        val record = Student(
            firstName = student.firstName,
            lastName = student.lastName,
            email = student.email
        )
        entityManager.persist(record)
    }

    override fun getById(id: Int): Student? {
        return entityManager.find(Student::class.java, id)
    }
}
