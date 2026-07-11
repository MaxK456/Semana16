/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package avanceproyecto;
import java.util.Scanner;
/**
 *
 * @author Admin
 */
public class Avanceproyecto {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner lector= new Scanner(System.in);
        
        //Menu principal:
        System.out.println("=======================================");
        System.out.println("        SISTEMA MAKRO PERU");
        System.out.println("=======================================");
        System.out.println("1. Boleta");
        System.out.println("2. Factura");
        System.out.println("3. Opciones Cajero");
        System.out.println("4. Venta TINKA");
        System.out.print("Seleccione opcion: ");
        int opcion = lector.nextInt();
        lector.nextLine();
        
        switch (opcion){
            
            //BOLETA
            case 1:
                
                System.out.print("\nIngrese DNI: ");
                String dni = lector.nextLine();

                String cliente;

                if (dni.equals("5999900")) {
                    cliente = "CLIENTE MAKRO";
                } else {
                    System.out.print("Ingrese nombre completo: ");
                    cliente = lector.nextLine().toUpperCase();
                }

                // VARIABLES
                String detalle = "";
                String producto;

                int contador = 1;
                double total = 0;

                // INGRESAR PRODUCTOS
                while (true) {

                    System.out.print("\nProducto (x para finalizar): ");
                    producto = lector.nextLine();

                    if (producto.equalsIgnoreCase("x")) {
                        break;
                    }

                    System.out.print("Cantidad: ");
                    int cantidad = lector.nextInt();

                    System.out.print("Precio unitario: ");
                    double precio = lector.nextDouble();
                    lector.nextLine();

                    double subtotal = cantidad * precio;
                    total += subtotal;

                    detalle += contador+ "  " + producto.toUpperCase() + "  x" + cantidad + "   S/. " + subtotal+ "\n";

                    contador++;
                }

                // IGV INCLUIDO
                double valorven = total / 1.18;
                double igv = total - valorven;

                //LISTA DE PRODUCTOS ANTES DE PAGAR
                System.out.println("\n---------------------------------------");
                System.out.println("N PRODUCTO: "+(contador - 1));
                System.out.println("---------------------------------------");
                System.out.print(detalle);
                System.out.println("---------------------------------------");
                System.out.println("Total: S/. "+ total);
                System.out.println("---------------------------------------");
                
                System.out.println("=======================================");
                System.out.println("        MEDIO DE PAGO");
                System.out.println("=======================================");
                System.out.println("1. Efectivo");
                System.out.println("2. Tarjetas SIP");
                System.out.println("3. Tarjeta Debito");
                System.out.println("4. Tarjeta Credito");
                System.out.print("Seleccione opcion: ");
                int opcionpago = lector.nextInt();
                lector.nextLine();
                
                double pago = 0;
                double vuelto= 0;
                String metodo="";
                
                switch (opcionpago){
                    
                    //EFECTIVO
                    case 1:
                        metodo="EFECTIVO";
                        
                        System.out.print("Monto recibido: ");
                        pago = lector.nextDouble();
                        
                        while (pago<total){
                            System.out.println("=======================================");
                            System.out.println("Monto menor al total. No se puede pagar. Intente denuevo");
                            System.out.println("=======================================");
                            System.out.println("\nTotal: S/. "+ total);
                            System.out.print("Monto recibido: ");
                            pago = lector.nextDouble();
                        }
                        vuelto = pago - total;
                        break;
                        
                    case 2:
                        System.out.println("=======================================");
                        System.out.println("               TARJETAS SIP");
                        System.out.println("=======================================");
                        System.out.println("1. Debito");
                        System.out.println("2. Credito");
                        System.out.print("Seleccione opcion: ");
                        int opcionsip = lector.nextInt();
                        lector.nextLine();
                        
                        switch (opcionsip){
                            case 1: metodo="TARJETA SIP DEBITO"; break;
                            case 2: metodo="TARJETA SIP CREDITO"; break;
                            default: System.out.println("Opción no válida."); break;
                        }
                        break;
                    
                    case 3: metodo="TAJETA DEBITO"; break;
                    case 4: metodo="TAJETA CREDITO"; break;
                    default: System.out.println("Opción no válida."); break;
                }

                // IMPRESION BOLETA
                System.out.println("\n=======================================");
                System.out.println("            MAKRO PERU S.A.");
                System.out.println("       RUC: 20100070970");
                System.out.println(" Av. Industrial 123 - Lima, Peru");
                System.out.println("=======================================");
                System.out.println("BOLETA DE VENTA ELECTRONICA");
                System.out.println("B001-00015487");
                System.out.println("---------------------------------------");
                System.out.println("FECHA : 05/05/26");
                System.out.println("HORA  : 16:55" );
                System.out.println("CAJA  : 03");
                System.out.println("CAJERO: LUIS");
                System.out.println("---------------------------------------");
                System.out.println("CLIENTE: " + cliente);
                System.out.println("DNI    : " + dni);
                System.out.println("---------------------------------------");
                System.out.print(detalle);
                System.out.println("---------------------------------------");
                System.out.printf("VALOR VENTA : S/. %.2f\n", valorven);
                System.out.printf("IGV (18%%)   : S/. %.2f\n", igv);
                System.out.printf("TOTAL       : S/. %.2f\n", total);
                System.out.println("MEDIO DE PAGO : "+ metodo);
                
                if (metodo.equals("EFECTIVO")){
                    System.out.printf("PAGO        : S/. %.2f\n", pago);
                    System.out.printf("VUELTO      : S/. %.2f\n", vuelto);
                }
                System.out.println("\n=======================================");
                System.out.println("Gracias por su compra.");
                System.out.println("=======================================");
                break;

            //FACTURA
            case 2:
                System.out.print("\nIngrese RUC: ");
                String ruc = lector.nextLine();
                System.out.print("Ingrese Razon Social: ");
                String razonSocial = lector.nextLine().toUpperCase();
                System.out.print("Ingrese Direccion: ");
                String direccion = lector.nextLine().toUpperCase();

                String detalleFactura = "";
                int contFactura = 1;
                double totalFactura = 0;

                while (true) {
                    System.out.print("\nProducto (x para finalizar): ");
                    String prodFactura = lector.nextLine();
                    if (prodFactura.equalsIgnoreCase("x")) break;

                    System.out.print("Cantidad: ");
                    int cantFactura = lector.nextInt();
                    System.out.print("Precio unitario (sin IGV): ");
                    double precioSinIgv = lector.nextDouble();
                    lector.nextLine();

                    double subtotalItem = cantFactura * precioSinIgv;
                    totalFactura += subtotalItem;
                    detalleFactura += contFactura + "  " + prodFactura.toUpperCase() + "  x" + cantFactura + "   S/. " + String.format("%.2f", subtotalItem) + "\n";
                    contFactura++;
                }

                double igvFactura = totalFactura * 0.18;
                double totalPagarFactura = totalFactura + igvFactura;

                System.out.println("\n=======================================");
                System.out.println("        MEDIO DE PAGO (FACTURA)");
                System.out.println("=======================================");
                System.out.println("1. Efectivo");
                System.out.println("2. Transferencia");
                System.out.println("3. Tarjeta Credito/Debito");
                System.out.print("Seleccione opcion: ");
                int pagoFacturaOpc = lector.nextInt();
                lector.nextLine();

                String metodoFactura = (pagoFacturaOpc == 1) ? "EFECTIVO" : (pagoFacturaOpc == 2) ? "TRANSFERENCIA" : "TARJETA";
                double pagoF = 0;
                if (pagoFacturaOpc == 1) {
                    System.out.print("Monto recibido: ");
                    pagoF = lector.nextDouble();
                }

                System.out.println("\n=======================================");
                System.out.println("            MAKRO PERU S.A.");
                System.out.println("      FACTURA ELECTRONICA");
                System.out.println("---------------------------------------");
                System.out.println("RUC          : " + ruc);
                System.out.println("RAZON SOCIAL : " + razonSocial);
                System.out.println("---------------------------------------");
                System.out.print(detalleFactura);
                System.out.println("---------------------------------------");
                System.out.printf("SUBTOTAL : S/. %.2f\n", totalFactura);
                System.out.printf("IGV 18%%  : S/. %.2f\n", igvFactura);
                System.out.printf("TOTAL    : S/. %.2f\n", totalPagarFactura);
                System.out.println("=======================================");
                break;
                
            //OPCIONES CAJERO
            case 3:
                System.out.println("=======================================");
                System.out.println("        OPCIONES CAJERO");
                System.out.println("=======================================");
                System.out.println("1. Recarga / Abono");
                System.out.println("2. Retiro");
                System.out.print("Seleccione opcion: ");
                int opcionCajero = lector.nextInt();
                lector.nextLine();

                switch (opcionCajero) {
                    case 1:
                        System.out.println("=======================================");
                        System.out.println("        RECARGA / ABONO");
                        System.out.println("=======================================");
                        System.out.println("1. Tarjeta Debito (Recarga)");
                        System.out.println("2. Tarjeta Credito (Abono)");
                        System.out.print("Seleccione opcion: ");
                        int tipo = lector.nextInt();
                        lector.nextLine();

                        String op = (tipo == 1) ? "RECARGA" : "ABONO";
                        String tiptarj = (tipo == 1) ? "DEBITO" : "CREDITO";

                        System.out.print("Ingrese monto: S/ ");
                        double mont = lector.nextDouble();
                        lector.nextLine();

                        System.out.println("=======================================");
                        System.out.println("COMPROBANTE DE OPERACION");
                        System.out.println("---------------------------------------");
                        System.out.println("OPERACION : " + op);
                        System.out.println("TARJETA   : " + tiptarj);
                        System.out.printf("MONTO     : S/. %.2f\n", mont);
                        System.out.println("=======================================");
                        break;

                    case 2:
                        // --- LÓGICA DE RETIRO AÑADIDA ---
                        System.out.println("=======================================");
                        System.out.println("           RETIRO DE EFECTIVO");
                        System.out.println("=======================================");
                        
                        System.out.print("Ingrese número de tarjeta (16 dígitos): ");
                        String nroTarjeta = lector.nextLine();
                    if (nroTarjeta.length()!=16) {
                        System.out.println("Numero de Tarjeta Invalido");
                    } else{
                        //continuar con el proceso
                        
                    }
                        System.out.print("Monto a retirar: S/. ");
                        double montoRetiro = lector.nextDouble();
                        lector.nextLine(); // Limpiar buffer

                        if (montoRetiro <= 0) {
                            System.out.println("Monto no válido.");
                        } else {
                            System.out.println("\nProcesando transacción...");
                            System.out.println("Retire su dinero de la bandeja.");

                            // Impresión del Voucher de Retiro
                            System.out.println("\n=======================================");
                            System.out.println("            MAKRO PERU S.A.");
                            System.out.println("       RUC: 20100070970");
                            System.out.println("=======================================");
                            System.out.println("         VOUCHER DE RETIRO");
                            System.out.println("---------------------------------------");
                            System.out.println("FECHA : 05/05/26");
                            System.out.println("HORA  : 16:55");
                            System.out.println("CAJA  : 03   CAJERO: LUIS");
                            System.out.println("---------------------------------------");
                            System.out.println("OPERACION : RETIRO");
                            // Mostrar solo los últimos 4 dígitos por seguridad
                            String oculto = (nroTarjeta.length() > 4) ? "****" + nroTarjeta.substring(nroTarjeta.length()-4) : nroTarjeta;
                            System.out.println("TARJETA   : " + oculto);
                            System.out.printf("MONTO     : S/. %.2f\n", montoRetiro);
                            System.out.println("---------------------------------------");
                            System.out.println("      Operación realizada con éxito");
                            System.out.println("=======================================");
                        }
                        break;

                    default:
                        System.out.println("Opción inválida");
                        break;
                }
                break;
            
            case 4:
                System.out.println("Función Venta TINKA en mantenimiento.");
                break;
                
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }
}

