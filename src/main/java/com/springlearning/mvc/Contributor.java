package com.springlearning.mvc;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Contributor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String designation;
    private String jobType;
    private float salary;
    private String paymentFrequency;
    @ManyToMany
    private List<Publication> worksFor;
    public Contributor(){super();}

    public Contributor(String name, String designation, String jobType, float salary, String paymentFrequency) {
        super();
        this.name = name;
        this.designation = designation;
        this.jobType = jobType;
        this.salary = salary;
        this.paymentFrequency = paymentFrequency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    @ManyToMany(mappedBy = "authors")
    private Collection<Book> books;

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    @ManyToMany(mappedBy = "authors")
    private Collection<Article> articles;

    public Collection<Article> getArticles() {
        return articles;
    }

    public void setArticles(Collection<Article> articles) {
        this.articles = articles;
    }

    public List<Publication> getWorksFor() {
        return worksFor;
    }

    public void setWorksFor(List<Publication> worksFor) {
        this.worksFor = worksFor;
    }

    public Boolean deleteAPublication(Publication publication){

        if (worksFor.contains(publication)){
            worksFor.remove(publication);
            return true;
        }
        return false;
    }
    public Boolean addAPublication(Publication publication){

        if (worksFor.contains(publication) == false){
            worksFor.add(publication);
            return true;
        }
        return false;
    }
}
