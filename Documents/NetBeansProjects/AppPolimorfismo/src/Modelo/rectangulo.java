
package Modelo;

public class rectangulo extends figura  {

    public rectangulo(double valor1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double Area() {
       return largo*ancho;
    }

    @Override
    public double Perimetro() {
     return 2*largo+2*ancho;
    }
    
    
   // atributos 
    private double largo;
    private double ancho;

    public rectangulo(double largo, double ancho) {
        this.largo = largo;
        this.ancho = ancho;
    }
    
    
}
