package Dominio;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private boolean esIntangible;

    public Producto(int id, String nombre, double precio, boolean esIntangible) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.esIntangible = esIntangible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isIntangible() {
        return esIntangible;
    }

    public void setIntangible(boolean esIntangible) {
        this.esIntangible = esIntangible;
    }
}
