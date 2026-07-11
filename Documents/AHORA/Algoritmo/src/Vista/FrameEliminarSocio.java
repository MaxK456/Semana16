package Vista;

import Archivos.ArchivoSocios;
import Modelo.Club;
import Modelo.Socio;
import Queue.metodosCola;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Elimina un socio por su ID (imagen 3, boton 8). */
public class FrameEliminarSocio extends JDialog {

    public FrameEliminarSocio(FrameTrabajador padre, Club club, metodosCola mcola) {
        super(padre, "Eliminar socio", true);
        setSize(400, 260);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("ELIMINAR SOCIO"), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setBackground(Estilo.FONDO);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(20, 24, 20, 24));

        JTextField txtId = Estilo.campoTexto();
        form.add(Estilo.etiqueta("ID a eliminar"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtId);
        form.add(Box.createVerticalStrut(20));

        JButton btnEliminar = Estilo.botonRojo("Eliminar socio");
        btnEliminar.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(btnEliminar);

        add(form, BorderLayout.CENTER);

        btnEliminar.addActionListener(e -> {
            int idEliminar;
            try {
                idEliminar = Integer.parseInt(txtId.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID invalido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Socio socioEliminar = club.buscarSocio(idEliminar);
            if (socioEliminar == null) {
                JOptionPane.showMessageDialog(this, "Socio no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int seguro = JOptionPane.showConfirmDialog(this,
                    "Socio: " + socioEliminar.getNombre() + "\n¿Seguro que desea eliminarlo?",
                    "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);
            if (seguro == JOptionPane.YES_OPTION) {
                club.eliminarSocio(idEliminar);
                mcola.eliminarPago(socioEliminar.getNombre());
                ArchivoSocios.guardar("socios.dat", club.getSocios());
                JOptionPane.showMessageDialog(this, "Socio eliminado correctamente.");
                padre.refrescarDespuesDeCambios();
                dispose();
            }
        });
    }
}
