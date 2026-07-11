
package Modelo;


public class cuadrado extends figura {
//
    private double lado;
    
    // constructor 

    public cuadrado(double lado) {
        this.lado = lado;
    }
    
    
    @Override
    public double Area() {
   return Math.pow(lado, 2);
    }

    @Override
    public double Perimetro() {
    return lado*4;
    }
    
}
