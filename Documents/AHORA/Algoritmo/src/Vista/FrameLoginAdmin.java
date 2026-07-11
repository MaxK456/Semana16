package Vista;

import Listas.ListaTrabajador;
import Modelo.Club;
import Queue.metodosCola;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Login de administrador. Misma validacion que Sistema.loginAdministrador(): usuario "admin", clave "12345". */
public class FrameLoginAdmin extends JFrame {

    private static final String USER = "admin";
    private static final String CLAVE = "12345";

    public FrameLoginAdmin(FrameRol padre, Club club, metodosCola mcola, ListaTrabajador listaTrabajadores) {
        setTitle("Login Administrador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(380, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("LOGIN ADMINISTRADOR"), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setBackground(Estilo.FONDO);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(24, 30, 24, 30));

        JTextField txtUsuario = Estilo.campoTexto();
        JPasswordField txtClave = Estilo.campoPassword();

        form.add(Estilo.etiqueta("Usuario"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtUsuario);
        form.add(Box.createVerticalStrut(16));
        form.add(Estilo.etiqueta("Contrasenia"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtClave);
        form.add(Box.createVerticalStrut(24));

        JButton btnIngresar = Estilo.botonAzul("Ingresar");
        btnIngresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(btnIngresar);

        add(form, BorderLayout.CENTER);

        btnIngresar.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String clave = new String(txtClave.getPassword());
            if (usuario.equals(USER) && clave.equals(CLAVE)) {
                dispose();
                new FrameAdministrador(padre, club, mcola, listaTrabajadores).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contrasenia incorrectos",
                        "Acceso denegado", JOptionPane.ERROR_MESSAGE);
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                padre.setVisible(true);
            }
        });
    }
}
