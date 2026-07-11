
package Menu;

import LOGIN.ListaUsuario;
import LOGIN.Usuario;
import Listas.ListaTrabajador;
import Modelo.Club;
import Modelo.Membresia;
import Modelo.Socio;
import Modelo.Trabajador;
import Queue.metodosCola;
import java.util.Scanner;

public class Metodos {
     
     //se seleccion el tipo de membresia del socio
public static Membresia categorias (Scanner sc) {
    System.out.println("\nSeleccione membresia:");
    System.out.println("1. Basica (150)");
    System.out.println("2. Silver (200)");
    System.out.println("3. Gold (350)");
        int opcion = sc.nextInt();
        Membresia membresia = new Membresia(opcion);
        return membresia;
    }
     
     //valida el id que tenga 8 digitos
     public static int leerID(Scanner sc) {
         String id;
         do {
             System.out.print("Ingrese ID (8 digitos): ");
             id = sc.next();
             if (!id.matches("\\d{8}")){
                 System.out.println("Hacer uso de 8 digitos");
             }
         } while (!id.matches("\\d{8}"));
         return Integer.parseInt(id);
    } 
     
     //registra datos del trabajador
     public static Trabajador registrarTrabajador(Scanner sc) {
         
         int id = leerID(sc); 
         sc.nextLine();
         
         System.out.print("Nombre: ");
         
         String nombre = sc.nextLine();
         
         System.out.print("Telefono: ");
         
         String telefono = sc.nextLine();
         
         System.out.print("Cargo: ");
         
         String cargo = sc.nextLine();
         
         System.out.print("Correo: ");
         
         String correo = sc.nextLine();
         
         return new Trabajador(id, nombre, telefono, cargo, correo);
}
     
    
  
     
     //inicio sesion del cliente
     public static Usuario loginCliente(Scanner sc,ListaUsuario listaUsuarios) {
         sc.nextLine(); 
         System.out.print("Usuario: ");
         String usuario = sc.nextLine();
         
         System.out.print("Contrasenia: ");
         
         String contrasenia = sc.nextLine();
         
         Usuario u =listaUsuarios.validarLogin(usuario, contrasenia);
         
         if (u == null) {
             System.out.println("Credenciales incorrectas");
         }
         
         return u;
     }
     
     //inicia sesion del login del trabajador
     public static Trabajador loginTrabajador(Scanner sc, ListaTrabajador listaTrabajadores) {
         sc.nextLine(); 
         System.out.print("Usuario: ");
         
         String usuario = sc.nextLine();
         System.out.print("Contrasenia: ");
         
         String contrasenia = sc.nextLine();
         
         Trabajador t = listaTrabajadores.validarLoginT(usuario, contrasenia);
         
         if (t == null) {
             
             System.out.println("Credenciales de trabajador incorrectas.");
         
         }
         
         return t;
     }
     
     
     //registra un nuevo invitado
     public static Modelo.Invitado resgistrarInvitado(Scanner sc) {
         System.out.println("Registra el invitado");
        int idInvNum = leerID(sc);
         sc.nextLine();
          System.out.print("Nombre del invitado");
          String nombre =sc.nextLine();
           System.out.print("Socio que esta haciendo la invitacion"); 
           int idSocioNum = leerID(sc);
           sc.nextLine();
           
           //  instancia del contructor
           Modelo.Invitado nuevoInvitado = new Modelo.Invitado();
           nuevoInvitado.setIdInvitado(String.valueOf(idInvNum));
           nuevoInvitado.setNombre(nombre);
           nuevoInvitado.setIdSocioResponsable(String.valueOf(idSocioNum));
           nuevoInvitado.setConsumoIndividual(0.0);
           
           System.out.println("Se ha creado el invitado de manera independiente");
           return nuevoInvitado;
                   
     }
     
        // metodo del registro de consumo del invitado
            public static void registrarConsumo(Scanner sc, Club club) {
        System.out.println("\n--- REGISTRAR CONSUMO ---");
        System.out.println("1. Consumo de Socio");
        System.out.println("2. Consumo de Invitado (Independiente)");
        System.out.print("Seleccione una opcion: ");
        int tipo = sc.nextInt();
        
        if (tipo == 1) {
            System.out.println("Datos del Socio:");
            int idSocio = leerID(sc);
            
            Socio socio = club.buscarSocio(idSocio); 
            if (socio != null) {
                System.out.print("Ingrese el monto del consumo: ");
                double monto = sc.nextDouble();
                socio.setDeuda(socio.getDeuda() + monto); 
                System.out.println("Consumo cargado a la cuenta del Socio.");
            } else {
                System.out.println("Socio no encontrado.");
            }
            
        } else if (tipo == 2) {
            System.out.println("Datos del Invitado:");
            int idInvitado = leerID(sc);
            
            Modelo.Invitado invitado = club.buscarInvitado(String.valueOf(idInvitado));
            if (invitado != null) {
                System.out.print("Ingrese el monto del consumo: ");
                double monto = sc.nextDouble();
                
                invitado.registrarConsumo(monto); 
                System.out.println("Consumo cargado independiente de " + invitado.getNombre());
            } else {
                System.out.println("Invitado no registrado, debes registrar en el menu del trabajador");
            }
        } else {
            System.out.println("Opcion invalida.");
        }
    }
        
    // pregunta si va a eliminar a un trabajador o un invitado
 
    public static void eliminarRegistroMenu(Scanner sc, Modelo.Club club, Listas.ListaTrabajador listaTrabajadores) {
        System.out.print(" Eliminar  trabajador (1) o invitado(2): ");
        int op = sc.nextInt();
        
        if (op == 1) {
            System.out.print("Ingrese id del trabajador: ");
            int idTrab = sc.nextInt();
            listaTrabajadores.eliminar(idTrab);
            Archivos.ArchivoTrabajador.guardarT("trabajadores.dat", listaTrabajadores);
            System.out.println("proceso de eliminacion finalizado.");
            
        } else if (op == 2) {
            System.out.print("Ingrese el id del invitado");
            int idInv = leerID(sc); 
            
            if (club.eliminarInvitado(String.valueOf(idInv))) {
                System.out.println("Invitado eliminado");
            } else {
                System.out.println("Error: El ID no existe.");
            }
        } else {
            System.out.println("Opcion incorrecta");
        }
        
    }   
            
     
     
}
