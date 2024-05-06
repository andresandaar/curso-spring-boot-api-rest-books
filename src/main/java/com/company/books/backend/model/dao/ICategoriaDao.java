package com.company.books.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.company.books.backend.model.entity.Categoria;

public interface ICategoriaDao extends CrudRepository<Categoria,Long> {

}
