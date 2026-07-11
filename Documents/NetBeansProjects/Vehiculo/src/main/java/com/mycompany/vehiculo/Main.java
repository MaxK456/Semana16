
package com.mycompany.vehiculo;

public class Main {
    public static void main(String[] args) {
        // 1. Probando un Auto normal
        Auto miAuto = new Auto("Toyota", "Corolla", 2022, 4, "Gasolina");
        miAuto.mostrarInfo();
        miAuto.mostrarDetallesAuto();

        System.out.println();

        // 2. Probando la "Herencia Múltiple" con MotoTaxi
        MotoTaxi miMotoTaxi = new MotoTaxi("Torito", "RE-200", 2024, 200, "Trimóvil", 2);
        miMotoTaxi.mostrarInfoMotoTaxi();
    }
}
