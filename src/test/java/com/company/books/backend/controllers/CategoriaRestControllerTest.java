package com.company.books.backend.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.company.books.backend.model.entity.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.services.ICategoriaService;

public class CategoriaRestControllerTest {
    @InjectMocks
    CategoriaRestController categoriaRestController;

    @Mock
    private ICategoriaService service;

     @BeforeEach
    public void setupThis() {
        System.out.println("Inicializando el test");
        MockitoAnnotations.openMocks(this);
    };

    @SuppressWarnings("deprecation")
    @Test
    public void createTest(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Categoria categoria = new Categoria(Long.valueOf(1),"Abarrotes","Descripcion de abarrotes");
        when(service.crearCategoria(any(Categoria.class))).thenReturn(new ResponseEntity<CategoriaResponseRest>(HttpStatus.CREATED));
        ResponseEntity<CategoriaResponseRest> respuesta = categoriaRestController.crearCategorias(categoria);
        assertThat(respuesta.getStatusCodeValue(),equalTo(201));
      
    }
}
