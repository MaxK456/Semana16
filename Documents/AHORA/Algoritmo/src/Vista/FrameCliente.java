package Vista;

import Modelo.Club;
import Modelo.Pago;
import Modelo.Socio;
import Queue.metodosCola;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/** Panel de cliente (imagen 4): 5 botones + tabla de pagos. */
public class FrameCliente extends JFrame {

    private final FrameRol padre;
    private final Club club;
    private final metodosCola mcola;
    private final Socio socio;

    private DefaultTableModel modelo;
    private JLabel barraEstado;

    public FrameCliente(FrameRol padre, Club club, metodosCola mcola, Socio socio) {
        this.padre = padre;
        this.club = club;
        this.mcola = mcola;
        this.socio = socio;

        setTitle("Panel de Cliente - " + socio.getNombre());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1180, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(construirHeader(), BorderLayout.NORTH);
        add(construirCentro(), BorderLayout.CENTER);

        barraEstado = Estilo.crearBarraEstado("");
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
        JPanel header = Estilo.crearHeader("PANEL DE CLIENTE");
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
            "1. Ver total pagado", "2. Cambiar membresia", "3. Renovar membresia",
            "4. Registrar invitado", "5. Cerrar sesion"
        };
        Runnable[] acciones = {
            this::accionVerTotal, this::accionCambiarMembresia, this::accionRenovarMembresia,
            this::accionRegistrarInvitado, this::accionCerrarSesion
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

        modelo = Estilo.modeloNoEditable(new String[]{"Fecha", "Monto"});
        JTable tabla = new JTable(modelo);
        JScrollPane tablaScroll = Estilo.crearTabla(tabla);

        centro.add(sidebarScroll, BorderLayout.WEST);
        centro.add(tablaScroll, BorderLayout.CENTER);
        return centro;
    }

    private void refrescarTabla() {
        modelo.setRowCount(0);
        List<Pago> pagos = socio.getPagos();
        for (Pago p : pagos) {
            modelo.addRow(new Object[]{p.getFechaFormateada(), String.format("S/ %.2f", p.getMonto())});
        }
        barraEstado.setText(String.format("  Socio: %s | Membresia: %s | Total pagado: S/ %.2f",
                socio.getNombre(), socio.getMembresia().getTipo().toUpperCase(), socio.total()));
    }

    public void refrescarDespuesDeCambios() {
        refrescarTabla();
    }

    // 1. Ver total pagado
    private void accionVerTotal() {
        refrescarTabla();
    }

    // 2. Cambiar membresia
    private void accionCambiarMembresia() {
        new FrameCambiarMembresia(this, socio, mcola).setVisible(true);
    }

    // 3. Renovar membresia
    private void accionRenovarMembresia() {
        if (socio.getDeuda() > 0) {
            JOptionPane.showMessageDialog(this, "Tiene deuda.", "No se puede renovar", JOptionPane.WARNING_MESSAGE);
        } else {
            socio.renovarm();
            JOptionPane.showMessageDialog(this, "Membresia renovada", "Renovacion exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // 4. Registrar invitado
    private void accionRegistrarInvitado() {
        new FrameRegistrarInvitado(this, club, socio).setVisible(true);
    }

    // 5. Cerrar sesion
    private void accionCerrarSesion() {
        dispose();
        padre.setVisible(true);
    }
}
