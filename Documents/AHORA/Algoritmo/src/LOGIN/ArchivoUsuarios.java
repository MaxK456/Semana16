
package LOGIN;
import LOGIN.ListaUsuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArchivoUsuarios {
     //carga la lista de usuario
     public static ListaUsuario cargar(String archivo) {

        File arch = new File(archivo);

        if (!arch.exists()) {
            return new ListaUsuario();
        }

        try (ObjectInputStream ois =
                new ObjectInputStream(
                        new FileInputStream(arch))) {

            return (ListaUsuario) ois.readObject();

        } catch (Exception e) {

            System.out.println("Error al cargar.");
            return new ListaUsuario();
        }
    }
     //guarda la lista de usuario
    public static void guardar(String archivo,
            ListaUsuario usuarios) {

        try (ObjectOutputStream oos =
                new ObjectOutputStream(
                        new FileOutputStream(archivo))) {

            oos.writeObject(usuarios);

        } catch (Exception e) {

            System.out.println("Error al guardar.");
        }
    }
    
}
