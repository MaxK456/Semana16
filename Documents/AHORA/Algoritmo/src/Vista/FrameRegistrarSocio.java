package Vista;

import Modelo.Club;
import Modelo.Membresia;
import Modelo.Pago;
import Modelo.Socio;
import Queue.metodosCola;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Formulario para registrar un socio nuevo (imagen 3, boton 1). */
public class FrameRegistrarSocio extends JDialog {

    public FrameRegistrarSocio(FrameTrabajador padre, Club club, metodosCola mcola) {
        super(padre, "Registrar socio", true);
        setSize(420, 400);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("REGISTRO DE SOCIO"), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setBackground(Estilo.FONDO);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(20, 24, 20, 24));

        JTextField txtId = Estilo.campoTexto();
        JTextField txtNombre = Estilo.campoTexto();
        JComboBox<String> comboMembresia = new JComboBox<>(new String[]{"1. Basica (S/ 150)", "2. Silver (S/ 200)", "3. Gold (S/ 350)"});
        comboMembresia.setFont(Estilo.FONT_NORMAL);

        form.add(Estilo.etiqueta("ID (8 digitos)"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtId);
        form.add(Box.createVerticalStrut(14));
        form.add(Estilo.etiqueta("Nombre"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtNombre);
        form.add(Box.createVerticalStrut(14));
        form.add(Estilo.etiqueta("Membresia"));
        form.add(Box.createVerticalStrut(4));
        form.add(comboMembresia);
        form.add(Box.createVerticalStrut(20));

        JButton btnRegistrar = Estilo.botonAzul("Registrar socio");
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(btnRegistrar);

        add(form, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> {
            String idStr = txtId.getText().trim();
            if (!idStr.matches("\\d{8}")) {
                JOptionPane.showMessageDialog(this, "Hacer uso de 8 digitos", "ID invalido", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int id = Integer.parseInt(idStr);
            if (club.buscarSocio(id) != null) {
                JOptionPane.showMessageDialog(this, "Ingrese otro id, ya se encuentra registrado.", "ID duplicado", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Membresia membresia = new Membresia(comboMembresia.getSelectedIndex() + 1);
            Socio nuevoSocio = new Socio(id, nombre, membresia);
            club.agregarSocio(nuevoSocio);
            mcola.agregarPagoCola(new Pago(membresia.getCosto(), nuevoSocio.getNombre()));

            JOptionPane.showMessageDialog(this,
                    "Socio registrado en el sistema.\nSocio enviado a la lista de espera.",
                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            padre.refrescarDespuesDeCambios();
            dispose();
        });
    }
}
