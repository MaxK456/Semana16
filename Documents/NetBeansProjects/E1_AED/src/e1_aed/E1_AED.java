package e1_aed;
import java.util.ArrayList;
import java.util.Scanner;

public class E1_AED {
    
    static private void Decorador(String word) {
        System.out.println("=========================================");
        System.out.println(word);
        System.out.println("=========================================");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList <String> estudiantes = new ArrayList();
        int opcion;
        do {
            System.out.println("-----MENÚ-----");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Mostrar estudiantes");
            System.out.println("3. Buscar estudiante");
            System.out.println("4. Eliminar estudiante");
            System.out.println("5. Salir");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion){
                case 1: {
                    System.out.println("Ingrese el nombre del estudiante para agregar: ");
                    String nombre = sc.nextLine();
                    estudiantes.add(nombre);
                    Decorador("Estudiante añadido con exito!!!");
                    break;
                }
                case 2: {
                    for (String e: estudiantes) {
                        System.out.println(e);
                    }
                    break;
                }
                case 3: {
                    System.out.println("Ingrese el nombre del estudiante a buscar: ");
                    String nombre = sc.nextLine();
                    if (estudiantes.contains(nombre)) {
                       for (String e: estudiantes) {
                        if (e==nombre) {
                            Decorador("El estudiante se encuentra registrado.");
                        }
                    } 
                    }
                    else {
                        Decorador("El estudiante no existe");
                    }
                    break;
                }
                case 4: {
                    System.out.println("Ingrese el nombre del estudiante a eliminar: ");
                    String nombre = sc.nextLine();
                    if (estudiantes.contains(nombre)) {
                       estudiantes.remove(nombre);
                    Decorador("Estudiante eliminado con exito!!!");
                    }
                    else {
                        Decorador("El estudiante no existe.");
                    }
                    break; 
                }
                case 5: {
                    Decorador("Saliendo del sistema...");
                    break;
                }
                default: {
                    Decorador("Opción no reconocida.");
                }
            }
        }
        while (opcion != 5);
    }
    
}
