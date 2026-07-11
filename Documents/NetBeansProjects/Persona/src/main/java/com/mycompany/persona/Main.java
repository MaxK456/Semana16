
package com.mycompany.persona;


public class Main {
    public static void main(String[] args) {
        // Creamos un tutor
        Tutor tutor1 = new Tutor(
            "Carlos Ruiz", 
            22, 
            "12345678A", 
            "10mo Semestre", 
            9.5, 
            "Álgebra Lineal", 
            300.0
        );

        // Mostramos el resultado
        tutor1.mostrarDobleRol();
    }
}
