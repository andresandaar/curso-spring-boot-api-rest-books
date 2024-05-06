package com.company.books.backend.model.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libro")
public class Libro implements Serializable {
   
     private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    private String descripcion;
    /*
     * @ManyToOne= relacion de mucho a uno , una categira puede estar en muchos libros o  muchos libros pueden pertenecer a una categoría,
     *  pero cada libro pertenece a una sola categoría.
     *  * ------fetch = FetchType.LAZY------
     * 
     * existen dos:
     * EAGER = ancioso,lanzara toda la información relacionada con el usuario
     * ------caso de uso-----
     * Nosotros listamos un usuario por ejemplo en postman de una me devolvera 
     * toda la información relacionada con ese usurio roles, examenes, etc.
     * 
     * LAZY = perezoso,Solo lanza la información del usuario y no sus relaciones
     * 
     */

  
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

   

}
