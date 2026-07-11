package Vista;

import Archivos.ArchivoTrabajador;
import Modelo.Club;
import Listas.ListaTrabajador;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Elimina un trabajador o un invitado por ID (equivalente a Metodos.eliminarRegistroMenu). */
public class FrameEliminarTrabInvitado extends JDialog {

    public FrameEliminarTrabInvitado(FrameAdministrador padre, Club club, ListaTrabajador listaTrabajadores) {
        super(padre, "Eliminar trabajador / invitado", true);
        setSize(420, 300);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("ELIMINAR REGISTRO"), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setBackground(Estilo.FONDO);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(20, 24, 20, 24));

        JRadioButton rbTrabajador = new JRadioButton("Trabajador", true);
        JRadioButton rbInvitado = new JRadioButton("Invitado");
        rbTrabajador.setBackground(Estilo.FONDO);
        rbInvitado.setBackground(Estilo.FONDO);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbTrabajador);
        grupo.add(rbInvitado);

        JPanel radios = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        radios.setBackground(Estilo.FONDO);
        radios.add(rbTrabajador);
        radios.add(Box.createHorizontalStrut(20));
        radios.add(rbInvitado);

        JTextField txtId = Estilo.campoTexto();

        form.add(Estilo.etiqueta("Que desea eliminar:"));
        form.add(Box.createVerticalStrut(6));
        form.add(radios);
        form.add(Box.createVerticalStrut(16));
        form.add(Estilo.etiqueta("ID"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtId);
        form.add(Box.createVerticalStrut(20));

        JButton btnEliminar = Estilo.botonRojo("Eliminar");
        btnEliminar.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(btnEliminar);

        add(form, BorderLayout.CENTER);

        btnEliminar.addActionListener(e -> {
            String idStr = txtId.getText().trim();
            if (idStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (rbTrabajador.isSelected()) {
                try {
                    int idTrab = Integer.parseInt(idStr);
                    listaTrabajadores.eliminar(idTrab);
                    ArchivoTrabajador.guardarT("trabajadores.dat", listaTrabajadores);
                    JOptionPane.showMessageDialog(this, "Proceso de eliminacion finalizado.");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "ID invalido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (!idStr.matches("\\d{8}")) {
                    JOptionPane.showMessageDialog(this, "Hacer uso de 8 digitos", "ID invalido", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (club.eliminarInvitado(idStr)) {
                    JOptionPane.showMessageDialog(this, "Invitado eliminado");
                } else {
                    JOptionPane.showMessageDialog(this, "Error: El ID no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dispose();
            }
            padre.refrescarDespuesDeCambios();
        });
    }
}
