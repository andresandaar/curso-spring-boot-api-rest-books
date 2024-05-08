package com.company.books.backend.ejemplos.junit;

public class Calculadora {

    /**
     * Suma dos números.
     */
    float sumar(float a, float b) {
        return a + b;
    }

    /**
     * Resta dos números.
     */
    float restar(float a, float b) {
        return a - b;
    }

    /**
     * Multiplica dos números.
     */
    float multiplicar(float a, float b) {
        if (!Float.isFinite(a) || !Float.isFinite(b)) {
            throw new IllegalArgumentException("Los operandos deben ser números finitos");
        }
        return a * b;
    }

    /**
     * Divide dos números.
     * @throws ArithmeticException si el divisor es cero.
     */
    float dividir(float a, float b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        if (!Float.isFinite(a) || !Float.isFinite(b)) {
            throw new IllegalArgumentException("Los operandos deben ser números finitos");
        }
        // Se podría agregar una validación adicional aquí para evitar la división por números muy pequeños
        return a / b;
    }

    // Aquí podrías agregar métodos adicionales según sea necesario.
}

