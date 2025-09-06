
package controlador;

import com.google.gson.Gson;
import modelo.Palabra;
import modelo.PalabraDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("obtenerPalabra".equals(accion)) {
            PalabraDAO dao = new PalabraDAO();
            Palabra palabra = dao.obtenerPalabraAleatoria();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (palabra != null) {
                Gson gson = new Gson();
                response.getWriter().write(gson.toJson(palabra));
            } else {
                response.getWriter().write("{\"error\":\"No se pudo obtener la palabra\"}");
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
