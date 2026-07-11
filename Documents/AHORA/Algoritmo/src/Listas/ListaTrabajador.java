
package Listas;

import Modelo.Trabajador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaTrabajador implements Serializable {
    private static final long serialVersionUID = 7997565944685403134L;
    private Nodo inicio;
    private Nodo fin;

    public ListaTrabajador() {
        this.inicio = null;
        this.fin = null;
    }
    
    //se inserta trabajador a la lista
    
    public void insertar(Trabajador trabajador) {
        Nodo nuevo = new Nodo(trabajador);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            nuevo.anterior = fin;
            fin = nuevo;
        }
    }

    public void mostrar() {
        Nodo actual = inicio;
        if (actual == null) {
            System.out.println("No hay trabajadores registrados.");
            return;
        }
        
        System.out.println("\n--- LISTA DE TRABAJADORES ---");
        while (actual != null) {
            System.out.println(actual.trabajador);
            actual = actual.siguiente;
        }
    }
    //se elimina al trabajador por su id
    public void eliminar(int id){
        if(inicio != null){
            
        if(inicio.trabajador.getId() == id){
            inicio = inicio.siguiente;
            if(inicio != null){
                inicio.anterior = null;
            }else{
                fin = null;
            }
        }else{
            Nodo eliminar = inicio.siguiente;
            
            while(eliminar != null && eliminar.trabajador.getId() != id){
                eliminar = eliminar.siguiente;
            }

            if(eliminar != null){
                if(eliminar == fin){
                    fin = eliminar.anterior;
                    fin.siguiente = null;
                }else{
                  
                    eliminar.anterior.siguiente = eliminar.siguiente;
                    eliminar.siguiente.anterior = eliminar.anterior;
                }
            }
        }
        }
    }
    //devuelve todos los trabajadores como lista, usado por la interfaz grafica (JTable)
    public List<Trabajador> obtenerTodos() {
        List<Trabajador> lista = new ArrayList<>();
        Nodo actual = inicio;
        while (actual != null) {
            lista.add(actual.trabajador);
            actual = actual.siguiente;
        }
        return lista;
    }

    //se valida el acceso al tabajador
    public Trabajador validarLoginT(String usuario, String contrasena) {
        Nodo actual = inicio;
        
        while (actual != null) {
             Trabajador t = actual.trabajador;

        if (t.getUsuario() != null && t.getContrasena() != null) {

            if (t.getUsuario().equals(usuario) &&
                t.getContrasena().equals(contrasena)) {
                return t;
            }
        }

        actual = actual.siguiente;
    }

    return null;
}
    
}
