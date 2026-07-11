package Vista;

import Archivos.ArchivoSocios;
import Modelo.Club;
import Modelo.Cobro;
import Modelo.Pago;
import Pila.CajaCobro;
import Queue.metodosCola;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Caja de cobro (registrar consumo/servicio/descuento, deshacer, rehacer, confirmar). Equivalente a Sistema.procesarCaja(). */
public class FrameCaja extends JDialog {

    private final CajaCobro caja;
    private final Club club;
    private final metodosCola mcola;
    private final boolean esInvitado;
    private JTextArea areaEstado;
    private JLabel lblTotal;

    public FrameCaja(FrameTrabajador padre, CajaCobro caja, Club club, metodosCola mcola, boolean esInvitado) {
        super(padre, "Caja de cobro", true);
        this.caja = caja;
        this.club = club;
        this.mcola = mcola;
        this.esInvitado = esInvitado;

        setSize(560, 560);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader((esInvitado ? "Invitado: " : "Socio: ") + caja.getNombreCliente()), BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(Estilo.FONDO);
        centro.setBorder(new EmptyBorder(16, 16, 16, 16));

        areaEstado = new JTextArea();
        areaEstado.setEditable(false);
        areaEstado.setFont(new Font("Consolas", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(areaEstado);
        scroll.setBorder(BorderFactory.createLineBorder(Estilo.BORDE));
        centro.add(scroll, BorderLayout.CENTER);

        lblTotal = Estilo.etiqueta("Total actual: S/ 0.00");
        lblTotal.setFont(Estilo.FONT_SUB);
        JPanel panelTotal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTotal.setBackground(Estilo.FONDO);
        panelTotal.add(lblTotal);
        centro.add(panelTotal, BorderLayout.NORTH);

        JPanel botones = new JPanel(new GridLayout(4, 2, 10, 10));
        botones.setBackground(Estilo.FONDO);
        botones.setBorder(new EmptyBorder(16, 0, 0, 0));

        JButton btnConsumo = Estilo.botonAzul("1. Registrar consumo");
        JButton btnServicio = Estilo.botonAzul("2. Registrar servicio");
        JButton btnDescuento = Estilo.botonAzul("3. Registrar descuento");
        JButton btnDeshacer = Estilo.botonAzul("4. Deshacer");
        JButton btnRehacer = Estilo.botonAzul("5. Rehacer");
        JButton btnMostrar = Estilo.botonAzul("6. Mostrar estado");
        JButton btnConfirmar = Estilo.botonRojo("7. Confirmar y salir");
        JButton btnSalir = Estilo.botonRojo("8. Salir sin confirmar");

        botones.add(btnConsumo);
        botones.add(btnServicio);
        botones.add(btnDescuento);
        botones.add(btnDeshacer);
        botones.add(btnRehacer);
        botones.add(btnMostrar);
        botones.add(btnConfirmar);
        botones.add(btnSalir);

        centro.add(botones, BorderLayout.SOUTH);
        add(centro, BorderLayout.CENTER);

        btnConsumo.addActionListener(e -> registrar(Cobro.Tipo.consumo));
        btnServicio.addActionListener(e -> registrar(Cobro.Tipo.servicio));
        btnDescuento.addActionListener(e -> registrar(Cobro.Tipo.descuento));
        btnDeshacer.addActionListener(e -> { caja.deshacer(); actualizar(); });
        btnRehacer.addActionListener(e -> { caja.rehacer(); actualizar(); });
        btnMostrar.addActionListener(e -> actualizar());
        btnConfirmar.addActionListener(e -> confirmarYSalir());
        btnSalir.addActionListener(e -> dispose());

        actualizar();
    }

    private void registrar(Cobro.Tipo tipo) {
        JTextField txtDescripcion = Estilo.campoTexto();
        JTextField txtMonto = Estilo.campoTexto();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Estilo.etiqueta("Descripcion:"));
        panel.add(txtDescripcion);
        panel.add(Box.createVerticalStrut(8));
        panel.add(Estilo.etiqueta("Monto:"));
        panel.add(txtMonto);

        int resultado = JOptionPane.showConfirmDialog(this, panel, "Registrar " + tipo,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resultado != JOptionPane.OK_OPTION) return;

        try {
            double monto = Double.parseDouble(txtMonto.getText().trim().replace(",", "."));
            Cobro cobro = new Cobro(tipo, txtDescripcion.getText().trim(), monto);
            caja.ejecutar(cobro);
            actualizar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Monto invalido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizar() {
        areaEstado.setText(Estilo.capturarSalida(caja::mostrar));
        lblTotal.setText(String.format("Total actual: S/ %.2f", caja.totalActual()));
    }

    private void confirmarYSalir() {
        double total = caja.totalActual();
        boolean esSocio = caja.esSocio();
        caja.confirmar();
        if (total > 0 && esSocio) {
            Pago pago = new Pago(total, caja.getNombreCliente());
            mcola.agregarPagoCola(pago);
        }
        if (esSocio) {
            ArchivoSocios.guardar("socios.dat", club.getSocios());
        }
        JOptionPane.showMessageDialog(this, "Cobro confirmado.\nTotal: S/ " + String.format("%.2f", total),
                "Caja confirmada", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
}
