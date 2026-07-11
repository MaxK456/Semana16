
package LOGIN;
import Modelo.Club;
import Modelo.Socio;
import static Principal.Sistema.menuCliente;
import Queue.metodosCola;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Usuario implements Serializable{
    
    private static final long serialVersionUID=1L;
    
    private String nombre;
    
    private String usuario;
    
    private String contrasenia;
    
    private String rol;

    public Usuario(String nombre, String usuario, String contrasenia, String rol) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contrasenia;
    }

    public void setContraseña(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    //genera un usuario aleatorio
    public static String generarUsuario(int id) {
        return "Club" +String.format("%04d", id % 10000);
    }
    
    //genera credenciales aleatorias
     public static String generarTrabajador(int id) {
        
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random r = new Random();
        String usuario = "Trab"; 
        for (int i = 0; i < 4; i++) {
            usuario = usuario + caracteres.charAt(r.nextInt(caracteres.length()));
        }
        return usuario;
    }
    
    public static String generarContrasenia() {
        
        String caracter ="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        
        Random r = new Random();
        
        String contrasenia = "";
        
        for (int i = 0; i < 6; i++) {

        contrasenia =contrasenia + caracter.charAt(r.nextInt(caracter.length()));
        }
        
        return contrasenia;
    }

    
}
