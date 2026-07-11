
package Archivos;

import Listas.ListaTrabajador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ArchivoTrabajador {

    // Método para cargar la lista desde el archivo
    public static ListaTrabajador cargarT(String archivo) {
        File arch = new File(archivo);

        // Si el archivo no existe, devolvemos una lista nueva vacía
        if (!arch.exists()) {
            return new ListaTrabajador();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arch))) {
            return (ListaTrabajador) ois.readObject();
        } catch (Exception e) {
            System.out.println("error al cargar trabajadores del archivo.");
            return new ListaTrabajador();
        }
    }


    public static void guardarT(String archivo, ListaTrabajador trabajadores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(trabajadores);
        } catch (Exception e) {
            System.out.println("error al guardar trabajadores en el archivo.");
        }
    }
}