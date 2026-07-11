package Vista;

import Archivos.ArchivoSocios;
import Modelo.Club;
import Modelo.Socio;
import Queue.metodosCola;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Procesa el pago pendiente de un socio en la cola (imagen 3, boton 2). */
public class FrameProcesarPago extends JDialog {

    public FrameProcesarPago(FrameTrabajador padre, Club club, metodosCola mcola) {
        super(padre, "Procesar pago", true);
        setSize(420, 380);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("PROCESAR PAGO"), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setBackground(Estilo.FONDO);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(20, 24, 20, 24));

        JTextField txtId = Estilo.campoTexto();
        JLabel lblInfo = Estilo.etiqueta(" ");
        lblInfo.setFont(Estilo.FONT_SIDEBAR);
        JTextField txtCorreo = Estilo.campoTexto();
        txtCorreo.setEnabled(false);

        JButton btnBuscar = Estilo.botonAzul("Buscar socio");
        JButton btnConfirmar = Estilo.botonAzul("Confirmar pago");
        btnConfirmar.setEnabled(false);

        form.add(Estilo.etiqueta("ID socio"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtId);
        form.add(Box.createVerticalStrut(10));
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(btnBuscar);
        form.add(Box.createVerticalStrut(14));
        form.add(lblInfo);
        form.add(Box.createVerticalStrut(10));
        form.add(Estilo.etiqueta("Correo para enviar la boleta"));
        form.add(Box.createVerticalStrut(4));
        form.add(txtCorreo);
        form.add(Box.createVerticalStrut(20));
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(btnConfirmar);

        add(form, BorderLayout.CENTER);

        final Socio[] socioEncontrado = new Socio[1];

        btnBuscar.addActionListener(e -> {
            try {
                Socio s = club.buscarSocio(Integer.parseInt(txtId.getText().trim()));
                if (s != null) {
                    socioEncontrado[0] = s;
                    lblInfo.setText("<html>Socio: " + s.getNombre() + "<br>Monto a pagar: S/ " +
                            String.format("%.2f", s.getMembresia().getCosto()) + "</html>");
                    txtCorreo.setEnabled(true);
                    btnConfirmar.setEnabled(true);
                } else {
                    socioEncontrado[0] = null;
                    lblInfo.setText("Socio no encontrado.");
                    btnConfirmar.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID invalido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnConfirmar.addActionListener(e -> {
            Socio s = socioEncontrado[0];
            if (s == null) return;
            String correo = txtCorreo.getText().trim();
            boolean procesado = mcola.procesarPagoDe(s.getNombre(), club);
            if (procesado) {
                ArchivoSocios.guardar("socios.dat", club.getSocios());
                JOptionPane.showMessageDialog(this,
                        "Boleta procesada de manera conforme y notificada a: " + correo + "\nPago procesado.",
                        "Pago procesado", JOptionPane.INFORMATION_MESSAGE);
                padre.refrescarDespuesDeCambios();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Este socio no tiene pagos pendientes en la cola.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
