package Pila;

public class Pila<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;
        Nodo(T dato) { 
            this.dato = dato;
        }
    }

    private Nodo<T> tope;
    private int tamanio;
    
    //pila vacia
    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }
    
    //agrega al inicio de la pila
    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamanio++;
    }

    public T pop() {
        if (isEmpty()){
            return null;
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        tamanio--;
        return dato;
    }

    public T peek() {
        if (isEmpty()) {
           return null; 
        }
        return tope.dato;
        
    }

    public boolean isEmpty() {
        return tope == null;
    }
    
    public boolean isFull(){
        return false ;
    }

    public int tamanio() {
        return tamanio;
    }

    public void vaciar() {
        tope = null;
        tamanio = 0;
    }

    public void mostrar() {
        if (isEmpty()) {
            System.out.println("  (pila vacía)");
            return;
        }
        
        
        Nodo<T> actual = tope;
        int i = 1;
        
        while (actual != null) {
            System.out.println("  [" + i + "] " + actual.dato);
            actual = actual.siguiente;
            i++;
        }
    }
    //devuelve el contenido de la pila como lista (de arriba hacia abajo), usado por la interfaz grafica
    public java.util.List<T> aLista() {
        java.util.List<T> lista = new java.util.ArrayList<>();
        Nodo<T> actual = tope;
        while (actual != null) {
            lista.add(actual.dato);
            actual = actual.siguiente;
        }
        return lista;
    }
    
    public void imprimirPila(){
        Nodo<T> actual=tope;
        if(actual==null){
            System.out.println("El historial de pagos esta vacio");
            return;
        }
        while(actual!=null){
            System.out.println(actual.dato);
            actual=actual.siguiente;
        }
    }
}

