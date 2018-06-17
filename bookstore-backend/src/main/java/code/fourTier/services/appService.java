package code.fourTier.services;


import java.sql.Date;

public interface appService {
    public String GetAllBooks();

    public void AddBook(String name, String author, String price, String year, int storage, String category);

    public String ModifyBook(Long id, String name, String author, String price, String year, int storage, String category);

    public String AdminLogin(String adminname);

    public String AddUser(String name, String password, String phone, String email);

    public String CheckUser(String name, String password);

    public String ModifyUser(int id, String name, String password, String phone, String email);

    public String GetAllUsers();

    public String DeleteBook(Long ID);

    public String AddOrder(int userId, Long bookId, int amount, double price);

    public String PrevOrder(int userId);

    public String PrevOrderByDate(int userId, Date beginDate, Date endDate);

    public String GetAllOrdersByDate(Date beginDate, Date endDate);

    public String GetAllOrders();
}
