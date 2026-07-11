package Modelo;

import Archivos.ArchivoInvitados;
import Archivos.ArchivoSocios;
import Listas.ListaSocio;
import Modelo.Socio;
import Modelo.Invitado;
import Queue.metodosCola;
import java.io.*;
import java.util.ArrayList;


public class Club {
    private ListaSocio socios;
    
    private ArrayList<Invitado> invitados;
    
    private final String archivo_persistencia="socios.dat";
    private final String archivo_invitados = "invitados.dat";

    public Club() {
        socios = new ListaSocio();
        invitados = new ArrayList<>();
        cargar();
    }

    public ListaSocio getSocios() {
        return socios;
    }

    public void setSocios(ListaSocio socios) {
        this.socios = socios;
    }
    
    
    private void cargar(){
        socios = ArchivoSocios.cargar(archivo_persistencia);
        invitados = ArchivoInvitados.cargar(archivo_invitados);
    }
    
    //socio
    public void agregarSocio(Socio s) { 
        socios.insertar(s);
        guardar();
    }
    
    
    public void agregarInvitado(Invitado inv) {
        
        Invitado existente = buscarInvitado(inv.getIdInvitado());
        
        if (existente != null) {
 
            // si esiste solo  remueve al nuevo monto
            invitados.remove(existente);
        }
        
        invitados.add(inv);
        guardar();
    }
    
    //guarda a los socios y a los invitados del socio
    private void guardar(){
        ArchivoSocios.guardar(archivo_persistencia, socios);
        ArchivoInvitados.guardar(archivo_invitados, invitados);
    }
    
//arbol metodo buscar(id)
    public Socio buscarSocio(int id) {
        return socios.buscar(id);
    }
//-----------------------------------------
    
    
    public Invitado buscarInvitado(String id) {
        for (Invitado inv : invitados) {
            if (inv.getIdInvitado().equals(id)) {
                return inv; 
            }
        }
        return null; 
    }

    // visualiza a los invitados
    public void listarInvitados() {
        System.out.println("------- registro de invitados ---------");
        if (invitados.isEmpty()) {
            System.out.println("No hay invitados registrados en el club.");
            return;
        }

       
        ArrayList<Invitado> listaOrdenada = new ArrayList<>(invitados);

        // metodo burbuja
        int n = listaOrdenada.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                
                if (listaOrdenada.get(j).getConsumoIndividual() < listaOrdenada.get(j + 1).getConsumoIndividual()) {
                    
                    Invitado temp = listaOrdenada.get(j);
                    listaOrdenada.set(j, listaOrdenada.get(j + 1));
                    listaOrdenada.set(j + 1, temp);
                }
            }
        }

        
        for (Invitado inv : listaOrdenada) {
            System.out.printf("ID Invitado: %s | Nombre: %s | ID Socio que esta invitando: %s | Consumo: S/. %.2f\n",
                    inv.getIdInvitado(), inv.getNombre(), inv.getIdSocioResponsable(), inv.getConsumoIndividual());
        }
    }
        
    
    public void listarSocios() {
       socios.mostrar();
    }

    public void ordenarSociosPorId() {
        socios.ordenar();
    }
  
    public void mostrarEstadisticas() {
        socios.Estadistica();
    }
    
    //corregirNombreSocio con arbol
        
    public boolean corregirNombreSocio(int id, String nuevoNombre) {
    Socio s = buscarSocio(id);
    if (s != null) {
        s.setNombre(nuevoNombre);
        guardar();
        return true;
    }
    return false;
    }
    
    // eliminan  alsocio con arbol 
    public boolean eliminarSocio(int id) {
    Socio s = buscarSocio(id);
    if (s != null) {
        socios.eliminar(id);
        guardar();
        return true;
    }
    return false;
    }
    
    // elimina al invitado
    public boolean eliminarInvitado(String id) {
        Invitado inv = buscarInvitado(id);
        if (inv != null) {
            invitados.remove(inv); 
            guardar();            
            return true;
        }
        return false;
    } 
    
    public void listaEspera(metodosCola cola){
        System.out.println("-------Socios en espera de Pago---------");
        socios.mostrarSociosEnEspera(cola);
    }

    
    //lista de buscatr socio y que funcionara en procesar pago 
    public Socio buscarSocioN(String nombre){
        return socios.buscarPorNombre(nombre);
    }
    
    //devuelve la lista de invitados (copia), usado por la interfaz grafica (JTable)
    public java.util.List<Invitado> getInvitadosLista() {
        return new java.util.ArrayList<>(invitados);
    }
    
    //devuelve los socios que aun no pagan (en espera), usado por la interfaz grafica
    public java.util.List<Socio> obtenerSociosEnEspera(metodosCola cola) {
        java.util.List<Socio> enEspera = new java.util.ArrayList<>();
        for (Socio s : socios.obtenerTodos()) {
            if (!cola.Pagado(s.getNombre())) {
                enEspera.add(s);
            }
        }
        return enEspera;
    }
    
    public void auditoriaArchivoFisico() {
    File archivo = new File(archivo_persistencia);
    if (!archivo.exists()) {
        System.out.println("El archivo fisico aun no ha sido creado.");
        return;
    }

    System.out.println("------ AUDITORÍA EN DISCO ------");
    System.out.println("Ruta: " + archivo.getAbsolutePath());
    System.out.println("Tamanio: " + archivo.length() + " bytes");

        System.out.println("Socios detectados:");
        socios.mostrar();
       }
}