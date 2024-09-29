package com.example.database.dao

import com.example.database.entity.Student
import com.example.database.input.web.dto.StudentRequestDto
import com.example.database.input.web.dto.StudentUpdateRequestDto
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

    override fun findAll(): List<Student> {
        val query = entityManager.createQuery("from Student", Student::class.java)
        return query.resultList
    }

    override fun findByLastName(lastName: String): List<Student> {
        val query = entityManager.createQuery(
            "from Student where lastName = :lastName",
            Student::class.java
        )
        query.setParameter("lastName", lastName)
        return query.resultList
    }

    @Transactional
    override fun update(student: StudentUpdateRequestDto) {
        val newStudent = entityManager.find(Student::class.java, student.id)

        val updatedStudent = newStudent.copy(
            firstName = student.firstName ?: newStudent.firstName,
            lastName = student.lastName ?: newStudent.lastName,
            email = student.email ?: newStudent.email
        )

        entityManager.merge(updatedStudent)
    }
}
