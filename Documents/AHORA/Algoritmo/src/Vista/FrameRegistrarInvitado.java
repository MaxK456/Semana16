package Vista;

import Modelo.Club;
import Modelo.Invitado;
import Modelo.Socio;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Registra un invitado (imagen 4, boton 4). Equivalente a Sistema.menuCliente caso 4. */
public class FrameRegistrarInvitado extends JDialog {

    public FrameRegistrarInvitado(FrameCliente padre, Club club, Socio socioLogueado) {
        super(padre, "Registrar invitado", true);
        setSize(420, 380);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("REGISTRO DE INVITADO"), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setBackground(Estilo.FONDO);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(20, 24, 20, 24));

        JTextField txtIdInv = Estilo.campoTexto();
        JTextField txtNombre = Estilo.campoTexto();
        JTextField txtIdSocio = Estilo.campoTexto();
        txtIdSocio.setText(String.format("%08d", socioLogueado.getId()));

        form.add(Estilo.etiqueta("ID del invitado (8 digitos)"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtIdInv);
        form.add(Box.createVerticalStrut(14));
        form.add(Estilo.etiqueta("Nombre del invitado"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtNombre);
        form.add(Box.createVerticalStrut(14));
        form.add(Estilo.etiqueta("ID del socio responsable"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtIdSocio);
        form.add(Box.createVerticalStrut(20));

        JButton btnRegistrar = Estilo.botonAzul("Registrar invitado");
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(btnRegistrar);

        add(form, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> {
            String idInv = txtIdInv.getText().trim();
            if (!idInv.matches("\\d{8}")) {
                JOptionPane.showMessageDialog(this, "Hacer uso de 8 digitos", "ID invalido", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (club.buscarInvitado(idInv) != null) {
                JOptionPane.showMessageDialog(this, "Ingrese otro id, el invitado ya se encuentra registrado.", "ID duplicado", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nombreInv = txtNombre.getText().trim();
            if (nombreInv.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String idSocioStr = txtIdSocio.getText().trim();
            int idSocioValidador;
            try {
                idSocioValidador = Integer.parseInt(idSocioStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID de socio invalido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (club.buscarSocio(idSocioValidador) != null) {
                Invitado nuevoInvitado = new Invitado();
                nuevoInvitado.setIdInvitado(idInv);
                nuevoInvitado.setNombre(nombreInv);
                nuevoInvitado.setIdSocioResponsable(String.valueOf(idSocioValidador));
                nuevoInvitado.setConsumoIndividual(0.0);
                club.agregarInvitado(nuevoInvitado);
                JOptionPane.showMessageDialog(this,
                        "¡Su invitado ha sido registrado de manera correcta e independiente!",
                        "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "El invitado no fue registrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
