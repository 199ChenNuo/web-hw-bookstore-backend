package ebook;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;


import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import ebook.HibernateUtil;
import ebook.Book;

@WebServlet("/ModifyBooks")
public class ModifyBooksServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//List<DOMTreeRow> rowList;sa
    JsonStructure parsed;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyBooksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private Long ID;
    private String Name;
    private String Author;
    private String Price;
    private String Year;  
    private String Storage;  
    

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
            String author = request.getParameter("author");
            String price = request.getParameter("price");
            String year = request.getParameter("year");
            String storage = request.getParameter("storage");
            
            List<Book> result = HibernateUtil.getSessionFactory()
                    .getCurrentSession().createQuery("from Book").list(); 
            Iterator<Book> it = result.iterator();
            
            Book book = new Book();
            
            while (it.hasNext()) {
            	book = (Book) it.next();
            	Long bID = book.getId();
            	if(bID==ID) {
            		book.setName(name);
                    book.setAuthor(author);
                    book.setPrice(price);
                    book.setStorage(storage);
                    book.setYear(year);
                    break;
            	}
            }        
            
            System.out.println("complete");
            
            session.save(book);
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