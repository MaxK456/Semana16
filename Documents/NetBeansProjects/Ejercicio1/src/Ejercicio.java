/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejercicio1;

import java.util.Scanner;

/**
 *
 * @author LAB-USR-LSUR
 */
public class Ejercicio1 {
      public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String respuesta = "si";

        while (respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("sí")) {

            int numero = 0;

            // Validar número con while
            while (numero < 1 || numero > 100) {
                System.out.print("Ingrese un número (1-100): ");
                numero = sc.nextInt();

                if (numero < 1 || numero > 100) {
                    System.out.println("Número inválido, intente otra vez.");
                }
            }

            sc.nextLine(); // limpiar buffer

            // Pedir respuesta
            System.out.print("¿Desea continuar? (Sí/No): ");
            respuesta = sc.nextLine();

            // Validar respuesta con while
            while (!respuesta.equalsIgnoreCase("si") &&
                   !respuesta.equalsIgnoreCase("sí") &&
                   !respuesta.equalsIgnoreCase("no")) {

                System.out.print("Respuesta inválida. Escriba Sí o No: ");
                respuesta = sc.nextLine();
            }
        }

        System.out.println("Programa terminado.");
        sc.close();
    }
}
