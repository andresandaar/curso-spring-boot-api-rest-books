package com.company.books.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.books.backend.model.entity.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.services.ICategoriaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/v1")
public class CategoriaRestController {

    @Autowired
    private ICategoriaService service;

    @RequestMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> consultarCategorias() {
        ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
        return response;

    };

    @RequestMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> consultarPorId(@PathVariable Long id) {
        ResponseEntity<CategoriaResponseRest> response = service.buscarCategoriasPorId(id);
        return response;
    };

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> crearCategorias(@RequestBody Categoria categoria) {
        ResponseEntity<CategoriaResponseRest> response = service.crearCategoria(categoria);
        return response;
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> actualizarCategoria(@PathVariable Long id,
            @RequestBody Categoria categoria) {
        ResponseEntity<CategoriaResponseRest> response = service.actualizarCategoria(categoria, id);
        return response;
    };

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> borrarCategoria(@PathVariable Long id) {
        ResponseEntity<CategoriaResponseRest> response = service.eliminarCategoria(id);
        return response;
    };

}
