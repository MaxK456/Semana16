package Modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//
public class Pago implements Serializable{
    private static final long serialVersionUID = 4502730633342849069L;
    private double monto;
    private LocalDateTime fechaHora;
    private String nombre;

    public Pago(double monto,String nombre) {
        this.monto = monto;
        this.nombre=nombre;
        this.fechaHora = LocalDateTime.now(); 
    }

    public String getNombre() {
        return nombre;
    }

    public double getMonto() {
        return monto; 
    }
    
    //devuelve la fecha/hora formateada, usado por la interfaz grafica (JTable)
    public String getFechaFormateada() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fechaHora.format(fmt);
    }
    
    //muestra los datos del pago 
    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("Socio:%s | Monto: S/ %.2f | Fecha: %s",nombre, monto, fechaHora.format(fmt));
    }
}