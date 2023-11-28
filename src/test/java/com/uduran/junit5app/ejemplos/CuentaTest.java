package com.uduran.junit5app.ejemplos;

import com.uduran.junit5app.ejemplos.exceptions.DineroInsuficienteException;
import com.uduran.junit5app.ejemplos.models.Banco;
import com.uduran.junit5app.ejemplos.models.Cuenta;
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
    void testSaldoCuenta() {
        Cuenta cuenta = new Cuenta("Ulises", new BigDecimal("1000.12345"));
        assertAll(
                () -> {
                    assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
                },
                () -> {
                    assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
                });
    }

    @Test
    void testReferenciaCuenta() {
        Cuenta cuenta = new Cuenta("Jon Doe", new BigDecimal("8900.9997"));
        Cuenta cuenta1 = new Cuenta("Jon Doe", new BigDecimal("8900.9997"));
        assertEquals(cuenta1, cuenta);
    }

    @Test
    void testDebitocuenta() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal(100));
        assertAll(
                () -> {
                    assertNotNull(cuenta.getSaldo());
                },
                () -> {
                    assertEquals(900, cuenta.getSaldo().intValue()
                },
                () -> {
                    assertEquals("900.12345", cuenta.getSaldo().toPlainString());
                    ;
                });

    }

    @Test
    void testCreditoCuenta() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));
        assertAll(
                () -> {
                    assertNotNull(cuenta.getSaldo());
                },
                () -> {
                    assertEquals(1100, cuenta.getSaldo().intValue());
                },
                () -> {
                    assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
                });
    }

    @Test
    void testDineroInsuficienteExceptionCuenta() {
        Cuenta cuenta = new Cuenta("Ulises", new BigDecimal("1000.12345"));
        Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.debito(new BigDecimal("1500"));
        });
        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(actual, esperado);
    }

    @Test
    void testTrasnferirDineroCuenta() {
        Cuenta cuenta1 = new Cuenta("John Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Ulises", new BigDecimal("1500"));

        Banco banco = new Banco("BBVA");
        banco.transferir(cuenta2, cuenta1, new BigDecimal(1000));
        assertAll(
                () -> {
                    assertEquals("500", cuenta2.getSaldo().toPlainString());
                },
                () -> {
                    assertEquals("3500", cuenta1.getSaldo().toPlainString());
                });

    }

    @Test
    void testRelacionBancoCuenta() {
        Cuenta cuenta1 = new Cuenta("John Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Ulises", new BigDecimal("1500"));

        Banco banco = new Banco("BBVA");
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);
        assertAll(
                () -> {
                    assertEquals(2, banco.getCuentas().size());
                },
                () -> {
                    assertEquals("BBVA", cuenta1.getBanco().getNombre());
                },
                () -> {
                    assertEquals("Ulises", banco.getCuentas().stream()
                            .filter(c -> c.getPersona().equals("Ulises"))
                            .findFirst().get().getPersona());
                },
                () -> {
                    assertTrue(banco.getCuentas().stream()
                            .anyMatch(c -> c.getPersona().equals("Ulises")));
                });

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