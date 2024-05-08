package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AsserArrayEqualsTeoria {

    @Test
    public void pruebaArreglo()
    {
        String[] arreglito = {"1", "2"};
        String[] arreglitoDos = {"1", "2"};
        String[] arreglitoTres = {"1", "2","3"};
        assertArrayEquals(arreglito, arreglitoDos, "Los arreglos no son iguales");

        assertArrayEquals(arreglito, arreglitoTres, "Los arreglos no son iguales");;
    }

}
