package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AssertEqualsTeoria {
@Test
    public void miTest(){
        String actual = "hola1";
        String expected = "hola1";
        assertEquals(expected, actual, "La prueba ha fallado");
    }

}
