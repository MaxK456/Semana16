

package com.mycompany.persona;

public class Persona {
    protected String nombre;
    protected int edad;
    protected String dni;

    public Persona(String nombre, int edad, String dni) {
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
    }

    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre + " | Edad: " + edad + " | DNI: " + dni);
    }
}