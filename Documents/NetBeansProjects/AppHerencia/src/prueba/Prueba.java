package prueba;

import java.util.Scanner;
import modelo.Empleado;

public class Prueba {

    public static void main(String[] args) {
        // crear objeto de la clase Empleado
        Empleado emp = new Empleado();

        //ingresar datos desde la consola
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese codigo :");
        int id = sc.nextInt();
        emp.setId(id);
        sc.nextLine();
        System.out.print("Ingrese nombre :");
        String nombre = sc.nextLine();
        emp.setNombre(nombre);

        System.out.print("Ingrese edad :");
        int edad = sc.nextInt();
        emp.setEdad(edad);

        System.out.print("Ingrese sueldo :");
        double sueldo = sc.nextDouble();
        emp.setSueldo(sueldo);

        //imprimir los datos
        System.out.println("===== Empleado =====");
        System.out.println("Codigo :" + emp.getId());
        System.out.println("Nombre :" + emp.getNombre());
        System.out.println("Edad :" + emp.getEdad());
        System.out.println("Sueldo :" + emp.getSueldo());
    }
}
