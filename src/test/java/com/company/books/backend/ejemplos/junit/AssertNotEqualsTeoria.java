package com.company.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class AssertNotEqualsTeoria {

@Test
public void miTest() {
	
	String actual = "hola";
	String expected = "hola2";

	
	assertNotEquals(expected, actual);
    assertNotEquals(expected, actual, "el mensaje de error");
}

}
