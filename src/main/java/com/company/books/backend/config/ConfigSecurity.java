package com.company.books.backend.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.company.books.backend.filter.JwtReqFilter;
@Configuration
public class ConfigSecurity {
    @Autowired
    @Lazy
    private JwtReqFilter jwtReqFilter;

    /*
     * @Bean
     * public InMemoryUserDetailsManager userDetailsManager() {
     * UserDetails alfredo = User.builder()
     * .username("alfredo")
     * .password("{noop}alfredo123")
     * .roles("Empleado")
     * .build();
     * 
     * UserDetails agustin = User.builder()
     * .username("agustin")
     * .password("{noop}agustin123")
     * .roles("Empleado","Jefe")
     * .build();
     * 
     * UserDetails edita = User.builder()
     * .username("edita123")
     * .password("{noop}edita123")
     * .roles("Empleado","Jefe")
     * .build();
     * 
     * return new InMemoryUserDetailsManager(alfredo,agustin,edita);
     * 
     * }
     */

    /*
     * //Deprecado
     * 
     * @Bean
     * public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     * 
     * http.authorizeRequests((authorizeHttpRequests) -> authorizeHttpRequests
     * .requestMatchers(HttpMethod.GET, "/v1/libros").hasRole("Empleado")
     * .requestMatchers(HttpMethod.GET, "/v1/libros/**").hasRole("Empleado")
     * .requestMatchers(HttpMethod.POST, "/v1/libros/**").hasRole("Jefe")
     * .requestMatchers(HttpMethod.PUT, "/v1/libros/**").hasRole("Jefe")
     * .requestMatchers(HttpMethod.DELETE, "/v1/libros/**").hasRole("Jefe"));
     * 
     * http.httpBasic(Customizer.withDefaults());
     * http.csrf((crsf) -> crsf.disable());
     * return http.build();
     * 
     * };
     */

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);

    };

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // https://www.bezkoder.com/websecurityconfigureradapter-deprecated-spring-boot/

        http.authorizeHttpRequests(
                (requests) -> {
                    requests.requestMatchers(HttpMethod.GET, "/v1/libros").hasRole("Empleado")
                            .requestMatchers(HttpMethod.GET, "/v1/libros/**").hasRole("Empleado")
                            .requestMatchers(HttpMethod.POST, "/v1/libros/**").hasRole("Jefe")
                            .requestMatchers(HttpMethod.PUT, "/v1/libros/**").hasRole("Jefe")
                            .requestMatchers(HttpMethod.DELETE, "/v1/libros/**").hasRole("Jefe")
                            .requestMatchers(HttpMethod.GET, "/v1/categorias").hasRole("Empleado")
                            .requestMatchers(HttpMethod.GET, "/v1/categorias/**").hasRole("Empleado")
                            .requestMatchers(HttpMethod.POST, "/v1/categorias/**").hasRole("Jefe")
                            .requestMatchers(HttpMethod.PUT, "/v1/categorias/**").hasRole("Jefe")
                            .requestMatchers(HttpMethod.DELETE, "/v1/categorias/**").hasRole("Jefe")
                            .requestMatchers("/v1/authenticate", "/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html")
                            .permitAll().anyRequest().authenticated();
                            
                })
                .addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();

    };
/*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/v1/**") // Mapea esta configuración solo para los endpoints en /v1/**
                        .allowedOrigins("http://localhost:4200") // Permitir solicitudes solo desde localhost:4200
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir los métodos GET, POST, PUT, DELETE
                        .allowedHeaders("*"); // Permitir todos los encabezados
            }
        };
    };
*/
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Permitir acceso a cualquier endpoint
                        .allowedOrigins("*") // Permitir solicitudes desde cualquier origen
                        .allowedMethods("*") // Permitir cualquier método (GET, POST, PUT, DELETE, etc.)
                        .allowedHeaders("*"); // Permitir todos los encabezados
            }
        };
    }


}
