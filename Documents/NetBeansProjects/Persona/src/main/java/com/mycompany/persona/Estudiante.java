
package com.mycompany.persona;

public class Estudiante extends Persona implements IEstudiante {
    protected String grado;
    protected double promedio;

    public Estudiante(String nombre, int edad, String dni, String grado, double promedio) {
        super(nombre, edad, dni);
        this.grado = grado;
        this.promedio = promedio;
    }

    @Override
    public void mostrarRolEstudiante() {
        System.out.println("Rol: Estudiante de " + grado + " (Promedio: " + promedio + ")");
    }
}