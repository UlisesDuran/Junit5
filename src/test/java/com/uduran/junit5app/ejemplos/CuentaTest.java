package com.uduran.junit5app.ejemplos;

import com.uduran.junit5app.ejemplos.exceptions.DineroInsuficienteException;
import com.uduran.junit5app.ejemplos.models.Banco;
import com.uduran.junit5app.ejemplos.models.Cuenta;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.w3c.dom.ls.LSOutput;

import java.math.BigDecimal;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CuentaTest {

    Cuenta cuenta;

    @BeforeEach
    void initTestMethod(){
        this.cuenta = new Cuenta("Ulises", new BigDecimal("1000.12345"));
        System.out.println("Iniciando metodo de prueba...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Finalizando metodo test...");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicializando el Test...");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finalizando el Test...");
    }

    @Nested
    class SistemaOperativoTest{
        @Test
        @EnabledOnOs(OS.WINDOWS)
        void testSoloWindows(){
        }

        @Test
        @EnabledOnOs({OS.MAC, OS.LINUX})
        void testSoloLinuxMac(){
        }

        @Test
        @DisabledOnOs(OS.WINDOWS)
        void testNoWindows(){
        }
    }
    @Nested
    class JavaVersionTest{

        @Test
        @EnabledOnJre(JRE.JAVA_8)
        void testSoloJdk8(){
        }
        @Test
        @EnabledOnJre(JRE.JAVA_21)
        void testSoloJdk21(){
        }
    }
    @Nested
    class SystemPropertiesTest{
        @Test
        void testImprimirSystemProperties(){
            Properties properties = System.getProperties();
            properties.forEach((k, v) -> System.out.println(k + ": " + v));
        }
        @Test
        @EnabledIfSystemProperty(named = "java.version", matches = "19.0.2")
        void testSoloJdk19(){
        }
        @Test
        @EnabledIfSystemProperty(named = "ENV", matches = "dev")
        void testSoloEnDev(){
        }
    }


    @Test
    @DisplayName("Probando nombre de la cuenta ¿?")
    void testCuenta() {
        String esperado = "Ulises";
        String real = cuenta.getPersona();
        Assertions.assertEquals(esperado, real);
    }

    @Test
    @DisplayName("Probando el saldo de la cuenta ¿?")
    void testSaldoCuenta() {
        assertAll(
                () -> assertEquals(1000.12345, cuenta.getSaldo().doubleValue()),
                () -> assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0));
    }

    @Test
    @DisplayName("Probando que las referencias sean iguales ¿?")
    void testReferenciaCuenta() {
        Cuenta cuenta1 = new Cuenta("Jon Doe", new BigDecimal("8900.9997"));
        Cuenta cuenta2 = new Cuenta("Jon Doe", new BigDecimal("8900.9997"));
        assertEquals(cuenta2, cuenta1);
    }

    @Test
    void testDebitocuenta() {
        cuenta.debito(new BigDecimal(100));
        assertAll(
                () -> assertNotNull(cuenta.getSaldo()),
                () -> assertEquals(900, cuenta.getSaldo().intValue()),
                () -> assertEquals("900.12345", cuenta.getSaldo().toPlainString()));
    }

    @Test
    void testCreditoCuenta() {
        cuenta.credito(new BigDecimal(100));
        assertAll(
                () -> assertNotNull(cuenta.getSaldo()),
                () -> assertEquals(1100, cuenta.getSaldo().intValue()),
                () -> assertEquals("1100.12345", cuenta.getSaldo().toPlainString()));
    }

    @Test
    void testDineroInsuficienteExceptionCuenta() {
        Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.debito(new BigDecimal("1500"));
        });
        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        // Es mejor crearlo el mensaje de error con Lambda porque así solo se crea si la prueba falla.
        assertEquals(actual, esperado, () -> "La cuenta no tiene suficiente dinero!");
    }

    @Test
    void testTrasnferirDineroCuenta() {
        Cuenta cuenta1 = new Cuenta("John Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Ulises", new BigDecimal("1500"));
        Banco banco = new Banco("BBVA");
        banco.transferir(cuenta2, cuenta1, new BigDecimal(1000));
        assertAll(
                () -> assertEquals("500", cuenta2.getSaldo().toPlainString()),
                () -> assertEquals("3500", cuenta1.getSaldo().toPlainString()));

    }

    @Test
    void testRelacionBancoCuenta() {
        Cuenta cuenta1 = new Cuenta("John Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Ulises", new BigDecimal("1500"));
        Banco banco = new Banco("BBVA");
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);
        assertAll(
                () -> assertEquals(2, banco.getCuentas().size()),
                () -> assertEquals("BBVA", cuenta1.getBanco().getNombre()),
                () -> assertEquals("Ulises", banco.getCuentas().stream()
                            .filter(c -> c.getPersona().equals("Ulises"))
                            .findFirst().get().getPersona()),
                () -> assertTrue(banco.getCuentas().stream()
                            .anyMatch(c -> c.getPersona().equals("Ulises"))));
    }

    @Test
    @DisplayName("test saldo cuenta dev")
    void testSaldoCuentaDev() {
        boolean esDev = "dev".equals(System.getProperty("ENV"));
        assumeTrue(esDev);
        assumingThat(esDev, ()->{
            assertAll(
                    () -> System.out.println("Estoy aqui"),
                    () -> assertNotNull(cuenta.getSaldo()),
                    () -> assertEquals(1000.12345, cuenta.getSaldo().doubleValue()),
                    () -> assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0));
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