package code.fourTier.services.impl;

import code.fourTier.dao.BookDao;
import code.fourTier.dao.OrderFormDao;
import code.fourTier.dao.UserDao;
import code.fourTier.entity.Admin;
import code.fourTier.entity.Books;
import code.fourTier.entity.OrderForm;
import code.fourTier.entity.User;
import com.sun.deploy.panel.ITreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import code.fourTier.dao.AdminDao;
import code.fourTier.services.appService;

import java.util.Iterator;

@Service
public class appServiceImpl implements appService {
    @Autowired
    BookDao bookDao;

    @Autowired
    AdminDao adminDao;

    @Autowired
    UserDao userDao;

    @Autowired
    OrderFormDao orderFormDao;

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

    public void AddBook(String name, String author, String price, String year, int storage){
        Books book = new Books();
        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        book.setYear(year);
        book.setStorage(storage);
        bookDao.save(book);
        System.out.println("add a book");
    }

    public String ModifyBook(Long id, String name, String author, String price, String year, int storage){
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

    public String DeleteBook(Long ID){
        System.out.println("delete book");
        Iterable<Books> books = bookDao.findById(ID);
        Iterator<Books> it = books.iterator();
        if(it.hasNext()){
            Books book = (Books) it.next();
            bookDao.delete(book);
            return "删除书本成功";
        }else{
            return "删除书本失败";
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
        return "注册成功";
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

    public String AddOrder(int userId, Long bookId, int amount, double price){

        Iterable<Books> books = bookDao.findById(bookId);
        Iterator<Books> it = books.iterator();
        if(it.hasNext()){
            OrderForm orderForm = new OrderForm(userId, bookId, amount, price);
            orderFormDao.save(orderForm);
            Books book = (Books) it.next();
            book.setStorage(book.getStorage() - amount);
            bookDao.save(book);
            return "购买成功";
        }else{
            return "购买失败";
        }
    }

    public String PrevOrder(int userId){
        Iterable<OrderForm> orders = orderFormDao.findByUserid(userId);
        Iterator<OrderForm> it = orders.iterator();
        String rst="{[";
        while(it.hasNext()){
            OrderForm orderForm = (OrderForm) it.next();
            rst += "{ID:\"";
            String orderid =  String.valueOf(orderForm.getId());
            rst += orderid;

            Iterable<Books> books = bookDao.findById(orderForm.getBookid());
            Iterator<Books> bit = books.iterator();
            if(bit.hasNext()){
                Books book = bit.next();
                String bookname = book.getName();
                String bookauthor = book.getAuthor();
                String bookyear = book.getYear();
                rst += "\",name:\"";
                rst += bookname;
                rst += "\",author:\"";
                rst += bookauthor;
                rst += "\",year:\"";
                rst += bookyear;
            }
            rst += "\",price:\"";
            rst += orderForm.getPrice();
            rst += "\",count:\"";
            rst += orderForm.getAmount();
            rst += "\"},";
        }
        rst += "]}";
        return rst;
    }
}
