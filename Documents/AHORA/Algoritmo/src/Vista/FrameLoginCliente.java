package Vista;

import LOGIN.ListaUsuario;
import LOGIN.Usuario;
import Modelo.Club;
import Modelo.Socio;
import Queue.metodosCola;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Login de cliente. Misma validacion que Metodos.loginCliente() -> ListaUsuario.validarLogin(). */
public class FrameLoginCliente extends JFrame {

    public FrameLoginCliente(FrameRol padre, Club club, metodosCola mcola, ListaUsuario listaUsuarios) {
        setTitle("Login Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(380, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("LOGIN CLIENTE"), BorderLayout.NORTH);

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
            Usuario u = listaUsuarios.validarLogin(usuario, clave);
            if (u != null) {
                Socio socio = club.buscarSocioN(u.getNombre());
                if (socio == null) {
                    JOptionPane.showMessageDialog(this,
                            "Su usuario existe pero no se encontro el socio asociado.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dispose();
                new FrameCliente(padre, club, mcola, socio).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas",
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
