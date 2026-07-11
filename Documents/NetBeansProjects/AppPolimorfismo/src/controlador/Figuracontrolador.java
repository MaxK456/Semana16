
package controlador;

import Modelo.circulo;
import Modelo.cuadrado;
import Modelo.figura;
import Modelo.rectangulo;


public class Figuracontrolador {
    // metodo para crear objeto de tipo figura 
    
    public figura crearfigura(int tipo, double valor1, double valor2) {
        figura obj = null;
        switch (tipo) {
            case 1:
                obj = new cuadrado(valor1);
                break;
            case 2:
                obj = new circulo(valor1);
                break;
            case 3:
                obj = new rectangulo(valor1, valor2);
                break;

        }
   return obj;
    }
}