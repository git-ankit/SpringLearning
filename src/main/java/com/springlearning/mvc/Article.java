package com.springlearning.mvc;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date dateOfCreation;
    private String content;
    @ManyToMany
    private List<Issue> issues;
    @ManyToMany
    private List<Contributor> authors;
    public Article(){super();}

    public Article(Date dateOfCreation, String content, List<Issue> issues, List<Contributor> authors) {
        super();
        this.dateOfCreation = dateOfCreation;
        this.content = content;
        this.issues = issues;
        this.authors = authors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public List<Contributor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Contributor> authors) {
        this.authors = authors;
    }

}
