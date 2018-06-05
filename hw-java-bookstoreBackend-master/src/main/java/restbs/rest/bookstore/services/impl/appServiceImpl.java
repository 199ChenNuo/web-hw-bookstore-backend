package restbs.rest.bookstore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restbs.rest.bookstore.dao.AdminDao;
import restbs.rest.bookstore.dao.BookDao;
import restbs.rest.bookstore.dao.UserDao;
import restbs.rest.bookstore.entity.Admin;
import restbs.rest.bookstore.entity.Books;
import restbs.rest.bookstore.entity.User;
import restbs.rest.bookstore.services.appService;

import java.util.Iterator;

@Service
public class appServiceImpl implements appService {
    @Autowired
    BookDao bookDao;

    @Autowired
    AdminDao adminDao;

    @Autowired
    UserDao userDao;

    @SuppressWarnings("unchecked")
    public String GetAllBooks(){
        Iterable<Books> booklist = bookDao.findAll();

        String books = new String();
        books = "{[";
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

    public void AddBook(String name, String author, String price, String year, String storage){
        Books book = new Books();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        book.setYear(year);
        book.setStorage(storage);
        bookDao.save(book);
        System.out.println("add a book");
    }

    public String ModifyBook(Long id, String name, String author, String price, String year, String storage){
        Iterable<Books> books = bookDao.findById(id);
        Iterator<Books> it = books.iterator();
        if(it.hasNext()){
            Books book = (Books) it.next();
            book.setName(name);
            book.setAuthor(author);
            book.setPrice(price);
            book.setYear(year);
            book.setStorage(storage);
            bookDao.save(book);
            return "修改成功";
        }else{
            return "修改失败";
        }
    }

    public String AdminLogin(String adminname){
        System.out.println("adminname:"+adminname);
        Iterable<Admin> admins = adminDao.findByName(adminname);
        Iterator<Admin> it = admins.iterator();
        if(it.hasNext()){
            Admin admin = (Admin) it.next();
            System.out.println(admin.getPassword());
            return admin.getPassword();
        }else{
            return "";
        }
    }

    public String AddUser(String name, String password, String phone, String email){
        User user = new User(name, password, phone, email);
        userDao.save(user);
        return "add User Success";
    }

    public String CheckUser(String name, String password){
        System.out.println("user name:"+name);
        Iterable<User> users = userDao.findByName(name);
        Iterator<User> it = users.iterator();
        String rst="{user:[";
        if(it.hasNext()){
            User user = (User) it.next();
            String Name = user.getName();
            if(Name.equals(name)) {
                if(user.getPassword().equals(password)){
                    rst += "{ID:";
                    int uID = user.getId();
                    rst += uID;
                    rst += ",name:\"";
                    rst += user.getName();
                    rst += "\",password:\"";
                    rst += user.getPassword();
                    rst += "\",phone:\"";
                    rst += user.getPhone();
                    rst += "\",email:\"";
                    rst += user.getEmail();
                    rst += "\"}]}";
                }else{
                    rst = "";
                }
            }
        }else{
            rst = "";
        }
        System.out.println("user info:"+rst);
        return rst;
    }

    public String ModifyUser(int id, String name, String password, String phone, String email){
        Iterable<User> users = userDao.findById(id);
        Iterator<User> it = users.iterator();
        if(it.hasNext()){
            User user = (User) it.next();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setPhone(phone);
            userDao.save(user);
            return "修改成功";
        }else{
            return "修改失败";
        }
    }
}
