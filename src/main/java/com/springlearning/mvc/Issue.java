package com.springlearning.mvc;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date dateOfIssue;

    @ManyToOne
    private Publication publicationId;


    public Issue() {super();}

    public Issue(Date dateOfIssue, Publication publicationId) {
        super();
        this.dateOfIssue = dateOfIssue;
        this.publicationId = publicationId;
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

    public Publication getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Publication publicationId) {
        this.publicationId = publicationId;
    }

    @ManyToMany(mappedBy = "issues")
    private Collection<Article> articles;

    public Collection<Article> getArticles() {
        return articles;
    }

    public void setArticles(Collection<Article> articles) {
        this.articles = articles;
    }
}
