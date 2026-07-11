package Modelo;

import Modelo.Pago;
import Modelo.Membresia;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Socio implements Serializable{
    private static final long serialVersionUID=1L;
    
    private int id;
    private String nombre;
    private Membresia membresia;
    private List<Pago> pagos;
    private LocalDate fecha;
    private int renovaciones;
    private Membresia cambioPendiente;
    private double deuda;
    private boolean pago;
    private String usuario;
    private String contrasena;
    private String estado;
    
    //los datos del socio
    public Socio(int id, String nombre, Membresia membresia) {
        this.id = id;
        this.nombre = nombre;
        this.membresia = membresia;
        this.pagos = new ArrayList<>();
        this.fecha = LocalDate.now();
        pago=false;
        
        renovaciones = 0;

        cambioPendiente = null;
        
        deuda=0;
        
        usuario = "";
        
        contrasena = "";
    }

    public boolean isPago() {
        return pago;
    }
    
    public int getId() { 
        return id;
    }
    
    public String getNombre() {
        return nombre; 
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public Membresia getMembresia(){
        return membresia;
    }
    
    public Membresia getCambioPendiente() {
    return cambioPendiente;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void registrarPago(Pago p) {
        pagos.add(p);
    }
    
    //devuelve la lista de pagos (copia), usado por la interfaz grafica (JTable)
    public List<Pago> getPagos() {
        return new ArrayList<>(pagos);
    }
    //calcula el total de pagos del socio
    public double total(){
        double t=0;
        if(this.pagos!=null){
        for (Pago p : this.pagos) {
            t+=p.getMonto();
        }   
        }
        return t;
    }

    public void mostrar() {
        if (pagos.isEmpty()) {
        System.out.println("No hay pagos registrados");
        return;
        }
        for (Pago pago : pagos) {
            System.out.println(pago);
        }
    }
    public double getDeuda(){
    return deuda;
    }
    public void setDeuda(double deuda){
    this.deuda=deuda;
    }

    public long antiguedad() {
        return ChronoUnit.DAYS.between(fecha, LocalDate.now());
    }

    public void cambiarm(Membresia nueva) { 
        this.membresia = nueva;
    }

    public void renovarm() {
        this.renovaciones++;
        System.out.println("Membresia renovada. Total renovaciones: " + renovaciones);
    }
    
    public void solicitarc(Membresia nueva) {
    this.cambioPendiente = nueva;
    }
    
    //cambio de membresia
    public void aplicar() {
        if (cambioPendiente != null) {
            this.membresia = cambioPendiente;
            cambioPendiente = null;
        }
    }
    
    
    @Override
    public String toString() {
        return String.format("ID: %08d | Nombre: %s | %s | Antiguedad: %d dias", 
                id, nombre, membresia, antiguedad());
    }
  
}