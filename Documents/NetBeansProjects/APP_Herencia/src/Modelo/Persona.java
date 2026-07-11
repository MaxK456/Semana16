
package Modelo;

public class Persona {
    
    
    // atributos de la persona
    
    private int id;
    private String Nombreper;
    private int edad;
    
    
    // constructores 

    public Persona() {
    }

    public Persona(int id, String Nombreper, int edad) {
        this.id = id;
        this.Nombreper = Nombreper;
        this.edad = edad;
    }
    
    // metodos de encapsulamiento get and set

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreper() {
        return Nombreper;
    }

    public void setNombreper(String Nombreper) {
        this.Nombreper = Nombreper;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    // metodo devuelve datos de la clase persona 

    public String detalle_Persona() {
        return "\nCodigo : "  + id + ", \nNombre : "+ Nombreper + ", \nEdad :" + edad + '}';
    }
    
    
    
    
}
