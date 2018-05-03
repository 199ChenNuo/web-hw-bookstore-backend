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


/**
 * Servlet implementation class UserManagerServlet
 */
@WebServlet("/BookManager")
public class BookManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookManagerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            
            response.setContentType("text/html;charset=utf-8");
            response.setHeader("Access-Control-Allow-Origin","*");
            System.out.println("This is a book manager");

            List<Book> result = HibernateUtil.getSessionFactory()
                    .getCurrentSession().createQuery("from Book").list(); 
            Iterator<Book> it = result.iterator();
            System.out.println("before gen books");
            String books = new String();
            books = "{books:[";
            System.out.println("after gen books");
            while (it.hasNext()) {
                Book book = (Book) it.next();
                books += "{ID:";
                String bookid =  book.getId().toString();
                books += bookid;
                books += ",name:\"";
                books += (book.getName());
                books += "\",author:\"";
                books += book.getAuthor();
                books += "\",price:\"";
                books += (book.getPrice());
                books += ("\",year:\"");
                books += (book.getYear());
                books += ("\",storage:\"");
                books += (book.getStorage());
                books += ("\"},");        
                System.out.println("one book complete");
            }
            
            
            books += "]}";
            System.out.println("complete");
            response.getWriter().write(books);

            System.out.println(books);
         /*  
HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=utf-8");
            
            System.out.println("This is a book manager");

            List<Book> result = HibernateUtil.getSessionFactory()
                    .getCurrentSession().createQuery("from Book").list(); 
            Iterator<Book> it = result.iterator();
            
            ArrayList<JSONArray> booksJson = new ArrayList<JSONArray>();
            while (it.hasNext()) {
                Book book = (Book) it.next();
                ArrayList<String> arrayList = new ArrayList<String>();
                arrayList.add(book.getTitle());
                arrayList.add(book.getAuthor());
                arrayList.add(book.getLanguage());
                arrayList.add(book.getPublished());
                arrayList.add(book.getSales());                             
                booksJson.add(JSONArray.fromObject(arrayList));
            }
            JSONArray books = JSONArray.fromArray(booksJson.toArray());


            System.out.println(books);

           out.println(books);
           out.flush();
           out.close();
           HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
           */
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
