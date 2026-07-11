package Vista;

import Modelo.Club;
import Modelo.Socio;
import Queue.metodosCola;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** Muestra los socios que aun no pagan (equivalente a Club.listaEspera(cola)). */
public class FrameSociosEnEspera extends JDialog {

    public FrameSociosEnEspera(Frame padre, Club club, metodosCola mcola) {
        super(padre, "Socios en espera de pago", true);
        setSize(520, 420);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("SOCIOS EN ESPERA"), BorderLayout.NORTH);

        DefaultTableModel modelo = Estilo.modeloNoEditable(new String[]{"ID", "Nombre"});
        List<Socio> enEspera = club.obtenerSociosEnEspera(mcola);
        for (Socio s : enEspera) {
            modelo.addRow(new Object[]{String.format("%08d", s.getId()), s.getNombre()});
        }
        JTable tabla = new JTable(modelo);
        add(Estilo.crearTabla(tabla), BorderLayout.CENTER);
        add(Estilo.crearBarraEstado(enEspera.isEmpty() ? "No hay socios en espera" : "Total en espera: " + enEspera.size()), BorderLayout.SOUTH);
    }
}
