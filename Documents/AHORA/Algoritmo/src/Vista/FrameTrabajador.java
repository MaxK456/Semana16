package Vista;

import LOGIN.ListaUsuario;
import Modelo.Club;
import Modelo.Socio;
import Modelo.Trabajador;
import Queue.metodosCola;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** Panel de trabajador (imagen 3): 9 botones + tabla de socios. */
public class FrameTrabajador extends JFrame {

    private final FrameRol padre;
    private final Club club;
    private final metodosCola mcola;
    private final ListaUsuario listaUsuarios;
    private final Trabajador trabajador;

    private DefaultTableModel modelo;
    private JLabel barraEstado;

    private static final String[] COLUMNAS = {"ID", "Nombre", "Membresia", "Costo", "Pagado", "Antiguedad (dias)"};

    public FrameTrabajador(FrameRol padre, Club club, metodosCola mcola, ListaUsuario listaUsuarios, Trabajador trabajador) {
        this.padre = padre;
        this.club = club;
        this.mcola = mcola;
        this.listaUsuarios = listaUsuarios;
        this.trabajador = trabajador;

        setTitle("Panel de Trabajador - " + trabajador.getNombre());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1180, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(construirHeader(), BorderLayout.NORTH);
        add(construirCentro(), BorderLayout.CENTER);

        barraEstado = Estilo.crearBarraEstado("Total socios: 0");
        add(barraEstado, BorderLayout.SOUTH);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                padre.setVisible(true);
            }
        });

        refrescarTabla();
    }

    private JPanel construirHeader() {
        JPanel header = Estilo.crearHeader("PANEL DE TRABAJADOR");
        JButton btnCerrar = Estilo.botonRojo("Cerrar sesion");
        btnCerrar.addActionListener(e -> {
            dispose();
            padre.setVisible(true);
        });
        JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        derecha.setOpaque(false);
        derecha.add(btnCerrar);
        header.add(derecha, BorderLayout.EAST);
        return header;
    }

    private JPanel construirCentro() {
        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(Estilo.FONDO);

        JPanel sidebar = Estilo.crearSidebar();
        String[] etiquetas = {
            "1. Registrar socio", "2. Procesar pago", "3. Generar credenciales",
            "4. Ver cola de pagos", "5. Socios en espera", "6. Cobro con caja",
            "7. Corregir nombre", "8. Eliminar socio", "9. Cerrar sesion"
        };
        Runnable[] acciones = {
            this::accionRegistrarSocio, this::accionProcesarPago, this::accionGenerarCredenciales,
            this::accionColaPagos, this::accionSociosEnEspera, this::accionCobroCaja,
            this::accionCorregirNombre, this::accionEliminarSocio, this::accionCerrarSesion
        };
        for (int i = 0; i < etiquetas.length; i++) {
            JButton b = Estilo.botonSidebar(etiquetas[i]);
            Runnable accion = acciones[i];
            b.addActionListener(e -> accion.run());
            sidebar.add(b);
            sidebar.add(Box.createVerticalStrut(8));
        }
        JScrollPane sidebarScroll = new JScrollPane(sidebar);
        sidebarScroll.setBorder(null);
        sidebarScroll.setPreferredSize(new Dimension(260, 100));
        sidebarScroll.getVerticalScrollBar().setUnitIncrement(16);

        modelo = Estilo.modeloNoEditable(COLUMNAS);
        JTable tabla = new JTable(modelo);
        JScrollPane tablaScroll = Estilo.crearTabla(tabla);

        centro.add(sidebarScroll, BorderLayout.WEST);
        centro.add(tablaScroll, BorderLayout.CENTER);
        return centro;
    }

    private void refrescarTabla() {
        modelo.setRowCount(0);
        List<Socio> socios = club.getSocios().obtenerTodos();
        for (Socio s : socios) {
            modelo.addRow(new Object[]{
                String.format("%08d", s.getId()),
                s.getNombre(),
                s.getMembresia().getTipo().toUpperCase(),
                String.format("S/ %.2f", s.getMembresia().getCosto()),
                s.isPago() ? "Si" : "No",
                s.antiguedad()
            });
        }
        barraEstado.setText("  Total socios: " + socios.size());
    }

    public void refrescarDespuesDeCambios() {
        refrescarTabla();
    }

    // 1. Registrar socio
    private void accionRegistrarSocio() {
        new FrameRegistrarSocio(this, club, mcola).setVisible(true);
    }

    // 2. Procesar pago
    private void accionProcesarPago() {
        new FrameProcesarPago(this, club, mcola).setVisible(true);
    }

    // 3. Generar credenciales
    private void accionGenerarCredenciales() {
        new FrameGenerarCredenciales(this, club, listaUsuarios).setVisible(true);
    }

    // 4. Ver cola de pagos
    private void accionColaPagos() {
        new FrameColaPagos(this, mcola).setVisible(true);
    }

    // 5. Socios en espera
    private void accionSociosEnEspera() {
        new FrameSociosEnEspera(this, club, mcola).setVisible(true);
    }

    // 6. Cobro con caja
    private void accionCobroCaja() {
        new FrameCobroCajaSeleccion(this, club, mcola).setVisible(true);
    }

    // 7. Corregir nombre
    private void accionCorregirNombre() {
        new FrameCorregirNombre(this, club).setVisible(true);
    }

    // 8. Eliminar socio
    private void accionEliminarSocio() {
        new FrameEliminarSocio(this, club, mcola).setVisible(true);
    }

    // 9. Cerrar sesion
    private void accionCerrarSesion() {
        dispose();
        padre.setVisible(true);
    }
}
