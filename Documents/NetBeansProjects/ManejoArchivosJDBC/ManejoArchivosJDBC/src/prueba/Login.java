import Datos.UsuarioJDBC;
import javax.swing.*;
import java.awt.event.*;
import datos.ProductoJDBC;
import Dominio.Usuario;
import Presentaciones.MenuPrincipal;
import java.util.List;
import java.io.*;

public class Login extends JFrame {
    private UsuarioJDBC usuarioJDBC;
    private ProductoJDBC productoJDBC;
    private boolean loggedIn;
    private Usuario usuarioLogueado;

    public Login() {
        usuarioJDBC = new UsuarioJDBC();
        productoJDBC = new ProductoJDBC();
        loggedIn = false;
        usuarioLogueado = null;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setTitle("Manejo de Usuarios y Personas");
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblMensaje = new JLabel("¡Bienvenido!");
        lblMensaje.setBounds(150, 20, 200, 30);
        panel.add(lblMensaje);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 60, 80, 30);
        panel.add(lblUsuario);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(140, 60, 200, 30);
        panel.add(txtUsuario);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setBounds(50, 100, 80, 30);
        panel.add(lblPassword);

        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setBounds(140, 100, 200, 30);
        panel.add(txtPassword);

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(100, 150, 200, 30);
        panel.add(btnAceptar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(100, 200, 200, 30);
        panel.add(btnSalir);

        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!loggedIn) {
                    String username = txtUsuario.getText();
                    String password = new String(txtPassword.getPassword());
                    List<Usuario> usuarios = usuarioJDBC.select();

                    for (Usuario usuario : usuarios) {
                        if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                            loggedIn = true;
                            usuarioLogueado = usuario;
                            JOptionPane.showMessageDialog(null, "¡Inicio de sesión exitoso!");

                            // Aquí instanciamos y mostramos el formulario MenuPrincipal
                            MenuPrincipal menuPrincipal = new MenuPrincipal();
                            menuPrincipal.setVisible(true);

                            // Ocultar el formulario de inicio de sesión
                            setVisible(false);

                            break;
                        }
                    }

                    if (!loggedIn) {
                        JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrectos.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Ya ha iniciado sesión.");
                }
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Login app = new Login();
                app.setVisible(true);
            }
        });
    }
}
