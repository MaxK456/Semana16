

package Principal;
import Archivos.ArchivoSocios;
import Archivos.ArchivoTrabajador;
import LOGIN.ListaUsuario;
import LOGIN.Usuario;
import Queue.metodosCola;
import Pila.CajaCobro;
import Modelo.Club;
import Modelo.Cobro;
import Modelo.Membresia;
import Menu.Menu;
import Menu.Metodos;
import Modelo.Pago;
import Pila.Pila;
import Modelo.Socio;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import LOGIN.ArchivoUsuarios;
import Listas.ListaTrabajador;
import static Menu.Metodos.leerID;
import Modelo.Trabajador;

public class Sistema {
    
    //valida el acceso al administrador
    private static boolean loginAdministrador(Scanner sc){
        final String User="admin";
        final String Contra="12345";
        int opcion;
        do{
            System.out.println("-------LOGIN ADMINISTRADOR----------");
            System.out.println("1. Ingresar");
            System.out.println("2. Salir");
            System.out.println("Opcion: ");
            opcion=sc.nextInt();
            sc.nextLine();
            if(opcion==2){
                return false;
            }
            System.out.println("------------------");
            System.out.println("Usuario");
            String usuario=sc.nextLine();
            System.out.println("Contrasenia");
            String clave=sc.nextLine();
            if(usuario.equals(User)&&clave.equals(Contra)){
                System.out.println("Acceso concedido");
            return true;
}else{
        System.out.println("Usuario o contrasenia incorrectos");
    }
    }while(true);
    }
    
    //metodo principal inicia y carga archivos
    public static void main(String[] args) {
        metodosCola mcola = new metodosCola();
        Scanner sc = new Scanner(System.in);
        Club club = new Club(); // ya carga socios.dat e invitados.dat internamente
        ListaUsuario listaUsuarios = ArchivoUsuarios.cargar("usuarios.dat");
        ListaTrabajador listaTrabajadores = ArchivoTrabajador.cargarT("trabajadores.dat");
        int rol;
        
        do {
            Menu.rol();
            System.out.print("Seleccione rol: ");
            rol = sc.nextInt();
            
            switch (rol) {
                case 1:
                    if(loginAdministrador(sc)){
                    menuAdministrador(sc, club, mcola, listaTrabajadores);
                    }
                    break;
                
                case 2:
                    Trabajador t = Metodos.loginTrabajador(sc, listaTrabajadores);
                    if(t!=null){
                        menuTrabajador(sc, club,mcola,listaUsuarios);
                    }
                    break;
                
                case 3:
                    Usuario u = Metodos.loginCliente(sc, listaUsuarios);
                    if (u != null) {
                        menuCliente(sc, club, mcola);
                    }
                    break;
                
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                    
                default:
                    System.out.println("Opcion invalida");
            }
        } while (rol != 4);
    }
    
    
    //gestiona el cobro de caja, sirve tanto para un socio como para un invitado
    //cada caja (CajaCobro) tiene su propio historial y total, por lo que nunca se mezclan
    private static void procesarCaja(Scanner sc, CajaCobro caja, metodosCola mcola) {
        int op;
        do {
            System.out.println("\n" + (caja.esSocio() ? "Socio: " : "Invitado: ") + caja.getNombreCliente());
            Menu.caja();
            op = sc.nextInt(); 
            sc.nextLine();
            
            switch (op) {
                case 1:
                    registrar(sc, caja, Cobro.Tipo.consumo);
                    break;
                    
                 case 2:
                     registrar(sc, caja, Cobro.Tipo.servicio);
                     break;
                     
                 case 3:
                     registrar(sc, caja, Cobro.Tipo.descuento);
                     break;
                     
                case 4: 
                    caja.deshacer(); 
                    break;
                    
                case 5: 
                    caja.rehacer(); 
                    break;
                    
                case 6: 
                    caja.mostrar();
                    break;
                    
                case 7:
                    double total = caja.totalActual();
                    caja.confirmar();
                    // solo los socios entran a la cola de pagos/lista de espera del club
                    if (total > 0 && caja.esSocio()) {
                        Pago pago = new Pago(total, caja.getNombreCliente());
                        mcola.agregarPagoCola(pago);
                    }
                    op = 8;
                    break;
            }
        } while (op != 8);
    }
    
