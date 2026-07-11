
package com.mycompany.mavenproject1;

import java.util.Scanner;

/**
 *
 * @author LAB-USR-LSUR
 */
public class Mavenproject1 {

    public static void main(String[] args) {
       int edad=0 ;
               Scanner leer = new Scanner(System.in);
                       // Entrada de datos 
                       while(edad<10) {
               System.out.println("Ingrese su edad: ");
               edad = leer.nextInt();
                           System.out.println("La edad ingresada es: "+edad);
                           
    }
    }
}
