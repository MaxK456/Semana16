
package com.mycompany.persona;


public class Main {
    public static void main(String[] args) {
        // Creamos un tutor
        Tutor tutor1 = new Tutor(
            "miguel Toledo", 
            22, 
            "12345678A", 
            "5to Semestre", 
            9.5, 
            "Poo", 
            300.0
        );

        // Mostramos el resultado
        tutor1.mostrarDobleRol();
    }
}
