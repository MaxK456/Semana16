
package LOGIN;

import LOGIN.Usuario;

import java.io.Serializable;

//presenta un nodo de la lista
public class Nodo implements Serializable {
    private static final long serialVersionUID = 2406578928111116041L;
    
    Usuario usuario;
    Nodo siguiente;
    Nodo anterior;

    public Nodo(Usuario usuario) {
        this.usuario = usuario;
        this.siguiente = null;
        this.anterior = null;
    }

    
}
