
package Archivos;

import Modelo.Invitado;
import java.io.*;
import java.util.ArrayList;

public class ArchivoInvitados {

    // metodo de guardar a los invitados
    public static void guardar(String ruta, ArrayList<Invitado> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(lista);
            // System.out.println("Archivo de invitados guardado de forma conforme.");
        } catch (IOException e) {
            System.out.println("error al guardar el archivo de invitados: " + e.getMessage());
        }
    }

    // carga la lista de invitados
    public static ArrayList<Invitado> cargar(String ruta) {
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            // sino existe aarroja una lista en blanco
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (ArrayList<Invitado>) ois.readObject();
        } catch (Exception e) {
            System.out.println("error al cargar el archivo de invitados: " + e.getMessage());
            return new ArrayList<>(); 
        }
    }
}