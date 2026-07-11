
package vista;

import Modelo.figura;
import controlador.Figuracontrolador;
import java.util.Scanner;


public class prueba {

    
    public static void main(String[] args) {
       // crear objeto de la clase Figuracontrolador
       
       Figuracontrolador obj=new Figuracontrolador();
       
       
       // importar modelo figura 
       figura fig = null;
       
       // ingresar datos por consola 
       
       Scanner sc = new Scanner(System.in);
       // atributos 
       
       int opc;
       double v1,v2;
       
       // enseñar menu de opciones 
       
        do {            
            System.out.println("==== FIGURAS GEOMETRICAS ====");
            System.out.println(" 1. Cuadrado ");
            System.out.println(" 2. Circulo ");
            System.out.println(" 3. Rectangulo ");
            System.out.println(" 4. Salir ");
            System.out.print(" INGRESE OPCIONES: ");
            opc = sc.nextInt();
            
            
            System.out.println("==== DATOS DE FIGURA GEOMETRICA ====");
            
            switch (opc) {
                case 1:
                    System.out.print("Ingrese lado: ");
                    v1 = sc.nextDouble();
                    fig = obj.crearfigura(opc, v1, 0);
                    break;
                case 2:
                    System.out.print("Ingrese radio: ");
                    v1 = sc.nextDouble();
                    fig = obj.crearfigura(opc, v1, 0);
                    break;
                case 3:
                    System.out.print("Ingrese largo: ");
                    v1 = sc.nextDouble();
                    System.out.print("Ingrese ancho: ");
                    v2=sc.nextDouble();
                    fig = obj.crearfigura(opc, v1, v2);
                    break;
                case 4:
                    break;

            }
            System.out.println("Area :" + fig.Area());
            System.out.println("Perimetro :" + fig.Perimetro());
            
        } while ( opc !=4);
        System.out.println("FIN DEL PRGRAMA");
    }
    
}
