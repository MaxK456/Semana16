package Vista;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * Colores, fuentes y componentes reutilizables para que todas las ventanas
 * del sistema tengan el mismo estilo visual (header azul oscuro, botones
 * azules, boton rojo de salir/cerrar sesion, etc).
 */
public class Estilo {

    public static final Color AZUL_OSCURO   = new Color(27, 58, 92);
    public static final Color FONDO         = new Color(244, 247, 250);
    public static final Color AZUL_BOTON    = new Color(62, 110, 165);
    public static final Color AZUL_BOTON_H  = new Color(48, 92, 143);
    public static final Color ROJO          = new Color(219, 79, 74);
    public static final Color ROJO_H        = new Color(196, 62, 58);
    public static final Color BORDE         = new Color(214, 221, 229);
    public static final Color TEXTO         = new Color(30, 40, 50);
    public static final Color AZUL_SELECCION= new Color(233, 243, 251);

    public static final Font FONT_TITULO    = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FONT_SUB       = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FONT_BOTON     = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_SIDEBAR   = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_NORMAL    = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_TABLA     = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_TABLA_HEAD= new Font("Segoe UI", Font.BOLD, 13);

    /** Cabecera azul oscura con titulo a la izquierda (usada en todas las ventanas principales). */
    public static JPanel crearHeader(String titulo) {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(AZUL_OSCURO);
        header.setPreferredSize(new Dimension(100, 64));
        header.setBorder(new EmptyBorder(0, 24, 0, 24));
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(FONT_TITULO);
        lbl.setForeground(Color.WHITE);
        header.add(lbl, BorderLayout.WEST);
        return header;
    }

    /** Boton rojo tipo "Cerrar sesion" / "Salir". */
    public static JButton botonRojo(String texto) {
        return new BotonPersonalizado(texto, ROJO, ROJO_H, Color.WHITE, 10, false);
    }

    /** Boton azul principal (grande). */
    public static JButton botonAzul(String texto) {
        return new BotonPersonalizado(texto, AZUL_BOTON, AZUL_BOTON_H, Color.WHITE, 10, false);
    }

    /** Boton de la barra lateral (blanco, texto azul oscuro, borde sutil, alineado a la izquierda). */
    public static JButton botonSidebar(String texto) {
        BotonPersonalizado b = new BotonPersonalizado(texto, Color.WHITE, AZUL_SELECCION, AZUL_OSCURO, 8, true);
        b.setFont(FONT_SIDEBAR);
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 46));
        b.setAlignmentX(Component.LEFT_ALIGNMENT);
        return b;
    }

    /**
     * Boton que dibuja su propio fondo redondeado en paintComponent en lugar de
     * depender del Look&Feel del sistema operativo. Esto evita el bug tipico de
     * Windows donde un JButton con color de fondo personalizado se muestra en
     * blanco (sin color, a veces sin texto) hasta que se le pasa el mouse por encima.
     */
    private static class BotonPersonalizado extends JButton {
        private final Color colorBase;
        private final Color colorHover;
        private boolean sobreBoton = false;

        BotonPersonalizado(String texto, Color colorBase, Color colorHover, Color colorTexto, int radio, boolean alineadoIzquierda) {
            super(texto);
            this.colorBase = colorBase;
            this.colorHover = colorHover;
            setFont(FONT_BOTON);
            setForeground(colorTexto);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setHorizontalAlignment(alineadoIzquierda ? SwingConstants.LEFT : SwingConstants.CENTER);
            setBorder(new EmptyBorder(12, 16, 12, 16));
            this.radio = radio;
            this.esSidebar = alineadoIzquierda;
            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) { sobreBoton = true; repaint(); }
                public void mouseExited(java.awt.event.MouseEvent e)  { sobreBoton = false; repaint(); }
            });
        }

        private final int radio;
        private final boolean esSidebar;

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(sobreBoton ? colorHover : colorBase);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radio, radio);
            if (esSidebar) {
                g2.setColor(BORDE);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radio, radio);
            }
            g2.dispose();
            super.paintComponent(g);
        }
    }

    /** Panel lateral (sidebar) vertical con scroll, listo para agregar botones. */
    public static JPanel crearSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(Color.WHITE);
        sidebar.setBorder(new EmptyBorder(12, 12, 12, 12));
        return sidebar;
    }

    /** Tabla estilizada dentro de un JScrollPane, lista para usar. */
    public static JScrollPane crearTabla(JTable tabla) {
        tabla.setFont(FONT_TABLA);
        tabla.setRowHeight(28);
        tabla.setShowGrid(false);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setSelectionBackground(AZUL_SELECCION);
        tabla.setSelectionForeground(TEXTO);
        tabla.setEnabled(false);
        JTableHeader header = tabla.getTableHeader();
        header.setFont(FONT_TABLA_HEAD);
        header.setBackground(Color.WHITE);
        header.setForeground(TEXTO);
        header.setPreferredSize(new Dimension(100, 32));
        JScrollPane sp = new JScrollPane(tabla);
        sp.setBorder(BorderFactory.createLineBorder(BORDE));
        sp.getViewport().setBackground(Color.WHITE);
        return sp;
    }

    /** Barra inferior de estado (texto gris claro con fondo celeste, como en las capturas). */
    public static JLabel crearBarraEstado(String texto) {
        JLabel lbl = new JLabel("  " + texto);
        lbl.setOpaque(true);
        lbl.setBackground(new Color(214, 228, 241));
        lbl.setForeground(TEXTO);
        lbl.setFont(FONT_NORMAL);
        lbl.setPreferredSize(new Dimension(100, 30));
        return lbl;
    }

    /** Modelo de tabla no editable, util para poblar JTables rapido. */
    public static DefaultTableModel modeloNoEditable(String[] columnas) {
        return new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
    }

    /**
     * Ejecuta una accion que imprime por consola (System.out) y devuelve
     * todo lo impreso como String, para poder mostrarlo en un JTextArea.
     * Se usa para reutilizar exactamente la misma logica de tu codigo de
     * consola (mostrarEstadisticas, mostrarHistorial, etc) sin duplicarla.
     */
    public static String capturarSalida(Runnable accion) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            accion.run();
        } finally {
            System.setOut(original);
        }
        String texto = buffer.toString();
        return texto.isEmpty() ? "(sin datos)" : texto;
    }

    /** Muestra un texto (por ejemplo capturado con capturarSalida) dentro de un dialogo con scroll. */
    public static void mostrarTexto(Component padre, String titulo, String contenido) {
        JTextArea area = new JTextArea(contenido);
        area.setEditable(false);
        area.setFont(new Font("Consolas", Font.PLAIN, 13));
        area.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane sp = new JScrollPane(area);
        sp.setPreferredSize(new Dimension(520, 380));
        JOptionPane.showMessageDialog(padre, sp, titulo, JOptionPane.PLAIN_MESSAGE);
    }

    /** Campo de texto estilizado. */
    public static JTextField campoTexto() {
        JTextField tf = new JTextField();
        tf.setFont(FONT_NORMAL);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                new EmptyBorder(6, 8, 6, 8)));
        return tf;
    }

    public static JPasswordField campoPassword() {
        JPasswordField tf = new JPasswordField();
        tf.setFont(FONT_NORMAL);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                new EmptyBorder(6, 8, 6, 8)));
        return tf;
    }

    public static JLabel etiqueta(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(FONT_NORMAL);
        lbl.setForeground(TEXTO);
        return lbl;
    }
}
