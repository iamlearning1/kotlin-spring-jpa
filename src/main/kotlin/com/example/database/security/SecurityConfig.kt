package com.example.database.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpMethod
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import javax.sql.DataSource

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun userDetailsManager(datasource: DataSource): UserDetailsManager {
        val userDetailsManager = JdbcUserDetailsManager(datasource)

        userDetailsManager.usersByUsernameQuery = "select user_id, pw, active from members where user_id=?"

        userDetailsManager.setAuthoritiesByUsernameQuery(
            "select user_id, role from roles where user_id=?");

        return userDetailsManager
    }

    @Order(1)
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers(HttpMethod.GET, "/api/students").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/api/students/**").hasRole("STUDENT")
                    .requestMatchers(HttpMethod.POST, "/api/students").hasRole("TEACHER")
                    .requestMatchers(HttpMethod.PUT, "/api/students").hasRole("TEACHER")
                    .requestMatchers(HttpMethod.DELETE, "/api/students/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .httpBasic { Customizer.withDefaults<HttpSecurity>() }
            .build()
    }
}
