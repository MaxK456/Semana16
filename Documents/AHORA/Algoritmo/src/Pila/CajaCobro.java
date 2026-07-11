package Pila;

import Pila.Pila;
import Modelo.Cobro;
import Modelo.Pago;
import Modelo.Socio;
import Modelo.Invitado;
import Modelo.Club;

public class CajaCobro {

    // solo uno de los dos estara activo segun para quien se abrio la caja
    private Socio socio;
    private Invitado invitado;
    private Club club; // necesario para persistir el consumo del invitado

    private Pila<Cobro> historial;
    private Pila<Cobro> rehacer;
    private double total;

    //caja de cobro para un socio
    public CajaCobro(Socio socio) {
        this.socio = socio;
        this.historial = new Pila<>();
        this.rehacer = new Pila<>();
        this.total = 0;
    }

    //caja de cobro para un invitado (totalmente independiente de la del socio)
    public CajaCobro(Invitado invitado, Club club) {
        this.invitado = invitado;
        this.club = club;
        this.historial = new Pila<>();
        this.rehacer = new Pila<>();
        this.total = 0;
    }

    public boolean esSocio() {
        return socio != null;
    }

    public String getNombreCliente() {
        return socio != null ? socio.getNombre() : invitado.getNombre();
    }

    //resgistro nuevo cobro en la caja
    public void ejecutar(Cobro cobro) {
        historial.push(cobro);
        rehacer.vaciar();
        total = total+ cobro.calculo();
        System.out.println("Cobro registrado: " + cobro);
    }

    public void deshacer() {
        if (historial.isEmpty()) {
            System.out.println("No hay acciones para deshacer.");
            return;
        }
        Cobro C = historial.pop();
        rehacer.push(C);
        total = total- C.calculo();
        System.out.println(" Accion eliminada " + C);
    }

    public void rehacer() {
        if (rehacer.isEmpty()) {
            System.out.println(" No hay acciones para rehacer.");
            return;
        }
        Cobro a = rehacer.pop();
        historial.push(a);
        total = total + a.calculo();
        System.out.println("Accion recuperada " + a);
    }

    public double totalActual() {
        return total;
    }
    
    //confirma los cobros acumulados: si es socio genera un Pago, si es invitado suma a su consumo independiente
    public void confirmar() {
        if (historial.isEmpty()) {
            System.out.println(" No hay acciones para confirmar.");
            return;
        }
        
        System.out.println("Ultimo cobro: " + historial.peek());
        
        if (socio != null) {
            Pago pago = new Pago(total, socio.getNombre());
            socio.registrarPago(pago);
        } else if (invitado != null) {
            invitado.registrarConsumo(total);
            if (club != null) {
                club.agregarInvitado(invitado); // persiste en invitados.dat
            }
        }
        
        historial.vaciar();
        rehacer.vaciar();
        System.out.println("Cobro confirmado");
        System.out.println((socio != null ? "Socio: " : "Invitado: ") + getNombreCliente());
        System.out.println("Total: S/ " + total);
        total = 0;
    }

    public void mostrar() {
        System.out.println("\n----- ESTADO DE LA CAJA -----");
        if (historial.isEmpty()) {
            System.out.println("No hay cobros registrados");
            return;
        }
        
        System.out.println((socio != null ? "Socio: " : "Invitado: ") + getNombreCliente());
        
        System.out.println("Ultimo cobro:");
        System.out.println(historial.peek()); 
        System.out.println("\nHistorial:");
        historial.mostrar();
        System.out.println("Total: S/ " + total);
    }
}
