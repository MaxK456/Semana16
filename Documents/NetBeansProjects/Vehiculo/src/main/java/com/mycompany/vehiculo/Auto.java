
package com.mycompany.vehiculo;


public class Auto extends Vehiculo implements IAuto {
    protected int numPuertas;
    protected String combustible;

    public Auto(String marca, String modelo, int anio, int numPuertas, String combustible) {
        super(marca, modelo, anio);
        this.numPuertas = numPuertas;
        this.combustible = combustible;
    }

    @Override
    public void mostrarDetallesAuto() {
        System.out.println("Tipo: Auto | Puertas: " + numPuertas + " | Combustible: " + combustible);
    }
}
