/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package algoritmos_s6;

import java.util.Scanner;

public class AcumuladorGastos {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        
        double totalCompra = 0; // Este es nuestro ACUMULADOR
        double precioProducto;  // Variable para leer cada entrada
        int cantidadItems = 0;  // Un contador opcional para saber cuántos llevamos

        System.out.println("--- CAJA REGISTRADORA ---");
        System.out.println("Ingrese los precios (Escriba '0' para finalizar):");

        do {
            System.out.print("Precio del producto: ");
            precioProducto = teclado.nextDouble();

            if (precioProducto > 0) {
                totalCompra += precioProducto; // Equivale a: totalCompra = totalCompra + precioProducto
                cantidadItems++;               // Contador para el reporte final
            } else if (precioProducto < 0) {
                System.out.println("Precio no válido, se ignorará.");
            }

        } while (precioProducto != 0); // El bucle sigue mientras el precio sea distinto de cero

        System.out.println("\n-------------------------------");
        System.out.println("Resumen de la compra:");
        System.out.println("Productos registrados: " + cantidadItems);
        System.out.printf("TOTAL A PAGAR: $%.2f%n", totalCompra);
        System.out.println("-------------------------------");
        
        teclado.close();
    }
}