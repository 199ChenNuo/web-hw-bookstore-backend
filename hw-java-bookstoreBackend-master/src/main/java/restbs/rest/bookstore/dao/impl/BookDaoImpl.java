/*
package restbs.rest.bookstore.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import restbs.rest.bookstore.HibernateUtil;
import restbs.rest.bookstore.dao.BookDao;
import restbs.rest.bookstore.model.Books;

import java.util.Iterator;
import java.util.List;


public class BookDaoImpl extends HibernateDaoSupport implements BookDao {
    @SuppressWarnings("unchecked")
    public String GetAllBooks(){
        List<Books> booklist = HibernateUtil.getSessionFactory()
                .getCurrentSession().createQuery("from Book").list();

        String books = new String();
        Iterator<Books> it = booklist.iterator();
        System.out.println("before gen books");

        System.out.println("after gen books");
        while (it.hasNext()) {
            Books book = (Books) it.next();
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
        return books;
    }
}
*/
