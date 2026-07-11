package Vista;

import LOGIN.ArchivoUsuarios;
import LOGIN.ListaUsuario;
import LOGIN.Usuario;
import Modelo.Club;
import Modelo.Socio;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Genera credenciales de acceso para un socio (imagen 3, boton 3). */
public class FrameGenerarCredenciales extends JDialog {

    public FrameGenerarCredenciales(FrameTrabajador padre, Club club, ListaUsuario listaUsuarios) {
        super(padre, "Generar credenciales", true);
        setSize(420, 280);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("GENERAR CREDENCIALES"), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setBackground(Estilo.FONDO);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(20, 24, 20, 24));

        JTextField txtId = Estilo.campoTexto();
        form.add(Estilo.etiqueta("ID del socio"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtId);
        form.add(Box.createVerticalStrut(20));

        JButton btnGenerar = Estilo.botonAzul("Generar credenciales");
        btnGenerar.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(btnGenerar);

        add(form, BorderLayout.CENTER);

        btnGenerar.addActionListener(e -> {
            Socio soc;
            try {
                soc = club.buscarSocio(Integer.parseInt(txtId.getText().trim()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID invalido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (soc == null) {
                JOptionPane.showMessageDialog(this, "Socio no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (soc.getUsuario() == null || soc.getUsuario().equals("")) {
                String usuario = Usuario.generarUsuario(soc.getId());
                String contrasena = Usuario.generarContrasenia();
                soc.setUsuario(usuario);
                soc.setContrasena(contrasena);
                listaUsuarios.registrarCliente(soc);
                ArchivoUsuarios.guardar("usuarios.dat", listaUsuarios);

                JOptionPane.showMessageDialog(this,
                        "----------- CREDENCIALES DE ACCESO --------\nUsuario: " + usuario + "\nContrasenia: " + contrasena,
                        "Credenciales generadas", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "El socio ya cuenta con credenciales\nUsuario: " + soc.getUsuario() + "\nContrasenia: " + soc.getContrasena(),
                        "Ya tiene credenciales", JOptionPane.INFORMATION_MESSAGE);
            }
            dispose();
        });
    }
}
