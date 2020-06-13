package com.springlearning.mvc;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date dateOfIssue;

    @ManyToOne
    private Publication publication;


    public Issue() {super();}

    public Issue(Date dateOfIssue, Publication publication) {
        super();
        this.dateOfIssue = dateOfIssue;
        this.publication = publication;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publicationId) {
        this.publication = publicationId;
    }

    @ManyToMany(mappedBy = "issues")
    private Collection<Article> articles;

    public Collection<Article> getArticles() {
        return articles;
    }

    public void setArticles(Collection<Article> articles) {
        this.articles = articles;
    }

    @OneToMany(mappedBy = "issue")
    private Collection<Order> order;

    public Collection<Order> getOrder() {
        return order;
    }

    public void setOrder(Collection<Order> order) {
        this.order = order;
    }
}
