
package com.mycompany.vehiculo;

public class MotoTaxi extends Moto implements IAuto {
    private int numPuertasAdaptadas;
    private String tipoTecho;

    public MotoTaxi(String marca, String modelo, int anio, int cilindrada, String tipoMoto, int puertas) {
        // Hereda atributos de Moto
        super(marca, modelo, anio, cilindrada, tipoMoto);
        this.numPuertasAdaptadas = puertas;
        this.tipoTecho = "Lona Reforzada";
    }

    @Override
    public void mostrarDetallesAuto() {
        System.out.println("Atributo de Auto -> Cabina con " + numPuertasAdaptadas + " entradas.");
    }

    public void mostrarInfoMotoTaxi() {
        System.out.println("=== SISTEMA DE TRANSPORTE URBANO: MOTOTAXI ===");
        mostrarInfo();          // De Vehiculo
        mostrarDetallesMoto();  // De Moto
        mostrarDetallesAuto();  // Simulado de Auto
        System.out.println("Carrocería: " + tipoTecho);
        System.out.println("-----");
    }
}
