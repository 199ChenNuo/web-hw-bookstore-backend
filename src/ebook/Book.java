package ebook;

public class Book {

	private Long id;

    private String name;
    private String author;
    private String price;
    private String year;
    private String storage;


    public Book() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getAuthor() {
    	return author;
    }
    
    public void setAuthor(String author) {
    	this.author = author;
    }
    
    public String getYear() {
    	return year;
    }
    
    public void setYear(String year) {
    	this.year = year;
    }
    public String getPrice() {
    	return price;
    }
    
    public void setPrice(String price) {
    	this.price = price;
    }
    
    public String getStorage() {
    	return storage;
    }
    
    public void setStorage(String storage) {
    	this.storage = storage;
    }
}
