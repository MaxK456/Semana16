package Vista;

import LOGIN.ListaUsuario;
import Listas.ListaTrabajador;
import Modelo.Club;
import Queue.metodosCola;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Ventana principal: "Seleccione su rol" (imagen 1). */
public class FrameRol extends JFrame {

    private final Club club;
    private final metodosCola mcola;
    private final ListaUsuario listaUsuarios;
    private final ListaTrabajador listaTrabajadores;

    public FrameRol(Club club, metodosCola mcola, ListaUsuario listaUsuarios, ListaTrabajador listaTrabajadores) {
        this.club = club;
        this.mcola = mcola;
        this.listaUsuarios = listaUsuarios;
        this.listaTrabajadores = listaTrabajadores;

        setTitle("Sistema Club Deportivo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(680, 560);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(Estilo.crearHeader("SISTEMA CLUB DEPORTIVO"), BorderLayout.NORTH);
        add(construirCentro(), BorderLayout.CENTER);
    }

    private JPanel construirCentro() {
        JPanel centro = new JPanel();
        centro.setBackground(Estilo.FONDO);
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(new EmptyBorder(40, 90, 30, 90));

        JLabel titulo = new JLabel("Seleccione su rol");
        titulo.setFont(Estilo.FONT_SUB);
        titulo.setForeground(Estilo.AZUL_OSCURO);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        centro.add(titulo);
        centro.add(Box.createVerticalStrut(24));

        JButton btnAdmin = Estilo.botonAzul("ADMINISTRADOR");
        JButton btnTrabajador = Estilo.botonAzul("TRABAJADOR");
        JButton btnCliente = Estilo.botonAzul("CLIENTE");
        for (JButton b : new JButton[]{btnAdmin, btnTrabajador, btnCliente}) {
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setMaximumSize(new Dimension(400, 50));
            b.setPreferredSize(new Dimension(400, 50));
            centro.add(b);
            centro.add(Box.createVerticalStrut(16));
        }

        btnAdmin.addActionListener(e -> {
            setVisible(false);
            new FrameLoginAdmin(this, club, mcola, listaTrabajadores).setVisible(true);
        });
        btnTrabajador.addActionListener(e -> {
            setVisible(false);
            new FrameLoginTrabajador(this, club, mcola, listaUsuarios, listaTrabajadores).setVisible(true);
        });
        btnCliente.addActionListener(e -> {
            setVisible(false);
            new FrameLoginCliente(this, club, mcola, listaUsuarios).setVisible(true);
        });

        centro.add(Box.createVerticalGlue());

        JPanel filaSalir = new JPanel(new BorderLayout());
        filaSalir.setBackground(Estilo.FONDO);
        JButton btnSalir = Estilo.botonRojo("Salir");
        btnSalir.addActionListener(e -> System.exit(0));
        filaSalir.add(btnSalir, BorderLayout.EAST);
        centro.add(filaSalir);

        return centro;
    }
}
