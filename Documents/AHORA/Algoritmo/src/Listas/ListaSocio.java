package Listas;

import Arbol.ArbolSocio;
import Listas.Nodo;
import Modelo.Socio;
import Queue.metodosCola;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
        

public class ListaSocio implements Serializable{
    private static final long serialVersionUID = -4433143318100267181L;
    private Nodo inicio;
    private Nodo fin;

    public ListaSocio() {
        this.inicio = null;
        this.fin = null;
    }  
    
    //inserta un socio a la lista
    
    public void insertar(Socio socio){
        Nodo insertar =new Nodo(socio);
        
        if (inicio==null){
            inicio=insertar;
            fin=insertar;
        }else {
            fin.siguiente=insertar;
            insertar.anterior=fin;
            fin=insertar;
        }
    }
    
    
    //---------------------------
     //buscar socio con arbol 
    public Socio buscar(int id){
        
        ArbolSocio arbol = new ArbolSocio();
        Nodo actual = inicio;
        
        while (actual != null) {
            arbol.insertar(actual.socio);
            actual = actual.siguiente;
        }
        return arbol.buscar(id);
    }
    //--------------------------------------
    public void mostrar() {
        Nodo mostrar = inicio;
        if (mostrar == null) {
            System.out.println("No hay socios registrados");
            return;
        }
        while (mostrar != null) {
            if(mostrar.socio.isPago()){
            System.out.println(mostrar.socio);
            }
            mostrar = mostrar.siguiente;
        }
    }
    
    //devuelve todos los socios como lista, usado por la interfaz grafica (JTable)
    public List<Socio> obtenerTodos() {
        List<Socio> lista = new ArrayList<>();
        Nodo actual = inicio;
        while (actual != null) {
            lista.add(actual.socio);
            actual = actual.siguiente;
        }
        return lista;
    }
    
    //elimina un socio por su id
    public void eliminar(int id){
        if(inicio != null){
            
        // socio al inicio
        if(inicio.socio.getId() == id){
            inicio = inicio.siguiente;
            if(inicio != null){
                inicio.anterior = null;
            }else{
                fin = null;
            }
        }else{
            Nodo eliminar = inicio.siguiente;
            
            while(eliminar != null && eliminar.socio.getId() != id){
                eliminar = eliminar.siguiente;
            }

            if(eliminar != null){
                if(eliminar == fin){
                    fin = eliminar.anterior;
                    fin.siguiente = null;
                }else{
                    //socio al medio
                    eliminar.anterior.siguiente = eliminar.siguiente;
                    eliminar.siguiente.anterior = eliminar.anterior;
                }
            }
        }
        }
    }
    
    //ordena los socios 
    public void ordenar() {
       
        boolean cambio;
        
        if (inicio == null) {
            return;
        }
        
        do {
            cambio = false;
            Nodo ordenar = inicio;
     
            while (ordenar.siguiente != null) {  
                if (ordenar.socio.getId() > ordenar.siguiente.socio.getId()) {
                    Socio soc = ordenar.socio;
                    ordenar.socio = ordenar.siguiente.socio;
                    ordenar.siguiente.socio = soc;
                    cambio = true;
                }
                ordenar = ordenar.siguiente;
            }
        } while (cambio);
        
    }

    
    
    public void Estadistica() {

    if (inicio == null) {
        System.out.println("No hay socios");
        return;
    }

    Nodo actual = inicio;
    Socio mayor = null;
    Socio menor = null;
    
    double maxPago=-1.0;
    double minPago=Double.MAX_VALUE;
    
    while (actual != null) {
        double totalSocio=actual.socio.total();
        if(totalSocio>0){
            if(totalSocio>maxPago){
                maxPago=totalSocio;
                mayor=actual.socio;
            }
            if(totalSocio<minPago){
                minPago=totalSocio;
                menor=actual.socio;
        }
        }
        actual = actual.siguiente;
        }
    if(mayor!=null&&menor!=null){
    System.out.println("Mayor pago: " + mayor.getNombre() + "(S/ " +maxPago+")");
    System.out.println("Menor pago: " + menor.getNombre() + "(S/ " +minPago+")");
    
    }else{
            System.out.println("Aun no hay pagos procesados");
            }
    }
    
    public Socio buscarPorNombre(String nombre) {
    Nodo actual = inicio; 
    while (actual != null) {
        if (actual.socio != null && actual.socio.getNombre().equalsIgnoreCase(nombre)) {
            return actual.socio;
        }
        actual = actual.siguiente;
    }
    return null;
}
    
public void mostrarSociosEnEspera(metodosCola cola) {
    Nodo actual = inicio;
    boolean hayEnEspera = false;
    
    while (actual != null) {
        if (actual.socio != null) {
            if (!cola.Pagado(actual.socio.getNombre())) {
                System.out.println("ID: " + actual.socio.getId() + " | Nombre: " + actual.socio.getNombre());
                hayEnEspera = true;
            }
        }
        actual = actual.siguiente;
    }
    
    if (!hayEnEspera) {
        System.out.println("No hay socios en espera");
    }
}
}


    

