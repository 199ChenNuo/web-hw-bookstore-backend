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

import org.hibernate.Session;

import net.sf.json.JSONArray;
import ebook.HibernateUtil;
import ebook.User;


/**
 * Servlet implementation class UserManagerServlet
 */
@WebServlet("/UserManagerServlet")
public class UserManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserManagerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
          
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            
            System.out.println("This is a use manager");

            
            
            List<User> result = HibernateUtil.getSessionFactory()
                    .getCurrentSession().createQuery("from User").list(); 
            Iterator<User> it = result.iterator();
            
            
            
            ArrayList<JSONArray> usersJson = new ArrayList<JSONArray>();
            while (it.hasNext()) {
            	User user = (User) it.next();
                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.add(user.getName());
                arrayList.add(user.getPassword());
                arrayList.add(user.getPhone());
                arrayList.add(user.getEmail());                            
                usersJson.add(JSONArray.fromObject(arrayList));
            }
            JSONArray users = JSONArray.fromArray(usersJson.toArray());

            
            
            
            System.out.println(users);

           out.println(users);

           out.flush();
           out.close();
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

	
	
	@SuppressWarnings("unused")
	private void createAndStoreUser(String firstname, String lastname, String phone, String email) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        User user = new User();
        user.setName(firstname);
        user.setPassword(lastname);
        user.setPhone(phone);
        user.setEmail(email);
        session.save(user);

        session.getTransaction().commit();
    }
}
