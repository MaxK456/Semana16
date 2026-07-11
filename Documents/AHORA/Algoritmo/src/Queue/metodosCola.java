
package Queue;

import Archivos.ArchivoPagos;
import Modelo.Club;
import Modelo.Pago;
import Modelo.Socio;
import Pila.Pila;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class metodosCola {
   private Queue<Pago> Pagos;
   private Pila<String> historialPagos = new Pila<>();
   
   public metodosCola(){
       Pagos=ArchivoPagos.cargar();
   }
    
   //agrega un pago a la cola de espera
    public void agregarPagoCola(Pago p) {
        Pagos.add(p);
        ArchivoPagos.guardar(Pagos);
        System.out.println("pago registrado en cola para: " +p.getNombre());
    }
    
    //procesa pagos del primer socio en la cola
    public void procesarPago(Scanner sc,Club club) {
        if (!Pagos.isEmpty()) { 
        Pago p = Pagos.peek(); 
        System.out.print("Socio: " + p.getNombre() + " (S/ " + p.getMonto() + ") - ¿Confirmar pago? 1.Sí 2.No: ");
        int confirmar = sc.nextInt();
        sc.nextLine(); 
        
        if (confirmar == 1) {
            Pagos.poll(); 
            
            //buscar socio con nombre
            ArchivoPagos.guardar(Pagos);
            Socio soc = club.buscarSocioN(p.getNombre());
            if (soc != null) {
                soc.setPago(true);
                soc.registrarPago(p); 
            }
            historialPagos.push(p.toString()); 
            
            System.out.println("Pago procesado con exito. El ticket fue removido de la cola.");
        } else {
            System.out.println("Operacion cancelada. El pago sigue en la cola.");
        }
        
    } else {
        System.out.println("no hay pagos en cola para procesar.");
    }
}
    //muestra pagos pendientes
    public void mostrarCola() {       
        if (Pagos.isEmpty()) {
            return;
        }
        System.out.println("--------cola de pagos actual---------");
        for (Pago pago : Pagos) {
            System.out.println("Socio: " +pago.getNombre() + " Monto: S/" +pago.getMonto());
        }
    }
    
    public void eliminarCola() {
        if (!Pagos.isEmpty()) {
            Pagos.poll();
        }
    }
    
    public boolean Pagado(String nombre) {
        for (Pago p : Pagos) {
            if(p.getNombre().equalsIgnoreCase(nombre)){
                return false;
            }
        }
        return true;
    }
    
    //elimina un pago especifico de la cola
    public void eliminarPago(String nombre) {
    Queue<Pago> nuevaCola = new LinkedList<>();
    while (!Pagos.isEmpty()) {
        Pago p = Pagos.poll();
        if (!p.getNombre().equalsIgnoreCase(nombre)) {
            nuevaCola.add(p);
        }
    }

    Pagos = nuevaCola;
    ArchivoPagos.guardar(Pagos);
    }
    
    //muestra el historial de pagos
    public void mostrarHistorial() {
        System.out.println("\n---------- historial de pagos --------------");
        historialPagos.imprimirPila();
    }
    
    //devuelve la cola de pagos como lista, usado por la interfaz grafica (JTable)
    public java.util.List<Pago> obtenerColaLista() {
        return new java.util.ArrayList<>(Pagos);
    }
    
    //devuelve el historial de pagos como lista, usado por la interfaz grafica (JTable)
    public java.util.List<String> obtenerHistorialLista() {
        return historialPagos.aLista();
    }
    
    //procesa el pago de un socio en especifico dentro de la cola, sin usar Scanner (para la interfaz grafica)
    public boolean procesarPagoDe(String nombreSocio, Club club) {
        java.util.List<Pago> restante = new java.util.ArrayList<>();
        boolean encontrado = false;
        Pago procesado = null;
        while (!Pagos.isEmpty()) {
            Pago p = Pagos.poll();
            if (!encontrado && p.getNombre().equalsIgnoreCase(nombreSocio)) {
                encontrado = true;
                procesado = p;
            } else {
                restante.add(p);
            }
        }
        Pagos.addAll(restante);
        if (encontrado) {
            ArchivoPagos.guardar(Pagos);
            Socio soc = club.buscarSocioN(procesado.getNombre());
            if (soc != null) {
                soc.setPago(true);
                soc.registrarPago(procesado);
            }
            historialPagos.push(procesado.toString());
        }
        return encontrado;
    }
}