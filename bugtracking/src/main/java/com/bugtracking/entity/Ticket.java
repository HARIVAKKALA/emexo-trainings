package com.bugtracking.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TICKET_TBL")
public class Ticket implements Serializable {
    @Id
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ticketIdSeq")
   // @SequenceGenerator(name="ticketIdSeq",sequenceName = "TICKETID",allocationSize = 1)
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int ticket_id;

    @ManyToOne
    @JoinColumn(name = "application_id")
    public Application application;
    public String description;
    @Column(name = "Title")
    public String label;
    public String status;
    @ManyToOne
    @JoinColumn(name = "release_id")
    public Release release;

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }
}
