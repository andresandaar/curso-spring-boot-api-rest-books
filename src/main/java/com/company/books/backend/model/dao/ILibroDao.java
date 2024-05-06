package com.company.books.backend.model.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.company.books.backend.model.entity.Libro;

public interface ILibroDao extends CrudRepository<Libro,Long>{
    Optional<Libro> save(Optional<Libro> libro);

}
