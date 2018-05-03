package ebook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import ebook.HibernateUtil;
import ebook.User;

/**
 * Servlet implementation class UserManagerServlet
 */
@WebServlet("/UserManager")
public class UserManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            // Begin unit of work
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	           session.beginTransaction();
         // Write HTML header
         PrintWriter out = response.getWriter();
         response.setContentType("text/html;charset=utf-8");

         out.println("<html><head><title>User Manager</title></head><body>");
  
         out.println("	<div><ul>\r\n" + 
         		"	<li><a href=\"http://localhost:8080/db/index.html\">Ö÷Ò³</a></li>\r\n" + 
         		"	<li><a href=\"http://localhost:8080/db/login.html\">µÇÂ½</a></li>\r\n" + 
         		"	\r\n" + 
         		"	</ul></div>");
 
        out.println("</body></html>");
        out.flush();
        out.close();
        
        
        User user = new User();
        user.setName("122");
        user.setPassword("2");
        user.setPhone("3");
        user.setEmail("4");
        session.save(user);
        session.getTransaction().commit();
        
          
        }
        catch (Exception ex) {
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            if ( ServletException.class.isInstance( ex ) ) {
                throw ( ServletException ) ex;
            }
            else {
                throw new ServletException( ex );
            }
        }
		HibernateUtil.getSessionFactory().close();
		
	}

	
	
}
