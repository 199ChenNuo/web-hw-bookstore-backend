package ebook;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import ebook.HibernateUtil;
import ebook.User;

@WebServlet("/AddUser")
public class AddUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

    @SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			
        	session.beginTransaction();
            
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin","*");
            System.out.println("add a new user");

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            
            System.out.println("get username"+username);
            
            User user = new User();
            user.setName(username);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(password);
            System.out.println("gen user complete");
            
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
	}
}