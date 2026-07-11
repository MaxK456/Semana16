
package gestiontareas;

import java.util.LinkedList;


public class GestionTareas {

    public static void main(String[] args) {
        
        // 1. Instanciación de la Lista Enlazada
        LinkedList<String> listaTareas = new LinkedList<>();

        // 2. Agregar elementos (Nodos)
        listaTareas.add("Revisar correo ");
        listaTareas.add("Clase de Algoritmos ");
        listaTareas.add("Almorzar ");
        listaTareas.add("Hacer ejercicio  ");

        // 3. Eliminar el indice de la lista
        System.out.println("Eliminar por indice");
        int indiceAEliminar = 0;
        if ( indiceAEliminar >= 0 && indiceAEliminar<listaTareas.size());
        //System.out.println("Eliminando la última tarea pendiente...");
        listaTareas.remove(indiceAEliminar);
        System.out.println("Tarea en la posicion" + indiceAEliminar + "eliminada");

        // 4. Mostrar el contenido recorriendo la lista
        System.out.println("\n--- LISTA DE TAREAS ACTUAL ---");
        
        // Usamos un bucle "for-each" para navegar por los enlaces
        for (String tarea : listaTareas) {
            System.out.println("- " + tarea);
        }

        // Mostrar tamaño dinámico
        System.out.println("\nTotal de tareas: " + listaTareas.size());
    }
}

