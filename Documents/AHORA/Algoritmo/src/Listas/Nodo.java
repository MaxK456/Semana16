package Listas;

import Modelo.Socio;
import Modelo.Trabajador;
import java.io.Serializable;

//un nodo para almacenar socios y trabajadores
public class Nodo implements Serializable{
    private static final long serialVersionUID = 5638879084899707072L;
    Socio socio;
    Nodo siguiente;
    Nodo anterior;
    
    Trabajador trabajador;

    public Nodo(Socio socio) {
        this.socio = socio;
        this.siguiente = null;
        this.anterior = null;
    }
    
    public Nodo(Trabajador trabajador) {
        this.trabajador = trabajador;
        this.siguiente = null;
        this.anterior = null;
    }
    
    
    
    
    
}
