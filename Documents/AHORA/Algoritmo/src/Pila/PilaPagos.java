
package Pila;

import javax.swing.JTextArea;

public class PilaPagos {

    String pagos[] = new String[10];
    int tope = -1;


    public void push(String pago) {

        if (tope < 9) {
            tope++;
            pagos[tope] = pago;
        }
    }

    // Eliminar ultimo pago
    public void pop() {

        if (tope >= 0) {
            tope--;
        }
    }

    // Mostrar pagos en interfaz
    public void mostrar(JTextArea area) {

        area.setText("");

        if (tope == -1) {
            area.append("No hay pagos registrados");
        } else {

            area.append("=== REGISTRO DE PAGOS ===\n\n");

            for (int i = tope; i >= 0; i--) {
                area.append(pagos[i] + "\n");
            }
        }
    }
    public void mostrarEnConsola() {
        if (tope == -1) {
            System.out.println("No hay pagos registrados");
        } else {
            for (int i = tope; i >= 0; i--) {
                System.out.println(pagos[i]);
}
        }
    }
        
        //buscar pago por nombre
        public boolean buscarPago(String nombre){
            if(tope==-1){
                return false;
            }
            for (int i = tope; i >=0; i--) {
                if(pagos[i]!=null&& pagos[i].contains(nombre)){
                    return true;
                }
                
            }
            return false;
    }
}