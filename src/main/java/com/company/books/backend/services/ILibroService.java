package com.company.books.backend.services;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.entity.Libro;
import com.company.books.backend.response.LibroResponseRest;

public interface ILibroService {
    public ResponseEntity<LibroResponseRest> getLibros();

    public ResponseEntity<LibroResponseRest> getLibroById(long id);

    public ResponseEntity<LibroResponseRest> createLibro(Libro libro);

    public ResponseEntity<LibroResponseRest> updateLibro(Libro libro, long id);

    public ResponseEntity<LibroResponseRest> deleteLibro(long id);
}
