package Vista;

import Modelo.Membresia;
import Modelo.Pago;
import Modelo.Socio;
import Queue.metodosCola;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/** Cambia la membresia del socio logueado (imagen 4, boton 2). Equivalente a Sistema.menuCliente caso 2. */
public class FrameCambiarMembresia extends JDialog {

    public FrameCambiarMembresia(FrameCliente padre, Socio socio, metodosCola mcola) {
        super(padre, "Cambiar membresia", true);
        setSize(420, 300);
        setLocationRelativeTo(padre);
        setLayout(new BorderLayout());
        add(Estilo.crearHeader("CAMBIAR MEMBRESIA"), BorderLayout.NORTH);

        JPanel form = new JPanel();
        form.setBackground(Estilo.FONDO);
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(20, 24, 20, 24));

        form.add(Estilo.etiqueta("Membresia actual: " + socio.getMembresia().getTipo().toUpperCase()));
        form.add(Box.createVerticalStrut(16));

        JComboBox<String> combo = new JComboBox<>(new String[]{"1. Basica (S/ 150)", "2. Silver (S/ 200)", "3. Gold (S/ 350)"});
        combo.setFont(Estilo.FONT_NORMAL);
        form.add(Estilo.etiqueta("Nueva membresia"));
        form.add(Box.createVerticalStrut(4));
        form.add(combo);
        form.add(Box.createVerticalStrut(20));

        JButton btnConfirmar = Estilo.botonAzul("Confirmar solicitud");
        btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        form.add(btnConfirmar);

        add(form, BorderLayout.CENTER);

        btnConfirmar.addActionListener(e -> {
            Membresia nueva = new Membresia(combo.getSelectedIndex() + 1);
            int confirmar = JOptionPane.showConfirmDialog(this,
                    "Cambiar a membresia: " + nueva + "\n¿Confirmar solicitud?",
                    "Confirmar cambio", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                socio.cambiarm(nueva);
                double monto = nueva.getCosto();
                Pago p = new Pago(monto, socio.getNombre());
                socio.registrarPago(p);
                mcola.agregarPagoCola(p);
                JOptionPane.showMessageDialog(this,
                        "Membresia actualizada y pago registrado: S/ " + String.format("%.2f", monto),
                        "Actualizado", JOptionPane.INFORMATION_MESSAGE);
                padre.refrescarDespuesDeCambios();
                dispose();
            }
        });
    }
}
