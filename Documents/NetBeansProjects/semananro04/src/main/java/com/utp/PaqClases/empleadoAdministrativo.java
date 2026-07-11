
package com.utp.PaqClases;

public class empleadoAdministrativo extends empleado{
    //
   private double bonificación;
   //cosntructor

    public empleadoAdministrativo(double bonificación, String nombre, double sueldo) {
        super(nombre, sueldo);
        this.bonificación = bonificación;
    }

    public empleadoAdministrativo(double bonificación) {
        this.bonificación = bonificación;
    }

    public double getBonificación() {
        return bonificación;
    }

    public void setBonificación(double bonificación) {
        this.bonificación = bonificación;
    }
   //CalcularSueldo
    public double calcularSueldoTotal(){
        return sueldo+bonificación;
        
    }
   
}
