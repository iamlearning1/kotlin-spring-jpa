package com.example.database.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        val users: User.UserBuilder = User.withDefaultPasswordEncoder()
        val manager = InMemoryUserDetailsManager()

        manager.createUser(users.username("user").password("password").roles("STUDENT").build())
        manager.createUser(users.username("admin").password("password").roles("STUDENT","TEACHER").build())

        return manager
    }

    @Order(1)
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
         http {
             securityMatcher("api/students")
//             securityMatcher("api/students/**")
             authorizeRequests {
                 authorize(anyRequest, hasRole("TEACHER"))
             }
             httpBasic { }
         }
        return http.build()
//        return http
//            .authorizeHttpRequests {
//                it
//                    .requestMatchers(HttpMethod.GET, "/api/students").hasRole("STUDENT")
//                    .requestMatchers(HttpMethod.GET, "/api/students/**").hasRole("STUDENT")
//                    .requestMatchers(HttpMethod.POST, "/api/students").hasRole("TEACHER")
//                    .requestMatchers(HttpMethod.PUT, "/api/students").hasRole("TEACHER")
//                    .requestMatchers(HttpMethod.DELETE, "/api/students/**").hasRole("ADMIN")
//            }
//            .httpBasic {  }
//            .csrf { it.disable() }
//            .build()
    }
}
