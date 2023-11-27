package com.uduran.junit5app.ejemplos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    @Test
    void testCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setPersona("Ulises");
        String esperado = "Ulises";
        String real = cuenta.getPersona();
        Assertions.assertEquals(esperado, real);
    }
    @Test
    void testSaldoCuenta(){
        Cuenta cuenta = new Cuenta("Ulises", new BigDecimal("1000.12345"));
        assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
    }
    @Test
    void testReferenciaCuenta(){
        Cuenta cuenta = new Cuenta("Jong Doe", new BigDecimal("8900.9997"));
        Cuenta cuenta1 = new Cuenta("Jong Doe", new BigDecimal("8900.9997"));
        assertEquals(cuenta1, cuenta);
    }

    @Test
    void getPersona() {
    }

    @Test
    void setPersona() {
    }

    @Test
    void getSaldo() {
    }

    @Test
    void setSaldo() {
    }

    @Test
    void testToString() {
    }
}