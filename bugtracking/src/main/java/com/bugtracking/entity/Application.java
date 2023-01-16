package com.bugtracking.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "APPLICATION_TBL")
public class Application implements Serializable {
   @Id
   //@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "applicationSeq")
   //@SequenceGenerator(name="applicationSeq",sequenceName = "APPID",allocationSize = 1)
   @Column
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int application_id;
    public String description;
    @Column(name = "APPLICATION_NAME")
    public String name;
    public String owner;

    public int getApplication_id() {
        return application_id;
    }

    public void setApplication_id(int application_id) {
        this.application_id = application_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Application{" +
                "application_id=" + application_id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
