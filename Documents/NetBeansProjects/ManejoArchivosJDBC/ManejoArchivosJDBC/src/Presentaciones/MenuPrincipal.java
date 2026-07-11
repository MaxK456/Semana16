package Presentaciones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import datos.ProductoJDBC;
import Dominio.Producto;
import java.awt.event.*;
import java.util.List;

public class MenuPrincipal extends JFrame {
    private ProductoJDBC productoJDBC;
    private JTable table;
    private DefaultTableModel tableModel;

    public MenuPrincipal() {
        productoJDBC = new ProductoJDBC();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setTitle("Lista de Productos");
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Lista de Productos");
        lblTitulo.setBounds(200, 20, 200, 30);
        panel.add(lblTitulo);

        // Crear la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("id");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Precio");
        tableModel.addColumn("Es Intangible");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 70, 500, 200);
        panel.add(scrollPane);

        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.setBounds(50, 300, 150, 30);
        panel.add(btnAgregar);

        JButton btnEditar = new JButton("Editar Producto");
        btnEditar.setBounds(225, 300, 150, 30);
        panel.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Producto");
        btnEliminar.setBounds(400, 300, 150, 30);
        panel.add(btnEliminar);

        cargarProductosEnTabla();

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código para agregar producto
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código para editar producto
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Código para eliminar producto
            }
        });
    }

    private void cargarProductosEnTabla() {
        List<Producto> productos = productoJDBC.select();
        for (Producto producto : productos) {
            Object[] row = {
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.isIntangible() ? "Sí" : "No"
            };
            tableModel.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MenuPrincipal app = new MenuPrincipal();
                app.setVisible(true);
            }
        });
    }
}


