
package Archivos;

import Listas.ListaSocio;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ArchivoSocios {
    //carga la lista de socios
    public static ListaSocio cargar(String archivo) {
        File arch = new File(archivo);

        if (!arch.exists()) {
            return new ListaSocio();
        }

        try (ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(arch))) {

            return (ListaSocio) ois.readObject();

        } catch (Exception e) {
            System.out.println("Error al cargar.");
            return new ListaSocio();
        }
    }
    //guarda la lista de socios 
    public static void guardar(String archivo, ListaSocio socios) {
        try (ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream(archivo))) {

            oos.writeObject(socios);

        } catch (Exception e) {
            System.out.println("Error real al guardar.");
            
        
    }
    }
}


