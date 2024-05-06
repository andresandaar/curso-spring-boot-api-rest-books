package com.company.books.backend.services;

import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.entity.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;

public interface ICategoriaService {
    public ResponseEntity<CategoriaResponseRest> buscarCategorias();

    public ResponseEntity<CategoriaResponseRest> buscarCategoriasPorId(long id);

    public ResponseEntity<CategoriaResponseRest> crearCategoria(Categoria categoria);

    public ResponseEntity<CategoriaResponseRest> actualizarCategoria(Categoria categoria, long id);
    
    public ResponseEntity<CategoriaResponseRest> eliminarCategoria(long id);

}
