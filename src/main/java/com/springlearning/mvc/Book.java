package com.springlearning.mvc;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String isbn;
    private Calendar dateOfCreation;
    private Calendar dateOfPublication;
    private int editionNo;
    @ManyToOne
    private Publication publication;

    @ManyToMany
    private List<Contributor> authors;

    public Book(){super();}

    public Book(String isbn, Calendar dateOfCreation, Calendar dateOfPublication, int editionNo, Publication publication, List<Contributor> authors){
        super();
        this.isbn = isbn;
        this.dateOfCreation = dateOfCreation;
        this.dateOfPublication = dateOfPublication;
        this.editionNo = editionNo;
        this.publication = publication;
        this.authors = authors;
    }

    public Book(String isbn, Calendar dateOfCreation, Calendar dateOfPublication, int editionNo, Publication publication){
        super();
        this.isbn = isbn;
        this.dateOfCreation = dateOfCreation;
        this.dateOfPublication = dateOfPublication;
        this.editionNo = editionNo;
        this.publication = publication;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Calendar getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Calendar date_of_creation) {
        this.dateOfCreation = date_of_creation;
    }

    public Calendar getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Calendar date_of_publication) {
        this.dateOfPublication = date_of_publication;
    }

    public int getEditionNo() {
        return editionNo;
    }

    public void setEditionNo(int edition_no) {
        this.editionNo = edition_no;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication= publication;
    }

    public List<Contributor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Contributor> authors) {
        this.authors = authors;
    }

    @OneToMany(mappedBy = "isbn")
    private Collection<Chapter> chapter;

    public Collection<Chapter> getChapter() {
        return chapter;
    }

    public void setChapter(Collection<Chapter> chapter) {
        this.chapter = chapter;
    }

    @OneToMany(mappedBy = "book")
    private Collection<Order> order;

    public Collection<Order> getOrder() {
        return order;
    }

    public void setOrder(Collection<Order> order) {
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
