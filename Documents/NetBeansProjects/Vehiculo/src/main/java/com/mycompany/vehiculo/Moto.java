
package com.mycompany.vehiculo;

    
public class Moto extends Vehiculo implements IMoto {
    protected int cilindrada;
    protected String tipoMoto;

    public Moto(String marca, String modelo, int anio, int cilindrada, String tipoMoto) {
        super(marca, modelo, anio);
        this.cilindrada = cilindrada;
        this.tipoMoto = tipoMoto;
    }

    @Override
    public void mostrarDetallesMoto() {
        System.out.println("Tipo: Moto | Cilindrada: " + cilindrada + "cc | Estilo: " + tipoMoto);
    }
}
