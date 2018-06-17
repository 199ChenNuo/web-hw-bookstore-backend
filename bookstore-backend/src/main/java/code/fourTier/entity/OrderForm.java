package code.fourTier.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class OrderForm {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private int userid;
    private Long bookid;
    private int amount;
    private double price;
    private Date date;

    public OrderForm(){};

    public OrderForm(int userId, Long bookId, int amount, double price, Date date){
        this.userid = userId;
        this.bookid = bookId;
        this.amount = amount;
        this.price = price;
        this.date = date;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getUserid(){
        return userid;
    }

    public void setUserid(int userId){
        this.userid = userId;
    }

    public Long getBookid(){
        return bookid;
    }

    public void setBookid(Long bookId){
        this.bookid = bookId;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public Date getDate(){ return date; }

    public void setDate(Date date){ this.date = date; }
}
