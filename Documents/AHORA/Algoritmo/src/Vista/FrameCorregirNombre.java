package Vista;

import Archivos.ArchivoSocios;
import Modelo.Club;
import Modelo.Socio;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Corrige el nombre de un socio (imagen 3, boton 7). */
public class FrameCorregirNombre extends JDialog {

    public FrameCorregirNombre(FrameTrabajador padre, Club club) {
        super(padre, "Corregir nombre", true);
        setSize(420, 340);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("CORREGIR NOMBRE"), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setBackground(Estilo.FONDO);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(20, 24, 20, 24));

        JTextField txtId = Estilo.campoTexto();
        JLabel lblActual = Estilo.etiqueta(" ");
        JTextField txtNuevo = Estilo.campoTexto();
        txtNuevo.setEnabled(false);

        JButton btnBuscar = Estilo.botonAzul("Buscar");
        JButton btnCorregir = Estilo.botonAzul("Actualizar nombre");
        btnCorregir.setEnabled(false);

        form.add(Estilo.etiqueta("ID a corregir"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtId);
        form.add(Box.createVerticalStrut(10));
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(btnBuscar);
        form.add(Box.createVerticalStrut(12));
        form.add(lblActual);
        form.add(Box.createVerticalStrut(6));
        form.add(Estilo.etiqueta("Ingrese nuevo nombre"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtNuevo);
        form.add(Box.createVerticalStrut(20));
        btnCorregir.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(btnCorregir);

        add(form, BorderLayout.CENTER);

        final int[] idEditar = new int[1];

        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                Socio s = club.buscarSocio(id);
                if (s != null) {
                    idEditar[0] = id;
                    lblActual.setText("Nombre actual: " + s.getNombre());
                    txtNuevo.setEnabled(true);
                    btnCorregir.setEnabled(true);
                } else {
                    lblActual.setText("Socio no encontrado.");
                    btnCorregir.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID invalido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCorregir.addActionListener(e -> {
            String nuevoNombre = txtNuevo.getText().trim();
            if (nuevoNombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            club.corregirNombreSocio(idEditar[0], nuevoNombre);
            ArchivoSocios.guardar("socios.dat", club.getSocios());
            JOptionPane.showMessageDialog(this, "Nombre actualizado con exito.", "Actualizado", JOptionPane.INFORMATION_MESSAGE);
            padre.refrescarDespuesDeCambios();
            dispose();
        });
    }
}
