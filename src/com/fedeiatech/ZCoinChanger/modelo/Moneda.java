package com.fedeiatech.ZCoinChanger.modelo;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Moneda {

    @SerializedName("result")
    public boolean exito;

    @SerializedName("base_code")
    public String divisa;

    @SerializedName("conversion_rates")
    public Map<String, Double> conversiones;

    public Moneda(String divisa, Map<String, Double> conversiones) {
        this.divisa = divisa;
        this.conversiones = conversiones;
    }

    public Moneda(MonedaAPI monedaAPI) {
        if (monedaAPI.result().contains("error")) {
            throw new RuntimeException("No se encontró la divisa.");
        }
        this.exito = true;
        this.divisa = monedaAPI.base_code();
        this.conversiones = monedaAPI.conversion_rates();
    }

    public double obtenerConversion(String divisa) {
        if (!conversiones.containsKey(divisa)) {
            throw new IllegalArgumentException("Divisa no válida.");
        }
        return conversiones.get(divisa);
    }

    public String getDivisa() {
        return divisa;
    }

    @Override
    public String toString() {
        return "Moneda: " + divisa;
    }
}
