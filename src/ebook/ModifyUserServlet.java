package ebook;

import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import ebook.HibernateUtil;
import ebook.User;

@WebServlet("/ModifyUser")
public class ModifyUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyUserServlet() {
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
            System.out.println("add a new book");
            
            String sID = request.getParameter("ID");
            
            System.out.println("ID"+sID);
            Long ID = Long.valueOf(sID).longValue();
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            
            List<User> result = HibernateUtil.getSessionFactory()
                    .getCurrentSession().createQuery("from User").list(); 
            Iterator<User> it = result.iterator();
            
            User user = new User();
            
            while (it.hasNext()) {
            	user = (User) it.next();
            	int uID = user.getId();
            	if(uID==ID) {
            		user.setName(name);
            		user.setEmail(email);
            		user.setPassword(password);
            		user.setPhone(phone);
                    break;
            	}
            }        
            
            System.out.println("complete");
            
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