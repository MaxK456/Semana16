
package Modelo;
import java.io.Serializable;
import java.time.LocalDate;

public class Trabajador implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int id;
    private String nombre;
    private String telefono;
    private String cargo; 
    private String correo;
    private String usuario;
    private String contrasenia;
    private String rol;
    
    //los datos del trabajador
    public Trabajador(int id, String nombre, String telefono, String cargo, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.cargo = cargo;
        this.correo = correo;
        this.rol = "TRABAJADOR"; 
        this.usuario = "";       
        this.contrasenia = "";   
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasenia;
    }

    public void setContrasena(String contrasena) {
        this.contrasenia = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
     
    //muestra los datos del trabajador
    @Override
    public String toString() {
        return String.format("ID: %d | Nombre: %-15s | Cargo: %-10s | Usuario: %-10s | Contrasenia: %s", 
                         id, nombre, cargo, usuario, contrasenia);
    }
}

