
package prueba;

import java.util.Scanner;
import modelo.Empleado;

public class Prueba1 {

    public static void main(String[] args) {
                // crear objeto de la clase Empleado
        Empleado emp = new Empleado();

        //ingresar datos desde la consola
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese codigo: ");
        emp.setId(sc.nextInt());
        
        System.out.print("Ingrese nombre: ");
        emp.setNombre(sc.next());

        System.out.print("Ingrese edad: ");
        emp.setEdad(sc.nextInt());

        System.out.print("Ingrese sueldo: ");
        emp.setSueldo(sc.nextDouble());

        //imprimir los datos
        System.out.println("===== Empleado =====");
        System.out.println("Codigo: " + emp.getId());
        System.out.println("Nombre: " + emp.getNombre());
        System.out.println("Edad: " + emp.getEdad());
        System.out.println("Sueldo: " + emp.getSueldo());
    }
}
