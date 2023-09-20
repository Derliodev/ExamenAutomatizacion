package Services;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;

public class TransaccionService {

    private final ConexionBD conexionBD;

    public TransaccionService() throws NamingException {
        conexionBD = new ConexionBD(); 
    }

    public boolean realizarTransferencia(String rutCliente, String rutDueno, String idCuenta,
            double montoTransferencia, String cuentaTransferencia, String tipoCuenta) {
        Connection connection = null;
        try {
            connection = conexionBD.obtenerConexion();

            String insertTransaccionSQL = "INSERT INTO transacción (rutCliente, rutDueno, idCuenta, montoTransferencia, cuentaTransferencia, tipoCuenta) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTransaccionSQL);
            preparedStatement.setString(1, rutCliente);
            preparedStatement.setString(2, rutDueno);
            preparedStatement.setString(3, idCuenta);
            preparedStatement.setDouble(4, montoTransferencia);
            preparedStatement.setString(5, cuentaTransferencia);
            preparedStatement.setString(6, tipoCuenta);

            int registrosInsertados = preparedStatement.executeUpdate();

            return registrosInsertados > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                conexionBD.cerrarConexion(connection);
            }
        }
    }
}

