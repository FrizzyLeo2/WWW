import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
	 throws ServletException, IOException {

     String email = request.getParameter("email");
     String pass = request.getParameter("pass");
     LoginService loginService = new LoginService();
     boolean result = loginService.authenticateUser(email, pass);
     User user = loginService.getUserByEmail(email);
     if(result == true){
         request.getSession().setAttribute("user", user);
         RequestDispatcher rs = request.getRequestDispatcher("Index.html");
         rs.forward(request, response);
     }
     else{
         RequestDispatcher rs = request.getRequestDispatcher("Login.html");
	 rs.forward(request, response);
     }
}

}
