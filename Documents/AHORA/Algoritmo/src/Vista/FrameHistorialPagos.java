package Vista;

import Queue.metodosCola;
import java.awt.*;
import javax.swing.*;

/** Muestra el historial de pagos (equivalente a metodosCola.mostrarHistorial()). */
public class FrameHistorialPagos extends JDialog {

    public FrameHistorialPagos(Frame padre, metodosCola mcola) {
        super(padre, "Historial de pagos", true);
        setSize(520, 420);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("HISTORIAL DE PAGOS"), BorderLayout.NORTH);

        DefaultListModel<String> lista = new DefaultListModel<>();
        for (String linea : mcola.obtenerHistorialLista()) {
            lista.addElement(linea);
        }
        JList<String> jlist = new JList<>(lista);
        jlist.setFont(Estilo.FONT_TABLA);
        jlist.setEnabled(false);
        JScrollPane scroll = new JScrollPane(jlist);
        scroll.setBorder(BorderFactory.createLineBorder(Estilo.BORDE));
        add(scroll, BorderLayout.CENTER);
        add(Estilo.crearBarraEstado(lista.isEmpty() ? "El historial de pagos esta vacio" : "Total: " + lista.size() + " pagos"), BorderLayout.SOUTH);
    }
}
