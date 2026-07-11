package Vista;

import Listas.ListaTrabajador;
import Modelo.Trabajador;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** Lista los trabajadores registrados (equivalente a ListaTrabajador.mostrar()). */
public class FrameListarTrabajadores extends JDialog {

    public FrameListarTrabajadores(Frame padre, ListaTrabajador listaTrabajadores) {
        super(padre, "Listar trabajadores", true);
        setSize(680, 420);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("TRABAJADORES REGISTRADOS"), BorderLayout.NORTH);

        DefaultTableModel modelo = Estilo.modeloNoEditable(new String[]{"ID", "Nombre", "Telefono", "Cargo", "Correo", "Usuario"});
        List<Trabajador> lista = listaTrabajadores.obtenerTodos();
        for (Trabajador t : lista) {
            modelo.addRow(new Object[]{
                String.format("%08d", t.getId()), t.getNombre(), t.getTelefono(),
                t.getCargo(), t.getCorreo(), t.getUsuario()
            });
        }
        JTable tabla = new JTable(modelo);
        add(Estilo.crearTabla(tabla), BorderLayout.CENTER);
        add(Estilo.crearBarraEstado(lista.isEmpty() ? "No hay trabajadores registrados." : "Total: " + lista.size()), BorderLayout.SOUTH);
    }
}