    //registra un cobro en la caja
    private static void registrar(Scanner sc, CajaCobro caja, Cobro.Tipo tipo) {
        System.out.print("Descripcion: ");
        String descripcion = sc.nextLine();
        System.out.print("Monto: "); 
        double monto = sc.nextDouble();
        sc.nextLine();
        Cobro cobro = new Cobro(tipo, descripcion, monto);
        caja.ejecutar(cobro);
    }
    
    
   // menu administrador
    public static void menuAdministrador(Scanner sc, Club club, metodosCola mcola, ListaTrabajador listaTrabajadores) {
        int op;
        do {
            Menu.administrador();
            op = sc.nextInt();
            
            switch (op) {
                case 1:
                    club.listarSocios();
                    break;
                case 2:
                    System.out.print("ID: ");
                    Socio s = club.buscarSocio(sc.nextInt());
                    if (s != null){
                        System.out.println("Total: " + s.total());
                    } 
                    break;
                case 3:
                    club.ordenarSociosPorId();
                    club.listarSocios();
               
                       break;
                case 4:
                    mcola.mostrarCola();
                    break;
                case 5:
                    mcola.mostrarHistorial();
                    break;
                case 6:
                    club.mostrarEstadisticas();
                    break;
                case 7:
                    System.out.println("\n--- REGISTRO DE NUEVO TRABAJADOR ---");
                    Trabajador nuevoT = Metodos.registrarTrabajador(sc);
                    nuevoT.setUsuario(Usuario.generarTrabajador(nuevoT.getId()));
                    nuevoT.setContrasena(Usuario.generarContrasenia());
                    listaTrabajadores.insertar(nuevoT);
                    ArchivoTrabajador.guardarT("trabajadores.dat", listaTrabajadores);
                    System.out.println("Trabajador registrado exitosamente.");
                    System.out.println("Usuario asignado: " + nuevoT.getUsuario());
                    System.out.println("Contrasenia asignada: " + nuevoT.getContrasena());
                    break;
                case 8:
                    listaTrabajadores.mostrar();
                    break;
                    
                case 9:
                club.listarInvitados();
                break;
                case 10:
                    Metodos.eliminarRegistroMenu(sc, club, listaTrabajadores);
                    break;
                case 11:
                    break;
            }
        } while (op != 11);
    }
    
    
    
