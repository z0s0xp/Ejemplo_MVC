package controlador;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Rectangulo;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        //1. Procesamos parametros
        String accion = request.getParameter("accion");
        //2. Creamos los JavaBeans
        Rectangulo recRequest = new Rectangulo(1,2);
        Rectangulo recSesion = new Rectangulo(3,4);
        Rectangulo recAplicacion =  new Rectangulo(5,6);
                
        
        
        //3. Agregamos el javabean a algun alcance
        
        
        // revisamos la accion proporcionada
        if("agregarVariables".equals(accion)){
        //ALcanse de request
        request.setAttribute("rectanguloRequest", recRequest);
        //Alcace de session
        HttpSession sesion = request.getSession();
        sesion.setAttribute("rectanguloSesion", recSesion);
        //Alcanse Application
        ServletContext application = this.getServletContext();
        application.setAttribute("rectanguloAplicacion", recAplicacion);
        
        //agregamos un mensaje
        request.setAttribute("mensaje", "Las variables fueron agregadas");
        // 4. redireccionamos al jsp del index
        request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else if("listarVariables".equals(accion)){
        //Redireccionamos al  jsp que despliega las variables
        
        request.getRequestDispatcher("WEB-INF/alcanceVariables.jsp").forward(request, response);
        
        }
        else{
        // 4. Redireccionnamos a la pagina de inicio
        request.setAttribute("mensaje","Te fallo amigo");
        request.getRequestDispatcher("index.jsp").forward(request, response);
        
        // esta linea no agregaria info al jsp ya que no propaga los objetos
        //request y response
        //response.sendRedirect("index.jsp");
        }
        
        

    }
}
