package com.mycompany.persona;

public class Tutor extends Estudiante implements IProfesor {
    private String especialidadTutor;
    private double salarioTutor;

    public Tutor(String nombre, int edad, String dni, String grado, double promedio, String especialidad, double salario) {
        super(nombre, edad, dni, grado, promedio);
        this.especialidadTutor = especialidad;
        this.salarioTutor = salario;
    }

    @Override
    public void mostrarRolProfesor() {
        System.out.println("Rol secundario: Tutor de " + especialidadTutor + " (Pago extra: $" + salarioTutor + ")");
    }

    public void mostrarDobleRol() {
        System.out.println("---------- FICHA DE TUTOR ----------");
        mostrarInfo(); 
        mostrarRolEstudiante();
        mostrarRolProfesor();
        System.out.println("------------------------------------");
    }
}