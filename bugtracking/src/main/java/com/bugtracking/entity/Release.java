package com.bugtracking.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "RELEASE_TBL")
public class Release implements Serializable {
    @Id
   // @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "releaseIdSeq")
    //@SequenceGenerator(name="releaseIdSeq",sequenceName = "RELEASEID",allocationSize = 1)
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int release_id;
    public String description;
    @Column(name = "RELEASE_DATE")
    public Timestamp releaseDate;

    public int getRelease_id() {
        return release_id;
    }

    public void setRelease_id(int release_id) {
        this.release_id = release_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
    }
}
