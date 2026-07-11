package Vista;

import Modelo.Pago;
import Queue.metodosCola;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** Muestra la cola de pagos pendientes (equivalente a metodosCola.mostrarCola()). */
public class FrameColaPagos extends JDialog {

    public FrameColaPagos(Frame padre, metodosCola mcola) {
        super(padre, "Cola de pagos", true);
        setSize(480, 420);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("COLA DE PAGOS"), BorderLayout.NORTH);

        DefaultTableModel modelo = Estilo.modeloNoEditable(new String[]{"Socio", "Monto"});
        List<Pago> cola = mcola.obtenerColaLista();
        for (Pago p : cola) {
            modelo.addRow(new Object[]{p.getNombre(), String.format("S/ %.2f", p.getMonto())});
        }
        JTable tabla = new JTable(modelo);
        add(Estilo.crearTabla(tabla), BorderLayout.CENTER);
        add(Estilo.crearBarraEstado(cola.isEmpty() ? "No hay pagos en cola" : "Total en cola: " + cola.size()), BorderLayout.SOUTH);
    }
}
