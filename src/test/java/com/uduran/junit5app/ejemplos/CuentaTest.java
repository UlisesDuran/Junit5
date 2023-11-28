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
        Cuenta cuenta = new Cuenta("Jon Doe", new BigDecimal("8900.9997"));
        Cuenta cuenta1 = new Cuenta("Jon Doe", new BigDecimal("8900.9997"));
        assertEquals(cuenta1, cuenta);
    }
    @Test
    void testDebitocuenta(){
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
    }
    
    @Test
    void testCreditoCuenta(){
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
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