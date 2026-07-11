package Vista;

import Archivos.ArchivoTrabajador;
import LOGIN.ArchivoUsuarios;
import LOGIN.ListaUsuario;
import Listas.ListaTrabajador;
import Modelo.Club;
import Queue.metodosCola;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Punto de entrada de la version grafica (Swing) del sistema.
 * Carga los mismos datos que Principal.Sistema.main() (socios, invitados,
 * usuarios y trabajadores desde los .dat) y abre la ventana de seleccion
 * de rol en lugar del menu de consola.
 */
public class MainGUI {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        metodosCola mcola = new metodosCola();
        Club club = new Club(); // ya carga socios.dat e invitados.dat internamente
        ListaUsuario listaUsuarios = ArchivoUsuarios.cargar("usuarios.dat");
        ListaTrabajador listaTrabajadores = ArchivoTrabajador.cargarT("trabajadores.dat");

        SwingUtilities.invokeLater(() -> {
            FrameRol frame = new FrameRol(club, mcola, listaUsuarios, listaTrabajadores);
            frame.setVisible(true);
        });
    }
}
