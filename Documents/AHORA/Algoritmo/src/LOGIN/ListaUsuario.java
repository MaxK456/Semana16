
package LOGIN;
import Modelo.Socio;
import java.io.Serializable;
        

public class ListaUsuario implements Serializable {
    private static final long serialVersionUID = 7770649748224623780L;
    
    private Nodo inicio;
    private Nodo fin;

    public ListaUsuario() {
        this.inicio = null;
        this.fin = null;
    }
    
    //insertamos un usuario a la lista
    
    public void insertar(Usuario usuario) {

        Nodo nuevo = new Nodo(usuario);
        
        if(inicio == null) {
            
            inicio = nuevo;
            fin = nuevo;
        
        } else {
            fin.siguiente = nuevo;
            nuevo.anterior = fin;
            fin = nuevo;
        }
    }
    
    public Usuario buscar(String usuario) {

        Nodo actual = inicio;

        while (actual != null) {

            if (actual.usuario.getUsuario().equals(usuario)) {
                return actual.usuario;
            }

            actual = actual.siguiente;
        }

        return null;
    }

    public void mostrar() {

        Nodo actual = inicio;

        if (actual == null) {
            System.out.println("No hay usuarios registrados");
            return;
        }

        while (actual != null) {
            System.out.println(actual.usuario);
            actual = actual.siguiente;
        }
    }
    
    //valida los datos del acceso del usuario
    
    public Usuario validarLogin(String usuario, String contrasenia) {
        
        Nodo actual = inicio;
        
        while (actual != null) {
            Usuario user=actual.usuario;
            if (user != null && user.getUsuario() != null && user.getContraseña() != null) {
            
            // 2. Comparamos con seguridad
            if (user.getUsuario().equals(usuario) && user.getContraseña().equals(contrasenia)) {
            return user;
            }
            }
            actual = actual.siguiente;
            
        }
        
        return null;
    }  
    
    public void registrarCliente(Socio soc) {
        
        Usuario usuarioN = new Usuario(soc.getNombre(),soc.getUsuario(),soc.getContrasena(),"CLIENTE");
        insertar(usuarioN);
    }
}
