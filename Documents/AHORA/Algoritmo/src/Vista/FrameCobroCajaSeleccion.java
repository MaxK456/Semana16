package Vista;

import Modelo.Club;
import Modelo.Invitado;
import Modelo.Socio;
import Pila.CajaCobro;
import Queue.metodosCola;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Selecciona si la caja de cobro es para un socio o un invitado (imagen 3, boton 6). */
public class FrameCobroCajaSeleccion extends JDialog {

    public FrameCobroCajaSeleccion(FrameTrabajador padre, Club club, metodosCola mcola) {
        super(padre, "Cobro con caja", true);
        setSize(420, 300);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("COBRO CON CAJA"), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setBackground(Estilo.FONDO);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(20, 24, 20, 24));

        JRadioButton rbSocio = new JRadioButton("Caja de Socio", true);
        JRadioButton rbInvitado = new JRadioButton("Caja de Invitado");
        rbSocio.setBackground(Estilo.FONDO);
        rbInvitado.setBackground(Estilo.FONDO);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbSocio);
        grupo.add(rbInvitado);
        JPanel radios = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        radios.setBackground(Estilo.FONDO);
        radios.add(rbSocio);
        radios.add(Box.createHorizontalStrut(20));
        radios.add(rbInvitado);

        JTextField txtId = Estilo.campoTexto();

        form.add(Estilo.etiqueta("Tipo de caja"));
        form.add(Box.createVerticalStrut(6));
        form.add(radios);
        form.add(Box.createVerticalStrut(16));
        form.add(Estilo.etiqueta("ID del socio / invitado"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtId);
        form.add(Box.createVerticalStrut(20));

        JButton btnAbrir = Estilo.botonAzul("Abrir caja");
        btnAbrir.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(btnAbrir);

        add(form, BorderLayout.CENTER);

        btnAbrir.addActionListener(e -> {
            String idStr = txtId.getText().trim();
            if (rbSocio.isSelected()) {
                try {
                    Socio socioCaja = club.buscarSocio(Integer.parseInt(idStr));
                    if (socioCaja != null) {
                        CajaCobro caja = new CajaCobro(socioCaja);
                        dispose();
                        new FrameCaja(padre, caja, club, mcola, false).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(this, "Socio no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "ID invalido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (!idStr.matches("\\d{8}")) {
                    JOptionPane.showMessageDialog(this, "Hacer uso de 8 digitos", "ID invalido", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Invitado invCaja = club.buscarInvitado(idStr);
                if (invCaja != null) {
                    CajaCobro caja = new CajaCobro(invCaja, club);
                    dispose();
                    new FrameCaja(padre, caja, club, mcola, true).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Invitado no encontrado. Registrelo primero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
