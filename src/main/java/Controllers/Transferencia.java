package Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@WebServlet("/transferencia")
public class Transferencia extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String rutCliente = request.getParameter("rutCliente");
        String rutDueno = request.getParameter("rutDueno");
        String idCuenta = request.getParameter("idCuenta");
        double montoTransferencia = Double.parseDouble(request.getParameter("montoTransferencia"));
        String cuentaTransferencia = request.getParameter("cuentaTransferencia");
        String tipoCuenta = request.getParameter("tipoCuenta");

        
        boolean transferenciaExitosa = realizarTransferencia(rutCliente, rutDueno, idCuenta, montoTransferencia, cuentaTransferencia, tipoCuenta);

        if (transferenciaExitosa) {
            
            response.sendRedirect("transferenciaExitosa.jsp");
        } else {
            
            response.sendRedirect("transferenciaFallida.jsp");
        }
    }

    private boolean realizarTransferencia(String rutCliente, String rutDueno, String idCuenta,
            double montoTransferencia, String cuentaTransferencia, String tipoCuenta) {
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ctacorriente");
            Connection connection = dataSource.getConnection();

            
            String insertTransaccionSQL = "INSERT INTO transacción (rutCliente, rutDueno, idCuenta, montoTransferencia, cuentaTransferencia, tipoCuenta) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertTransaccionSQL);
            preparedStatement.setString(1, rutCliente);
            preparedStatement.setString(2, rutDueno);
            preparedStatement.setString(3, idCuenta);
            preparedStatement.setDouble(4, montoTransferencia);
            preparedStatement.setString(5, cuentaTransferencia);
            preparedStatement.setString(6, tipoCuenta);

            int registrosInsertados = preparedStatement.executeUpdate();
            connection.close();

            return registrosInsertados > 0;
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

