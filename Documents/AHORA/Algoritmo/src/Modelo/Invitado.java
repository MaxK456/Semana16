
package Modelo;

import java.io.Serializable;

public class Invitado implements Serializable {
    private static final long serialVersionUID = 6121224052758784879L;
    private String idInvitado;
    private String nombre;
    private String idSocioResponsable; // con respecto al socio que lo ha invitado al club
    private double consumoIndividual;

    // constructor sin parametro
    public Invitado(){
    }
    
    //resgitro propio del invitado
    public void registrarConsumo(double monto) {
        if (monto > 0) {
            this.consumoIndividual += monto;
        }
    }
    
    //getter and setter 

    public String getIdInvitado() {
        return idInvitado;
    }

    public void setIdInvitado(String idInvitado) {
        this.idInvitado = idInvitado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdSocioResponsable() {
        return idSocioResponsable;
    }

    public void setIdSocioResponsable(String idSocioResponsable) {
        this.idSocioResponsable = idSocioResponsable;
    }

    public double getConsumoIndividual() {
        return consumoIndividual;
    }

    public void setConsumoIndividual(double consumoIndividual) {
        this.consumoIndividual = consumoIndividual;
    }

}
