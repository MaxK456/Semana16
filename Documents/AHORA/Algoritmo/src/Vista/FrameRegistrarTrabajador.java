package Vista;

import Archivos.ArchivoTrabajador;
import LOGIN.Usuario;
import Listas.ListaTrabajador;
import Modelo.Trabajador;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Formulario para registrar un trabajador nuevo (imagen 2, boton 7). */
public class FrameRegistrarTrabajador extends JDialog {

    public FrameRegistrarTrabajador(FrameAdministrador padre, ListaTrabajador listaTrabajadores) {
        super(padre, "Registrar trabajador", true);
        setSize(420, 440);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("REGISTRO DE TRABAJADOR"), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setBackground(Estilo.FONDO);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(20, 24, 20, 24));

        JTextField txtId = Estilo.campoTexto();
        JTextField txtNombre = Estilo.campoTexto();
        JTextField txtTelefono = Estilo.campoTexto();
        JTextField txtCargo = Estilo.campoTexto();
        JTextField txtCorreo = Estilo.campoTexto();

        String[][] campos = {
            {"ID (8 digitos)", null}, {"Nombre", null}, {"Telefono", null}, {"Cargo", null}, {"Correo", null}
        };
        JTextField[] textFields = {txtId, txtNombre, txtTelefono, txtCargo, txtCorreo};
        for (int i = 0; i < campos.length; i++) {
            form.add(Estilo.etiqueta(campos[i][0]));
            form.add(Box.createVerticalStrut(4));
            form.add(textFields[i]);
            form.add(Box.createVerticalStrut(12));
        }

        JButton btnRegistrar = Estilo.botonAzul("Registrar trabajador");
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(Box.createVerticalStrut(8));
        form.add(btnRegistrar);

        add(form, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> {
            String idStr = txtId.getText().trim();
            if (!idStr.matches("\\d{8}")) {
                JOptionPane.showMessageDialog(this, "Hacer uso de 8 digitos", "ID invalido", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nombre = txtNombre.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String cargo = txtCargo.getText().trim();
            String correo = txtCorreo.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Trabajador nuevoT = new Trabajador(Integer.parseInt(idStr), nombre, telefono, cargo, correo);
            nuevoT.setUsuario(Usuario.generarTrabajador(nuevoT.getId()));
            nuevoT.setContrasena(Usuario.generarContrasenia());
            listaTrabajadores.insertar(nuevoT);
            ArchivoTrabajador.guardarT("trabajadores.dat", listaTrabajadores);

            JOptionPane.showMessageDialog(this,
                    "Trabajador registrado exitosamente.\n" +
                    "Usuario asignado: " + nuevoT.getUsuario() + "\n" +
                    "Contrasenia asignada: " + nuevoT.getContrasena(),
                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
    }
}
