package Vista;

import Modelo.Club;
import Modelo.Invitado;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** Lista los invitados registrados (equivalente a Club.listarInvitados()). */
public class FrameListarInvitados extends JDialog {

    public FrameListarInvitados(Frame padre, Club club) {
        super(padre, "Listar invitados", true);
        setSize(680, 420);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("INVITADOS REGISTRADOS"), BorderLayout.NORTH);

        DefaultTableModel modelo = Estilo.modeloNoEditable(new String[]{"ID Invitado", "Nombre", "ID Socio responsable", "Consumo"});
        List<Invitado> lista = club.getInvitadosLista();
        for (Invitado inv : lista) {
            modelo.addRow(new Object[]{
                inv.getIdInvitado(), inv.getNombre(), inv.getIdSocioResponsable(),
                String.format("S/ %.2f", inv.getConsumoIndividual())
            });
        }
        JTable tabla = new JTable(modelo);
        add(Estilo.crearTabla(tabla), BorderLayout.CENTER);
        add(Estilo.crearBarraEstado(lista.isEmpty() ? "No hay invitados registrados en el club." : "Total: " + lista.size()), BorderLayout.SOUTH);
    }
}
