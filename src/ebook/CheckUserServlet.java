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
import net.sf.json.JSONArray;

@WebServlet("/CheckUser")
public class CheckUserServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	public CheckUserServlet() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            String username = request.getParameter("username");
            System.out.println("username:"+username);
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            /* cross  origin */
            response.setHeader("Access-Control-Allow-Origin","*");
            
            System.out.println("checking an user");

            String rst="";
            
            List<User> result = HibernateUtil.getSessionFactory()
                    .getCurrentSession().createQuery("from User").list(); 
            Iterator<User> it = result.iterator();
            
            
            while (it.hasNext()) {
            	User user = (User) it.next();
            	String Name = user.getName();
                if(Name.equals(username)) {
                	rst=user.getPassword();
                }
            }            
            System.out.println("check ends");

            response.getWriter().write(rst);
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
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
