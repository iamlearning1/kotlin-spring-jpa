package com.example.database.input.web.error

import kotlin.RuntimeException

class StudentNotFoundException(s: String) : RuntimeException("Student not found")
