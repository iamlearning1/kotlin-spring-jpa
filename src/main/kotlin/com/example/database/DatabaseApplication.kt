package com.example.database

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DatabaseApplication

fun main(args: Array<String>) {
	runApplication<DatabaseApplication>(*args)
}

@Bean
fun commandLineRunner(): CommandLineRunner {
    return CommandLineRunner {
        println("Hello world")
    }
}
