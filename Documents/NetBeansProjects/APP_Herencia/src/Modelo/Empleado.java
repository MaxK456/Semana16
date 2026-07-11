
package Modelo;


public class Empleado extends Persona  {
    
  private double sueldo;  

    public Empleado() {
    }

    public Empleado(double sueldo) {
        this.sueldo = sueldo;
    }

    public Empleado(double sueldo, int id, String Nombreper, int edad) {
        super(id, Nombreper, edad);
        this.sueldo = sueldo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String detalle_Empleado() {
        return "======= Empleado 1  ======= " +super.detalle_Persona () +"\nSueldo :" this.sueldo ;
    }
}
    
 
    
    
   
    

