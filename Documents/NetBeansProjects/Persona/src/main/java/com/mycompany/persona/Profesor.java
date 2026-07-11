
package com.mycompany.persona;

public class Profesor extends Persona implements IProfesor {
    protected String especialidad;
    protected double salario;

    public Profesor(String nombre, int edad, String dni, String especialidad, double salario) {
        super(nombre, edad, dni);
        this.especialidad = especialidad;
        this.salario = salario;
    }

    @Override
    public void mostrarRolProfesor() {
        System.out.println("Rol: Profesor de " + especialidad + " (Salario: $" + salario + ")");
    }
}