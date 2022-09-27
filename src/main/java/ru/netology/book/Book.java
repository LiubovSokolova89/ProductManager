package ru.netology.book;

import ru.netology.product.Product;

public class Book extends Product {
    protected String author;

    public Book() {
        super();
    }


    public Book(int id, String name, int price, String author) {
        super(id, name, price);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
