
package utp;

import java.util.Scanner;


public class Prueba {

   
    public static void main(String[] args) {
      
             // crear objeto de la clase Pedido
        Pedido p = new Pedido();

        // asignar valor al objeto p
        Scanner sc = new Scanner(System.in);

        // ingresar datos desde la consola y asignar valores al objeto p
        System.out.print("Ingrese Proveedor:");
        p.setProveedor(sc.nextLine());

        System.out.print("Ingrese Importe:");
        p.setImporte(sc.nextDouble());

        
        // imprimir resultados
        System.out.println("===== DATOS DEL PEDIDO ======");
        System.out.println("Proveedor :"+p.getProveedor());
        System.out.println("Importe :"+p.getImporte());
        System.out.println("Impuesto :"+p.calcularImpuesto());
        System.out.println("Total a Pagar :"+p.calcularTotalAPagar());   

    }

}
    



