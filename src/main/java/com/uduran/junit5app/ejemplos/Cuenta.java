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
    public boolean equals(Object obj) {
        if(obj instanceof Cuenta) {
            return false;
        }
        Cuenta c = (Cuenta) obj;
        if(this.persona==null || this.saldo==null){
            return false;
        }
        return this.persona.equals(c.getPersona()) && this.saldo.equals(c.getSaldo());
    }
}


