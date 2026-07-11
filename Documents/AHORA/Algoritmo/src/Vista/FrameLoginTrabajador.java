package Vista;

import LOGIN.ListaUsuario;
import Listas.ListaTrabajador;
import Modelo.Club;
import Modelo.Trabajador;
import Queue.metodosCola;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Login de trabajador. Misma validacion que Metodos.loginTrabajador() -> ListaTrabajador.validarLoginT(). */
public class FrameLoginTrabajador extends JFrame {

    public FrameLoginTrabajador(FrameRol padre, Club club, metodosCola mcola,
                                 ListaUsuario listaUsuarios, ListaTrabajador listaTrabajadores) {
        setTitle("Login Trabajador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(380, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("LOGIN TRABAJADOR"), BorderLayout.NORTH);

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
            Trabajador t = listaTrabajadores.validarLoginT(usuario, clave);
            if (t != null) {
                dispose();
                new FrameTrabajador(padre, club, mcola, listaUsuarios, t).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales de trabajador incorrectas.",
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
