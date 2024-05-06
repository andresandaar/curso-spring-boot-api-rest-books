package com.company.books.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails andres = User.builder()
                .username("andres_david")
                .password("{noop}andres_david_123")
                .roles("Empleado")
                .build();

        UserDetails mari = User.builder()
                .username("mari_123")
                .password("{noop}mari_123")
                .roles("Empleado", "Jefe")
                .build();

        UserDetails juan = User.builder()
                .username("juan_123")
                .password("{noop}juan_123")
                .roles("Empleado")
                .build();

        return new InMemoryUserDetailsManager(andres, mari, juan);

    }

    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests((configure) -> {
            configure
            .requestMatchers(HttpMethod.GET, "/v1/libros").hasRole("Empleado")
            .requestMatchers(HttpMethod.GET, "/v1/libros/**").hasRole("Empleado")
            .requestMatchers(HttpMethod.POST, "/v1/libros/**").hasRole("Jefe")
            .requestMatchers(HttpMethod.PUT, "/v1/libros/**").hasRole("Jefe")
            .requestMatchers(HttpMethod.DELETE, "/v1/libros/**").hasRole("Jefe");
        });

        http.httpBasic(Customizer.withDefaults());
        http.csrf((crsf)->crsf.disable());
        return http.build();

    }
}
