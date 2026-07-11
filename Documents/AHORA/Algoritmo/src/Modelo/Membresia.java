
package Modelo;

import java.io.Serializable;

public class Membresia implements Serializable{
    private static final long serialVersionUID = 2680133759926924942L;
    private String tipo;
    private double costo;
    
    public double getCosto() {
    return costo;
}

public String getTipo() {
    return tipo;
}
    
    //las membresias
    public Membresia(int opcion) {
        switch (opcion) {
            case 1: 
                this.tipo = "Basica";
                this.costo = 150.0; 
                break;
            case 2:
                this.tipo = "Silver";
                this.costo = 200.0; 
                break;
            case 3: 
                this.tipo = "Gold"; 
                this.costo = 350.0; 
                break;
            default:
                this.tipo = "Invitado"; 
                this.costo = 50.0;
        }
    }

   
    @Override
    public String toString() {
        return tipo;
    }
}