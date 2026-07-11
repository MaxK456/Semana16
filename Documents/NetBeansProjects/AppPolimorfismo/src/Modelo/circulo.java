
package Modelo;


public class circulo extends figura {
// atributos 
    private double radio;
    // constructor 

    public circulo(double radio) {
        this.radio = radio;
    }
    
    @Override
    public double Area() {
 return Math.PI*Math.pow(radio, 2);
    }

    @Override
    public double Perimetro() {
 return 2*Math.PI*radio;
    }
    
}
