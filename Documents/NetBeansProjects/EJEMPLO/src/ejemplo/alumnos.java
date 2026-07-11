
package ejemplo;

public class alumnos {
   
   
       private String numero_solicitud;
       private String fecha;
       private String nombre_cliente;
       private String direccion;
       private double monto_total;
       private  String estado;

    public alumnos(String numero_solicitud, String fecha, String nombre_cliente, String direccion, double monto_total, String estado) {
        this.numero_solicitud = numero_solicitud;
        this.fecha = fecha;
        this.nombre_cliente = nombre_cliente;
        this.direccion = direccion;
        this.monto_total = monto_total;
        this.estado = estado;
    }

    public String getNumero_solicitud() {
        return numero_solicitud;
    }

    public void setNumero_solicitud(String numero_solicitud) {
        this.numero_solicitud = numero_solicitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(double monto_total) {
        this.monto_total = monto_total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
     
      
   
    
    
}

class detalle{
    














}