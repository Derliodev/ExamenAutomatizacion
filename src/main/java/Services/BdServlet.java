package Services;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/BdServlet")
public class BdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            Context contexto = new InitialContext();

 
            DataSource fuenteDatos = (DataSource) contexto.lookup("java:comp/env/jdbc/ctacorriente");

       
            Connection conexion = fuenteDatos.getConnection();


            conexion.close();
        } catch (NamingException | SQLException e) {
            e.printStackTrace(); 
        }
    }
}
