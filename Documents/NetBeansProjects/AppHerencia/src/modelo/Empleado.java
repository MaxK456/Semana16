package modelo;

public class Empleado extends Persona {
    //atributos

    private double sueldo;

    // constructores 
    public Empleado() {
    }

    public Empleado(double sueldo, int id, String nombre, int edad) {
        super(id, nombre, edad);
        this.sueldo = sueldo;
    }

    // metodo de encapasulacion: get y set
    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
}
