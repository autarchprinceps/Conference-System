/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author bastian
 */
@Entity
@Table(name = "CONFERENCE")
public class Conference implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "CONFERENCE_ORGANIZER")
    private User organizer;

    @ManyToMany
    @JoinColumn(name = "CONFERENCE_AUTHORS")
    private Set<User> authors;

    @OneToMany
    @JoinColumn(name = "CONFERENCE_VIEWER")
    private Set<ConferenceViewerAssociation> viewer;

    @ManyToMany
    @JoinColumn(name = "CONFERENCE_REVIEWER")
    private Set<User> reviewer;

    @OneToMany
    @JoinColumn(name = "CONFERENCE_PUBLICATIONS")
    private Set<Publication> publications;

    @Column(name = "conference_date")
    private Date date;

    private String name;

    private int participantLimit;

    public Set<User> getReviewer() {
        return reviewer;
    }

    public void setReviewer(Set<User> reviewer) {
        this.reviewer = reviewer;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public Set<ConferenceViewerAssociation> getViewer() {
        return viewer;
    }

    public void setViewer(Set<ConferenceViewerAssociation> viewer) {
        this.viewer = viewer;
    }

    public Set<User> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<User> authors) {
        this.authors = authors;
    }

    public Set<Publication> getPublications() {
        return publications;
    }

    public void setPublications(Set<Publication> publications) {
        this.publications = publications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getParticipantLimit() {
        return participantLimit;
    }

    public void setParticipantLimit(int participantLimit) {
        this.participantLimit = participantLimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conference)) {
            return false;
        }
        Conference other = (Conference) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conference.entity.Conference[ id=" + id + " ]";
    }

}
