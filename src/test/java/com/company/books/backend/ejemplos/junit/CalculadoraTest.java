package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	Calculadora calculadora ;
    @BeforeAll
    public static void setup() {
        System.out.println("Inicializando la configuración");
    };

    @AfterAll
    public static void cleanup() {
        System.out.println("Finalizando la configuración");
    };

   @BeforeEach
   public void setupThis() {
	   System.out.println("Inicializando el test");
	   calculadora = new Calculadora();
    };

    @AfterEach
    public void cleanupThis() {
        System.out.println("Finalizando el test");
    };

    @Test
    @DisplayName("Prueba que ocupa AssertEquals")
    @Disabled("Esta prueba no se executara")
    public void calculadorAssertEqualsTest() {
        
        assertEquals(7, calculadora.sumar(3, 5));
        assertEquals(6, calculadora.restar(10, 4)); // Comprobar que la resta de 10 y 4 es 6
        assertEquals(6, calculadora.multiplicar(2, 3)); // Comprobar que la multiplicación de 2 y 3 es 6
        assertEquals(5, calculadora.dividir(10, 2)); // Comprobar que la división de 10 entre 2 es 5
        assertThrows(ArithmeticException.class, () -> {
            calculadora.dividir(10, 0); // Debería lanzar una excepción de división por cero
        });
    };

    @Test
    public void calculadorAsserTrueFalse() {
        assertTrue(calculadora.sumar(3, 5)==8);
        assertTrue(calculadora.restar(10, 4)==6);
        assertFalse(calculadora.multiplicar(2, 3)==7);
        assertFalse(calculadora.dividir(10, 2)==6);
    }

}
