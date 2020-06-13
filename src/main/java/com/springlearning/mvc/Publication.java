package com.springlearning.mvc;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Collection;

@Entity
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String typicalTopics;
    private String type;
    private String periodicity;

    public Publication(){super();}

    public Publication(String title, String typicalTopics, String type, String periodicity){
        super();
        this.title = title;
        this.typicalTopics = typicalTopics;
        this.type = type;
        this.periodicity = periodicity;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypicalTopics() {
        return typicalTopics;
    }

    public void setTypicalTopics(String typical_topics) {
        this.typicalTopics = typical_topics;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToMany(mappedBy = "worksFor")
    private Collection<Contributor> contributors;

    public Collection<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(Collection<Contributor> contributors) {
        this.contributors = contributors;
    }


}
