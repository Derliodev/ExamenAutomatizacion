package Services;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBD {
    private DataSource dataSource;

    public ConexionBD() throws NamingException {
        try {
            Context contexto = new InitialContext();
            dataSource = (DataSource) contexto.lookup("java:comp/env/jdbc/ctacorriente");
        } catch (NamingException e) {
            throw new NamingException("No se pudo encontrar el recurso JDBC en el contexto.");
        }
    }

    public Connection obtenerConexion() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("El DataSource no está inicializado correctamente.");
        }
        
        return dataSource.getConnection();
    }

    public void cerrarConexion(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
