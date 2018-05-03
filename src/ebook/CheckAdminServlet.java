package ebook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import ebook.Admin;


/**
 * Servlet implementation class UserManagerServlet
 */
@WebServlet("/CheckAdmin")
public class CheckAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckAdminServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            String adminname = request.getParameter("username");
            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            
            response.setContentType("text/html;charset=utf-8");
            /* permit corss origin */
            response.setHeader("Access-Control-Allow-Origin","*");

            System.out.println("checking an admin");

            String rst = "";

            List<Admin> result = HibernateUtil.getSessionFactory()
                    .getCurrentSession().createQuery("from Admin").list(); 
            Iterator<Admin> it = result.iterator();
            
            while (it.hasNext()) {
                Admin admin = (Admin) it.next();
                String name = admin.getName();
                if(name.equals(adminname)){
                    System.out.println("find admin, name: ");
                    rst = admin.getPassword();
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
