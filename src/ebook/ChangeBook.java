package ebook;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;


import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
import ebook.User;

/**
 * Servlet implementation class JsonServlet
 */
@WebServlet("/ChangeBook")
public class ChangeBook extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//List<DOMTreeRow> rowList;sa
    JsonStructure parsed;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeBook() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private Long BookId;
    private String coltype;
    private String newval;
    
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String content = request.getParameter("content");
        parseJson(content);
        
        
        PrintWriter out = response.getWriter();
        out.println(buildJson());        
    
    
    }
    
    public void parseJson(String content) {
        /* Parse the data using the document object model approach */
        try (JsonReader reader = Json.createReader(new StringReader(content))) {
            parsed = reader.readObject();
        }

        /* Represent the DOM tree on a list for a JSF table */
        this.printTree(parsed, 0, "");
        
    }
    
    /* Used to populate rowList to display the DOM tree on a JSF table */
    public void printTree(JsonValue tree, int level, String key) {

        switch (tree.getValueType()) {
            case OBJECT:
                JsonObject object = (JsonObject) tree;
                for (int i = 0; i < level; i++)
                	System.out.print(" ");
                System.out.println( level + " " + tree.getValueType().toString()  + "| " +  key + "--");
                for (String name : object.keySet()) {
                   this.printTree(object.get(name), level+1, name);
                }
                break;
            case ARRAY:
                JsonArray array = (JsonArray) tree;
                for (int i = 0; i < level; i++)
                	System.out.print(" ");
             
                System.out.println( level + " " + tree.getValueType().toString() + " " + key + "--");

                
                for (JsonValue val : array) {
                    this.printTree(val, level+1, "");
                }
                break;
            case STRING:
                JsonString st = (JsonString) tree;
                for (int i = 0; i < level; i++)
                	System.out.print(" ");
                System.out.println( level + " " + tree.getValueType().toString() + " " + key + " " + st.getString());
                switch(key) {
            	case "id":
            		this.BookId=Long.valueOf(st.getString()).longValue();
            		break;
            	case "coltype":
            		this.coltype=st.getString();
            		break;
            	case "new_val":
            		this.newval=st.getString();
            		break;
            	        
            };            
                break;
            case NUMBER:
                JsonNumber num = (JsonNumber) tree;
                for (int i = 0; i < level; i++)
                	System.out.print(" ");
                System.out.println( level + " " + tree.getValueType().toString() + " " + key + " " + num.toString());
                break;
            case FALSE:
            case TRUE:
            case NULL:
                String valtype = tree.getValueType().toString();
                for (int i = 0; i < level; i++)
                	System.out.print(" ");
                System.out.println( level + " " + valtype + " " + key + " " + valtype.toLowerCase());
                break;
        }
    }
    


	public String buildJson() {        
        /* Build JSON Object Model */

		 System.out.println("-----2----");

		JsonObject model;
		
		model = Json.createObjectBuilder()
	           .add("add", "successful")	            
	       .build();
	

        /* Write JSON Output */
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.writeObject(model);
        }
        //return stWriter.toString();
        
        /* Write formatted JSON Output */
        Map<String,String> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, "");
        JsonWriterFactory factory = Json.createWriterFactory(config);
        
        StringWriter stWriterF = new StringWriter();
        try (JsonWriter jsonWriterF = factory.createWriter(stWriterF)) {
            jsonWriterF.writeObject(model);
        }                
    
        return stWriterF.toString();
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        
        System.out.println("---------");
		
        try {
            // Begin unit of work
        	Class.forName("com.mysql.jdbc.Driver");
    		String url = "jdbc:mysql://localhost:3306/bs";
    		String user = "root";
    		String passwd = "123456";
    		Connection con = DriverManager.getConnection(url, user, passwd);
    		System.out.println(con.getTransactionIsolation());

    		Statement stmt = con.createStatement();
    		stmt.executeUpdate("UPDATE `myu`.`books` SET `"+this.coltype+"` = '"+this.newval+"' WHERE `id` = '"+this.BookId+"';");


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
