package com.example.database.dao

import com.example.database.entity.Student
import com.example.database.input.web.dto.StudentRequestDto

interface StudentDAO {

    fun save(student: StudentRequestDto)

    fun getById(id: Int): Student?
}
