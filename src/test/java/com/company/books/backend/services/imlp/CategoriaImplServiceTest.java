package com.company.books.backend.services.imlp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.model.entity.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.services.impl.CategoriaImplService;

public class CategoriaImplServiceTest {
    @InjectMocks
    CategoriaImplService service;

    @Mock
    private ICategoriaDao categoriaDao;
    List <Categoria> list = new ArrayList<Categoria>();

    @BeforeEach
    public void setupThis() {
        System.out.println("Inicializando el test");
        MockitoAnnotations.openMocks(this);
        this.cargarCategorias();
    };
    @Test
    public void buscarCategoriasTest() {
        System.out.println("Ejecutando el test");
        when(categoriaDao.findAll()).thenReturn(list);
        ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
        assertEquals(4, response.getBody().getCategoriaResponse().getCategoria().size());
        verify(categoriaDao,times(1)).findAll();

       
    };
    public void cargarCategorias(){
        Categoria catUno= new Categoria(Long.valueOf(1),"Abarrotes","Descripcion de abarrotes");
        Categoria catDos= new Categoria(Long.valueOf(1),"Lacteos","Descripcion de Lacteos");
        Categoria catTres= new Categoria(Long.valueOf(1),"Bebidas","Descripcion de Bebidas");
        Categoria catCuatro= new Categoria(Long.valueOf(1),"Carnes blancas","Descripcion de Carnes blancas");
        list.add(catUno);
        list.add(catDos);
        list.add(catTres);
        list.add(catCuatro);


    }

}
