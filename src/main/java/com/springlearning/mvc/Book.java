package com.springlearning.mvc;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String isbn;
    private Date dateOfCreation;
    private Date dateOfPublication;
    private int editionNo;
    @ManyToOne
    private Publication publicationId;

    @ManyToMany
    private List<Contributor> authors;

    public Book(){super();}

    public Book(String isbn, Date dateOfCreation, Date dateOfPublication, int editionNo, Publication publicationId, List<Contributor> authors){
        super();
        this.isbn = isbn;
        this.dateOfCreation = dateOfCreation;
        this.dateOfPublication = dateOfPublication;
        this.editionNo = editionNo;
        this.publicationId = publicationId;
        this.authors = authors;
    }

    public Book(Date dateOfCreation, Date dateOfPublication, int editionNo, Publication publicationId){
        super();
        this.dateOfCreation = dateOfCreation;
        this.dateOfPublication = dateOfPublication;
        this.editionNo = editionNo;
        this.publicationId = publicationId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date date_of_creation) {
        this.dateOfCreation = date_of_creation;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date date_of_publication) {
        this.dateOfPublication = date_of_publication;
    }

    public int getEditionNo() {
        return editionNo;
    }

    public void setEditionNo(int edition_no) {
        this.editionNo = edition_no;
    }

    public Publication getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Publication publication_id) {
        this.publicationId = publication_id;
    }

    public List<Contributor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Contributor> authors) {
        this.authors = authors;
    }
}
