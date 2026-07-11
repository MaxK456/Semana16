
package com.mycompany.semana04;


public class empleado {
///metodos
    protected string nombre;
    protected double sueldo;

    public empleado() {
    }

    public empleado(string nombre, double sueldo) {
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public string getNombre() {
        return nombre;
    }

    public void setNombre(string nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
    //constructror
    