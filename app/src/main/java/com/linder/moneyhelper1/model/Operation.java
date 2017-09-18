package com.linder.moneyhelper1.model;

/**
 * Created by linderhassinger on 9/14/17.
 */

public class Operation {
    private String tipoE;
    private double monto;
    private String tarjett;


    public Operation(double monto, String tipoE, String tarjett) {
        this.tipoE = tipoE;
        this.monto = monto;
        this.tarjett = tarjett;
    }

    public String getTipoE() {
        return tipoE;
    }

    public void setTipoE(String tipoE) {
        this.tipoE = tipoE;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTarjett() {
        return tarjett;
    }

    public void setTarjett(String tarjett) {
        this.tarjett = tarjett;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "tipoE='" + tipoE + '\'' +
                ", monto=" + monto +
                ", tarjett='" + tarjett + '\'' +
                '}';
    }
}
