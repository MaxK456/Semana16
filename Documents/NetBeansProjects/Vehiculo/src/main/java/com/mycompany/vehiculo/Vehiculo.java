
package com.mycompany.vehiculo;


public class Vehiculo {
    protected String marca;
    protected String modelo;
    protected int anio;

    public Vehiculo(String marca, String modelo, int anio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
    }

    public void mostrarInfo() {
        System.out.println("Vehículo: " + marca + " " + modelo + " (" + anio + ")");
    }

    // El método main DEBE estar dentro de las llaves de la clase
    public static void main(String[] args) {
        // Ejemplo de Auto
        Auto miAuto = new Auto("Toyota", "Corolla", 2022, 4, "Gasolina");
        miAuto.mostrarInfo();
        miAuto.mostrarDetallesAuto();

        System.out.println("------------------------------------");

        // Ejemplo de MotoTaxi (Herencia múltiple simulada)
        MotoTaxi miMT = new MotoTaxi("Torito", "RE-200", 2024, 200, "Trimóvil", 2);
        miMT.mostrarInfoMotoTaxi();
    }
}