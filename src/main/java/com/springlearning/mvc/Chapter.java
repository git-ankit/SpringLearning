package com.springlearning.mvc;


import javax.persistence.*;

@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private int number;
    private String name;
    private String content;

    @ManyToOne
    private Book isbn;

    public Chapter(){super();}

    public Chapter(int number, String name, String content, Book isbn) {
        super();
        this.number = number;
        this.name = name;
        this.content = content;
        this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Book getIsbn() {
        return isbn;
    }

    public void setIsbn(Book isbn) {
        this.isbn = isbn;
    }
}
