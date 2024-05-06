package com.company.books.backend.model.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.company.books.backend.model.entity.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria,Long> {

	Optional<Categoria> save(Optional<Categoria> categoria);

}
