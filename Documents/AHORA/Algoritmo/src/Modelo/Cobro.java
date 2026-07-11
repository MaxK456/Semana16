package Modelo;

public class Cobro {

    public enum Tipo { 
        consumo,servicio,descuento
        
    }

    private Tipo tipo;
    private String descripcion;
    private double monto;

    public Cobro(Tipo tipo, String descripcion, double monto) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.monto = monto;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getMonto() {
        return monto;
    }
    
    
    //cobro o bien descuento 
    public double calculo() {
        if (tipo == Tipo.descuento) {
            return -monto;
        }
        
        return monto;
    }

    @Override
    public String toString() {
        return tipo + " || " + descripcion + " || S/ " + calculo();
                //String.format("%-20s | %-25s | S/ %8.2f",tipo, descripcion, calculo());
    }
}


