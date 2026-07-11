package Vista;

import Listas.ListaTrabajador;
import Modelo.Club;
import Modelo.Socio;
import Queue.metodosCola;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/** Panel de administrador (imagen 2): 11 botones + tabla de socios. */
public class FrameAdministrador extends JFrame {

    private final FrameRol padre;
    private final Club club;
    private final metodosCola mcola;
    private final ListaTrabajador listaTrabajadores;

    private DefaultTableModel modelo;
    private JLabel barraEstado;

    private static final String[] COLUMNAS = {"ID", "Nombre", "Membresia", "Costo", "Pagado", "Antiguedad (dias)"};

    public FrameAdministrador(FrameRol padre, Club club, metodosCola mcola, ListaTrabajador listaTrabajadores) {
        this.padre = padre;
        this.club = club;
        this.mcola = mcola;
        this.listaTrabajadores = listaTrabajadores;

        setTitle("Panel de Administrador");
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
        JPanel header = Estilo.crearHeader("PANEL DE ADMINISTRADOR");
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
            "1. Listar socios", "2. Ver total de un socio", "3. Ordenar socios por ID",
            "4. Ver socios en espera", "5. Historial de pagos", "6. Estadisticas",
            "7. Registrar trabajador", "8. Listar trabajadores", "9. Listar invitados",
            "10. Eliminar trab/invitado", "11. Cerrar sesion"
        };
        Runnable[] acciones = {
            this::accionListar, this::accionVerTotal, this::accionOrdenar,
            this::accionSociosEnEspera, this::accionHistorial, this::accionEstadisticas,
            this::accionRegistrarTrabajador, this::accionListarTrabajadores, this::accionListarInvitados,
            this::accionEliminarTrabInvitado, this::accionCerrarSesion
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
        tablaScroll.setBorder(new EmptyBorder(0, 0, 0, 0));

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

    // 1. Listar socios
    private void accionListar() {
        refrescarTabla();
    }

    // 2. Ver total de un socio
    private void accionVerTotal() {
        String idStr = JOptionPane.showInputDialog(this, "ID: ");
        if (idStr == null) return;
        try {
            Socio s = club.buscarSocio(Integer.parseInt(idStr.trim()));
            if (s != null) {
                JOptionPane.showMessageDialog(this, "Total: " + s.total(), "Total del socio", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Socio no encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID invalido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 3. Ordenar socios por ID
    private void accionOrdenar() {
        club.ordenarSociosPorId();
        refrescarTabla();
        JOptionPane.showMessageDialog(this, "Socios ordenados por ID.");
    }

    // 4. Ver socios en espera
    private void accionSociosEnEspera() {
        new FrameSociosEnEspera(this, club, mcola).setVisible(true);
    }

    // 5. Historial de pagos
    private void accionHistorial() {
        new FrameHistorialPagos(this, mcola).setVisible(true);
    }

    // 6. Estadisticas
    private void accionEstadisticas() {
        String salida = Estilo.capturarSalida(club::mostrarEstadisticas);
        Estilo.mostrarTexto(this, "Estadisticas", salida);
    }

    // 7. Registrar trabajador
    private void accionRegistrarTrabajador() {
        new FrameRegistrarTrabajador(this, listaTrabajadores).setVisible(true);
    }

    // 8. Listar trabajadores
    private void accionListarTrabajadores() {
        new FrameListarTrabajadores(this, listaTrabajadores).setVisible(true);
    }

    // 9. Listar invitados
    private void accionListarInvitados() {
        new FrameListarInvitados(this, club).setVisible(true);
    }

    // 10. Eliminar trabajador o invitado
    private void accionEliminarTrabInvitado() {
        new FrameEliminarTrabInvitado(this, club, listaTrabajadores).setVisible(true);
    }

    // 11. Cerrar sesion
    private void accionCerrarSesion() {
        dispose();
        padre.setVisible(true);
    }

    public void refrescarDespuesDeCambios() {
        refrescarTabla();
    }
}
