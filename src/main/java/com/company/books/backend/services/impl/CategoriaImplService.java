package com.company.books.backend.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.model.entity.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.services.ICategoriaService;

@Service
public class CategoriaImplService implements ICategoriaService {
    private static final Logger Log = LoggerFactory.getLogger(CategoriaImplService.class);

    @Autowired
    private ICategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
        Log.info("Inicio metodo buscarCategorias()");
        CategoriaResponseRest response = new CategoriaResponseRest();
        try {
            List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
            response.getCategoriaResponse().setCategoria(categoria);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            Log.error("Error al consultar categorias", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al consultar categorias");
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);// Devuelve 500
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);// Devuelve 200

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategoriasPorId(long id) {
        Log.info("Inicio metodo buscarCategoriasPorId(id)");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();
        try {
            Optional<Categoria> categoria = categoriaDao.findById(id);
            if (categoria.isPresent()) {
                list.add(categoria.get());
                response.getCategoriaResponse().setCategoria(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);// Devuelve 404
            }
        } catch (Exception e) {
            Log.error("Error al consultar categorias", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al consultar categorias por id");
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);// Devuelve 500
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);// Devuelve 200

    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> crearCategoria(Categoria categoria) {
        Log.info("Inicio metodo crearCategoria(id)");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();
        try {
            Categoria categoriaGuardada = categoriaDao.save(categoria);

            if (categoriaGuardada != null) {
                list.add(categoriaGuardada);
                response.getCategoriaResponse().setCategoria(list);
                response.setMetadata("Respuesta ok", "00", "Categoria creada");
            } else {
                response.setMetadata("Respuesta nok", "-1", "Categoria no creada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);// Devuelve 404
            }

        } catch (Exception e) {
            Log.error("Error al crear categorias", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al crear categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);// Devuelve 500
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);// Devuelve 200

    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> actualizarCategoria(Categoria categoria, long id) {

        Log.info("Inicio metodo actualizarCategoria()");
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();
        try {
            Optional<Categoria> buscarCategoria = categoriaDao.findById(id);
            if (buscarCategoria.isPresent()) {

                buscarCategoria.get().setNombre(categoria.getNombre());
                buscarCategoria.get().setDescripcion(categoria.getDescripcion());

                Categoria categoriaActualizada = categoriaDao.save(buscarCategoria.get());
                if (categoriaActualizada != null) {
                    response.setMetadata("Respuesta ok", "00", "Categoria actualizada");
                    list.add(categoriaActualizada);
                    response.getCategoriaResponse().setCategoria(list);
                } else {
                    response.setMetadata("Respuesta nok", "-1", "Categoria no actualizada");
                    return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);// Devuelve 40
                }

            } else {
                response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);// Devuelve 404
            }

        } catch (Exception e) {
            Log.error("Error al actualizar categorias", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);// Devuelve 500
        }

        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);// Devuelve 200

    }

    @Override
    public ResponseEntity<CategoriaResponseRest> eliminarCategoria(long id) {
        Log.info("Inicio metodo eliminarCategoria()");
        CategoriaResponseRest response = new CategoriaResponseRest();
        try {
            Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
            if (categoriaBuscada.isPresent()) {
                categoriaDao.deleteById(id);
                response.setMetadata("Respuesta ok", "00", "Categoria eliminada");

            } else {
                response.setMetadata("Respuesta nok", "-1", "Categoria no encontrada");
                return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);// Devuelve 404
            }

        } catch (Exception e) {
            Log.error("Error al actualizar categorias", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar categoria");
            e.getStackTrace();
            return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);// Devuelve 500
        }
        return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK);// Devuelve 200

    }

}
