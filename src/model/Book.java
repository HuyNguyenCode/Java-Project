package model;

public class Book {
    private int id;
    private int year;
    private int stock;
    private double price;
    private String title;
    private String ImageSrc;
    private String author;
    private String publisher;
    private String category;

    
    
    public Book(int id, int year, int stock, double price, String title, String imageSrc, String author,
            String publisher, String category) {
        this.id = id;
        this.year = year;
        this.stock = stock;
        this.price = price;
        this.title = title;
        ImageSrc = imageSrc;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
    }

    public Book() {
        this.id = 0;
        this.year = 0;
        this.stock = 0;
        this.price = 0;
        this.title = "";
        this.author = "";
        this.publisher = "";
        this.category = "";
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String name) {
        this.title = name;
    }
    public String getImageSrc() {
        return ImageSrc;
    }
    public void setImageSrc(String imageSrc) {
        ImageSrc = imageSrc;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
}
