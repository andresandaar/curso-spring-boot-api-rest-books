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

import com.company.books.backend.model.dao.ILibroDao;
import com.company.books.backend.model.entity.Libro;
import com.company.books.backend.response.LibroResponseRest;
import com.company.books.backend.services.ILibroService;

@Service
public class LibroImplService implements ILibroService {
    private static final Logger Log = LoggerFactory.getLogger(LibroImplService.class);

    @Autowired
    private ILibroDao libroDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> getLibros() {

        Log.info("Inicio metodo buscarLibros()");
        LibroResponseRest response = new LibroResponseRest();

        try {
            List<Libro> libros = (List<Libro>) libroDao.findAll();
            response.getLibroResponse().setLibro(libros);
            response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
        } catch (Exception e) {
            Log.error("Error al consultar los libros", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al consultar los libros");
            e.getStackTrace();
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);// Devuelve 500
        }
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);// Devuelve 200

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> getLibroById(long id) {

        Log.info("Inicio metodo getLibroById()");
        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();
        try {
            Optional<Libro> libro = libroDao.findById(id);
            if (libro.isPresent()) {
                list.add(libro.get());
                response.getLibroResponse().setLibro(list);
                response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
            } else {
                response.setMetadata("Respuesta nok", "-1", "Libro no encontrado");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);// Devuelve 404
            }
        } catch (Exception e) {
            Log.error("Error al consultar el libro", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al consultar el libro por id");
            e.getStackTrace();
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);// Devuelve 500
        }
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);// Devuelve 200
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> createLibro(Libro libro) {

        Log.info("Inicio metodo createLibro()");
        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();
        try {
            Libro libroGuardado = libroDao.save(libro);

            if (libroGuardado != null) {
                list.add(libroGuardado);
                response.getLibroResponse().setLibro(list);
                response.setMetadata("Respuesta ok", "00", "Libro creado");
            } else {
                response.setMetadata("Respuesta nok", "-1", "Libro no creado");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);// Devuelve 404
            }

        } catch (Exception e) {
            Log.error("Error al crear libro", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al crear libro");
            e.getStackTrace();
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);// Devuelve 500
        }

        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);// Devuelve 200
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> updateLibro(Libro libro, long id) {

        Log.info("Inicio metodo updateLibro()");
        LibroResponseRest response = new LibroResponseRest();
        List<Libro> list = new ArrayList<>();
        try {
            Optional<Libro> buscarLibro = libroDao.findById(id);
            if (buscarLibro.isPresent()) {

                buscarLibro.get().setNombre(libro.getNombre());
                buscarLibro.get().setDescripcion(libro.getDescripcion());
                buscarLibro.get().setCategoria(libro.getCategoria());

                Libro libroActualizado = libroDao.save(buscarLibro.get());
                if (libroActualizado != null) {
                    response.setMetadata("Respuesta ok", "00", "Libro actualizado");
                    list.add(libroActualizado);
                    response.getLibroResponse().setLibro(list);
                } else {
                    response.setMetadata("Respuesta nok", "-1", "Libro no actualizado");
                    return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);// Devuelve 40
                }

            } else {
                response.setMetadata("Respuesta nok", "-1", "Libro no encontrado");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);// Devuelve 404
            }

        } catch (Exception e) {
            Log.error("Error al actualizar el libro", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al actualizar el libro");
            e.getStackTrace();
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);// Devuelve 500
        }

        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);// Devuelve 200
    }

    @Override
    public ResponseEntity<LibroResponseRest> deleteLibro(long id) {

        Log.info("Inicio metodo deleteLibro()");
        LibroResponseRest response = new LibroResponseRest();
        try {
            Optional<Libro> libroBuscado = libroDao.findById(id);
            if (libroBuscado.isPresent()) {
                libroDao.deleteById(id);
                response.setMetadata("Respuesta ok", "00", "Libro eliminado");

            } else {
                response.setMetadata("Respuesta nok", "-1", "Libro no encontrado");
                return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);// Devuelve 404
            }

        } catch (Exception e) {
            Log.error("Error al eliminar libro", e.getMessage());
            response.setMetadata("Respuesta nok", "-1", "Error al eliminar libro");
            e.getStackTrace();
            return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);// Devuelve 500
        }
        return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK);// Devuelve 200
    }

}
