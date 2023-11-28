package com.uduran.junit5app.ejemplos;

import java.math.BigDecimal;
import java.util.Objects;

public class Cuenta {
    private String persona;
    //Trabajamos con este por que de esta forma es mucho mas preciso para trabajar con dinero.
    private BigDecimal saldo;

    public Cuenta() {
    }

    public Cuenta(String persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return persona + ", saldo=" + saldo;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Cuenta cuenta = (Cuenta) object;
        return Objects.equals(persona, cuenta.persona) && Objects.equals(saldo, cuenta.saldo);
    }


    @Override
    public int hashCode() {
        return Objects.hash(persona, saldo);
    }

    public void debito(BigDecimal monto){
        this.saldo = this.saldo.subtract(monto);
    }

    public void credito(BigDecimal monto){
        this.saldo = this.saldo.add(monto);
    }

}


