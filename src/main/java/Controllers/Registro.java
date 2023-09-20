package Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@WebServlet("/registro")
public class Registro extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String rut = request.getParameter("rut");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String nombreMascota = request.getParameter("nombreMascota");

        
        String nombreUsuario = request.getParameter("nombreUsuario");
        String password = request.getParameter("password");
        String departamento = request.getParameter("departamento");

        
        boolean registrado = registrarPersona(rut, nombre, apellido, direccion, correo, telefono, nombreMascota);
        boolean autenticado = autenticarUsuario(nombreUsuario, password, departamento, rut);

        if (registrado && autenticado) {
            
            response.sendRedirect("bienvenido.jsp");
        } else {
            
            response.sendRedirect("index.jsp");
        }
    }

    private boolean registrarPersona(String rut, String nombre, String apellido, String direccion,
                                      String correo, String telefono, String nombreMascota) {
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ctacorriente");
            Connection connection = dataSource.getConnection();

            
            String insertPersonaSQL = "INSERT INTO persona (rut, nombre, apellido, direccion, correo, telefono, nombreMascota) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertPersonaSQL);
            preparedStatement.setString(1, rut);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, apellido);
            preparedStatement.setString(4, direccion);
            preparedStatement.setString(5, correo);
            preparedStatement.setString(6, telefono);
            preparedStatement.setString(7, nombreMascota);

            int registrosInsertados = preparedStatement.executeUpdate();
            connection.close();

            return registrosInsertados > 0;
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean autenticarUsuario(String nombreUsuario, String password, String departamento, String rut) {
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/ctacorriente");
            Connection connection = dataSource.getConnection();

            
            String insertUsuarioSQL = "INSERT INTO usuario (nombreUsuario, password, departamento) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertUsuarioSQL);
            preparedStatement.setString(1, nombreUsuario);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, departamento);

            int registrosInsertados = preparedStatement.executeUpdate();

            
            if (registrosInsertados > 0) {
                String insertCtaCorrientSQL = "INSERT INTO cuentaCorriente (rutCliente, monto, ejecutivo) VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(insertCtaCorrientSQL);
                preparedStatement.setString(1, rut);
                preparedStatement.setDouble(2, 0.0);
                preparedStatement.setString(3, departamento);

                registrosInsertados = preparedStatement.executeUpdate();
            }

            connection.close();

            return registrosInsertados > 0;
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

