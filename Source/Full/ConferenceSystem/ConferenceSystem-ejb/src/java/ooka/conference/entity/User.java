/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author bastian
 */
@Entity
@Table(name = "WEB_USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany
    private Set<Conference> conferencesAsOrganizer;

    @ManyToMany
    private Set<Conference> conferencesAsAuthor;

    @OneToMany
    private Set<ConferenceViewerAssociation> conferencesAsViewer;

    @ManyToMany
    private Set<Conference> conferencesAsReviewer;

    @OneToMany
    private Set<Review> reviews;
    
    private String name;

    public Set<Conference> getConferencesAsReviewer() {
        return conferencesAsReviewer;
    }

    public void setConferencesAsReviewer(Set<Conference> conferencesAsReviewer) {
        this.conferencesAsReviewer = conferencesAsReviewer;
    }

    public Set<Conference> getConferencesAsOrganizer() {
        return conferencesAsOrganizer;
    }

    public void setConferencesAsOrganizer(Set<Conference> conferencesAsOrganizer) {
        this.conferencesAsOrganizer = conferencesAsOrganizer;
    }

    public Set<Conference> getConferencesAsAuthor() {
        return conferencesAsAuthor;
    }

    public void setConferencesAsAuthor(Set<Conference> conferencesAsAuthor) {
        this.conferencesAsAuthor = conferencesAsAuthor;
    }

    public Set<ConferenceViewerAssociation> getConferencesAsViewer() {
        return conferencesAsViewer;
    }

    public void setConferencesAsViewer(Set<ConferenceViewerAssociation> conferencesAsViewer) {
        this.conferencesAsViewer = conferencesAsViewer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conference.entity.User[ id=" + id + " ]";
    }

}
