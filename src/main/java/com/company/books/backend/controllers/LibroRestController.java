package com.company.books.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.response.LibroResponseRest;
import com.company.books.backend.services.ILibroService;

@RestController
@RequestMapping("/v1")
public class LibroRestController {
    
    @Autowired
    private ILibroService service;

    @RequestMapping("/libros")
    public ResponseEntity<LibroResponseRest> getLibros() {
        ResponseEntity<LibroResponseRest> response = service.getLibros();
        return response;
    };
    @RequestMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> getLibroById(@PathVariable Long id) {
        ResponseEntity<LibroResponseRest> response = service.getLibroById(id);
        return response;
    };

}