    // menu trabajador
    public static void menuTrabajador(Scanner sc, Club club, metodosCola mcola, ListaUsuario listaUsuarios) {
        int op;
        do {
            Menu.trabajador();
            op = sc.nextInt();
            switch (op) {
                
              case 1: 
                    System.out.println("--------------- REGISTRO SOCIO ---------------");
                    int id;
                    
                    do {
                        System.out.print("Ingrese ID (8 digitos): ");
                        id = sc.nextInt();
                        sc.nextLine(); 
                        // valida llos 8 digitos
                        if (id < 10000000 || id > 99999999) {
                            System.out.println(" Intente nuevamente.");
                        } 
                        // 8 digitos o duplicado
                        else if (club.buscarSocio(id) != null) {
                            System.out.println("Ingrese otro id, ya se encuentra registrado.");
                        }
                     
                    } while ((id < 10000000 || id > 99999999) || club.buscarSocio(id) != null);
                    
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    Membresia membresia = Metodos.categorias(sc); 
                    Socio nuevoSocio = new Socio(id, nombre, membresia);
                    club.agregarSocio(nuevoSocio); 
                    mcola.agregarPagoCola(new Pago(membresia.getCosto(), nuevoSocio.getNombre())); 
                    System.out.println("socio registrado en el sistema.");
                    System.out.println("socio enviado a la lista de espera.");
                    break;
                
            case 2: 
           System.out.println("--------------- PROCESAR PAGO ---------------");
           System.out.print("ID socio: ");
           int idBusqueda = sc.nextInt();
           sc.nextLine();
           Socio s = club.buscarSocio(idBusqueda);
           if (s != null) {
           System.out.println("Socio: " + s.getNombre());
           System.out.println("Ingrese correo para enviar la boleta: ");
           String correo=sc.nextLine();
           System.out.println("Monto a pagar: S/ " + s.getMembresia().getCosto());
           System.out.println("Boleta procesada de manera conforme y notififcada a: " +correo);
             double monto=s.getMembresia().getCosto();
           Pago pago = new Pago(monto,s.getNombre());
                        mcola.procesarPago(sc,club);
                        ArchivoSocios.guardar("socios.dat", club.getSocios());
                        System.out.println("Pago procesado ");
        } else {
           System.out.println("Socio no encontrado.");
    }
    break;

            case 3: 
                System.out.println("---------GENERAR CREDENCIALES-----------");
                System.out.println("ID: ");
                int idConsulta=sc.nextInt();
                sc.nextLine();
                Socio soc=club.buscarSocio(idConsulta);
                if(soc!=null){
                    if(soc.getUsuario() == null || soc.getUsuario().equals("")){
                    String usuario=Usuario.generarUsuario(soc.getId());
                    String contrasena=Usuario.generarContrasenia();
                    soc.setUsuario(usuario);
                    soc.setContrasena(contrasena);
                            
                    System.out.println("----------- CREDENCIALES DE ACCESO --------");
                    System.out.println("Usuario: " + usuario);
                    System.out.println("Contrasenia: " + contrasena);
                            
                    listaUsuarios.registrarCliente(soc);
                    ArchivoUsuarios.guardar("usuarios.dat", listaUsuarios);
                    
                    }else{
                        System.out.println("El socio ya cuenta con credenciales");
                        System.out.println("Usuario: " +soc.getUsuario());
                        System.out.println("Contrasenia: " +soc.getContrasena());
                    }
                }else{
                    System.out.println("Socio no encontrado");
                }
                break;


                case 4: 
                    mcola.mostrarCola();
                    break;
                        
                case 5: 
                    club.listaEspera(mcola);
                    break;

                case 6:
                    System.out.println("--------- COBRO CON CAJA ---------");
                    System.out.println("1. Caja de Socio");
                    System.out.println("2. Caja de Invitado");
                    System.out.print("Seleccione: ");
                    int tipoCaja = sc.nextInt();
                    sc.nextLine();

                    if (tipoCaja == 1) {
                        System.out.print("Ingrese ID del socio: ");
                        int idCaja = Metodos.leerID(sc);
                        sc.nextLine();
                        Socio socioCaja = club.buscarSocio(idCaja);
                        if (socioCaja != null) {
                            CajaCobro cajaSocio = new CajaCobro(socioCaja);
                            procesarCaja(sc, cajaSocio, mcola);
                            ArchivoSocios.guardar("socios.dat", club.getSocios());
                        } else {
                            System.out.println("Socio no encontrado.");
                        }
                    } else if (tipoCaja == 2) {
                        System.out.print("Ingrese ID del invitado: ");
                        int idInvCaja = Metodos.leerID(sc);
                        sc.nextLine();
                        Modelo.Invitado invCaja = club.buscarInvitado(String.valueOf(idInvCaja));
                        if (invCaja != null) {
                            CajaCobro cajaInvitado = new CajaCobro(invCaja, club);
                            procesarCaja(sc, cajaInvitado, mcola);
                        } else {
                            System.out.println("Invitado no encontrado. Registrelo primero.");
                        }
                    } else {
                        System.out.println("Opcion no valida.");
                    }
                    break;
               
    /*case 7:
    System.out.println("\n--------------- Registro de invitado ---------------");

    int idInvNumerico;
    do {
        idInvNumerico = Metodos.leerID(sc);
        if (club.buscarInvitado(String.valueOf(idInvNumerico)) != null) {
            System.out.println("Ingrese otro id, el invitado ya se encuentra registrado.");
        }
    } while (club.buscarInvitado(String.valueOf(idInvNumerico)) != null);
    sc.nextLine();

    System.out.print("Nombre del Invitado: ");
    String nombreInv = sc.nextLine();

    System.out.print("ID del socio responsable: ");
    int idSocioValidador = Metodos.leerID(sc);
    sc.nextLine();

    if (club.buscarSocio(idSocioValidador) != null) {

        Modelo.Invitado nuevoInvitado = new Modelo.Invitado();
        nuevoInvitado.setIdInvitado(String.valueOf(idInvNumerico));
        nuevoInvitado.setNombre(nombreInv);
        nuevoInvitado.setIdSocioResponsable(String.valueOf(idSocioValidador));
        nuevoInvitado.setConsumoIndividual(0.0);

        club.agregarInvitado(nuevoInvitado);

        System.out.println("Invitado registrado correctamente.");
    } else {
        System.out.println("Socio no existe. No se registró invitado.");
    }
    break;
*/


case 7:
    System.out.print("ID a corregir: ");
    int idEditar = sc.nextInt();
    sc.nextLine();

    Socio socioEditar = club.buscarSocio(idEditar);

    if (socioEditar != null) {
        System.out.println("Nombre actual: " + socioEditar.getNombre());
        System.out.print("Ingrese nuevo nombre: ");
        String nuevoNom = sc.nextLine();

        club.corregirNombreSocio(idEditar, nuevoNom);
        ArchivoSocios.guardar("socios.dat", club.getSocios());

        System.out.println("Nombre actualizado con éxito.");
    } else {
        System.out.println("Socio no encontrado.");
    }
    break;


case 8:
    System.out.print("ID a eliminar: ");
    int idEliminar = sc.nextInt();
    sc.nextLine();

    Socio socioEliminar = club.buscarSocio(idEliminar);

    if (socioEliminar != null) {
        System.out.println("Socio: " + socioEliminar.getNombre());
        System.out.print("¿Seguro que desea eliminarlo? 1.Si 2.No: ");
        int seguro = sc.nextInt();
        sc.nextLine();

        if (seguro == 1) {
            club.eliminarSocio(idEliminar);
            mcola.eliminarPago(socioEliminar.getNombre());
            ArchivoSocios.guardar("socios.dat", club.getSocios());

            System.out.println("Socio eliminado correctamente.");
        } else {
            System.out.println("Operación cancelada.");
        }
    } else {
        System.out.println("Socio no encontrado.");
    }
    break;
    
    case 9:
    System.out.println("Saliendo del menú de trabajador...");
    break;
}
} while (op != 10);

 }
    //menu cliente
    public static void menuCliente(Scanner sc, Club club, metodosCola mcola) {
        int op;
        do {
            Menu.cliente();
            op = sc.nextInt();
            sc.nextLine(); 
            
            switch (op) {
                case 1:
                    System.out.print("ID: ");
                    int codigo = Metodos.leerID(sc);
                    Socio socio = club.buscarSocio(codigo);
                    if (socio != null){
                        System.out.println("Total: " + socio.total());
                    } else {
                        System.out.println("No encontrado");
                    }
                    break;
                
                case 2:
                    System.out.print("ID: ");
                    int id = Metodos.leerID(sc);
                    Socio soc = club.buscarSocio(id);
                    if (soc != null) {
                        Membresia nueva = Metodos.categorias(sc);
                        System.out.println("cambiar membresia: " + nueva);
                        System.out.print("Confirmar solicitud? : 1.Si 2.No: ");
                        int solicitud = sc.nextInt();
                        if (solicitud == 1) {
                            soc.cambiarm(nueva);
                            double monto = nueva.getCosto();
                            Pago p = new Pago(monto,soc.getNombre());
                            soc.registrarPago(p);
                            mcola.agregarPagoCola(p);
                            System.out.println("Membresia actualizada y pago registrado: S/ " + monto);
                        }
                    } else {
                        System.out.println("No encontrado");
                    }
                    break;

                case 3:
                    System.out.print("ID: ");
                    int idSocio = sc.nextInt();
                    Socio soci = club.buscarSocio(idSocio);
                    if (soci != null){
                        if(soci.getDeuda() > 0){
                            System.out.println("Tiene deuda.");
                        } else {
                            soci.renovarm();
                            System.out.println("Membresia renovada");
                        }
                    } else {
                        System.out.println("No encontrado");
                    }
                    break;

                case 4: 
                    System.out.println("\n--------------- Registro de invitado ---------------");
                    
                    int idInvNumerico;
                    do {
                        idInvNumerico = Metodos.leerID(sc);
                        if (club.buscarInvitado(String.valueOf(idInvNumerico)) != null) {
                            System.out.println("Ingrese otro id, el invitado ya se encuentra registrado.");
                        }
                    } while (club.buscarInvitado(String.valueOf(idInvNumerico)) != null);
                    sc.nextLine();
                    
                    // si el id es unico ingrese datos
                    System.out.print("Nombre del Invitado: ");
                    String nombreInv = sc.nextLine();

                    System.out.print("Ingresa el id del socio para poder aceptar el registro del invitado: ");
                    int idSocioValidador = Metodos.leerID(sc);
                    sc.nextLine(); 

                    if (club.buscarSocio(idSocioValidador) != null) {
                        Modelo.Invitado nuevoInvitado = new Modelo.Invitado();
                        nuevoInvitado.setIdInvitado(String.valueOf(idInvNumerico));
                        nuevoInvitado.setNombre(nombreInv);
                        nuevoInvitado.setIdSocioResponsable(String.valueOf(idSocioValidador));
                        nuevoInvitado.setConsumoIndividual(0.0);

                        club.agregarInvitado(nuevoInvitado);
                        System.out.println("¡Su invitado ha sido registrado de manera correcta e independiente!");
                    } else {
                        System.out.println(" El invitado no fue registrado.");
                    }
                    break;

                case 5:
                    System.out.println("Saliendo del menu de cliente...");
                    break;
            }
        } while (op != 5);
    }
}
    

