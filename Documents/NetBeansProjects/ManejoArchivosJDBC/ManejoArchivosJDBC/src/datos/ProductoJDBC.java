package datos;

import Dominio.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoJDBC {
    private static final String SQL_SELECT = "SELECT id, nombre, precio, esIntangible FROM productos";
    private static final String SQL_INSERT = "INSERT INTO productos(nombre, precio, esIntangible) VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE productos SET nombre=?, precio=?, esIntangible=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE id=?";

    public List<Producto> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Producto> productos = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                boolean esIntangible = rs.getBoolean("esIntangible");

                Producto producto = new Producto(id, nombre, precio, esIntangible);
                productos.add(producto);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return productos;
    }

    public int insert(Producto producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setBoolean(3, producto.isIntangible());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return rows;
    }

    public int update(Producto producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setBoolean(3, producto.isIntangible());
            stmt.setInt(4, producto.getId());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return rows;
    }

    public int delete(Producto producto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, producto.getId());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }

        return rows;
    }

    private Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/prueba"; // Ajusta la URL
        String jdbcUser = "root"; // Ajusta el usuario
        String jdbcPassword = ""; // Ajusta la contraseña
        return DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
    }

    private void close(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    private void close(PreparedStatement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    private void close(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}

