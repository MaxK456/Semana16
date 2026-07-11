    
package Menu;

public class Menu {
    
    public static void administrador(){
        
        System.out.println("\n ------------- ADMINISTRADOR ---------------");
        System.out.println("1. Listar socios");
        System.out.println("2. Ver total pagado");
        System.out.println("3. Ordenar socios");
        System.out.println("4. Ver socios en espera");
        System.out.println("5. Ver historial pagos");
        System.out.println("6. Estadisticas");
        System.out.println("7. Registrar nuevo trabajador ");
        System.out.println("8. Listar Trabajadores");
        System.out.println("9. Listar Invitados");
        System.out.println("10. Eliminar trabajador o invitado");
        System.out.println("11. Salir");
    }
    
    public static void trabajador(){
        
        
        
        System.out.println("\n ------------- TRABAJADOR ---------------");
        System.out.println("1. Registrar socio");
        System.out.println("2. Procesar pago");
        System.out.println("3. Ver credenciales");
        System.out.println("4. Ver cola pagos");
        System.out.println("5. Ver socios en espera");
        System.out.println("6. Cobro con caja");
        System.out.println("7. Corregir nombre del socio");
        System.out.println("8. Eliminar socio");
        System.out.println("9. Salir");
    }
    
    public static void cliente(){
        
        System.out.println("\n ------------- CLIENTE ---------------");
        System.out.println("1. Ver total pagado");
        System.out.println("2. Cambiar membresia");
        System.out.println("3. Renovar membresia");
        System.out.println("4. Registrar invitado");
        System.out.println("5. Salir");
    }
    
    public static void caja() {
        System.out.println("\n-------------- COBRO DE SERVICIOS -------------");
        System.out.println("1. Registrar consumo");
        System.out.println("2. Registrar servicio");
        System.out.println("3. Aplicar descuento");
        System.out.println("4. Deshacer");
        System.out.println("5. Rehacer");
        System.out.println("6. Mostrar estado");
        System.out.println("7. Confirmar cobro");
        System.out.println("8. Salir");
    } 
    
    public static void rol() {
    System.out.println("\n--------------SISTEMA CLUB ------------");
        System.out.println("1. Administrador");
        System.out.println("2. Trabajador");
        System.out.println("3. Cliente");
        System.out.println("4. Salir");
    }
}
