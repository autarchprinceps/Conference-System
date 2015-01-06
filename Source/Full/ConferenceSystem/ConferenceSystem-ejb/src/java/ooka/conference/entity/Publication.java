/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.entity;

import java.io.Serializable;
import java.util.Set;
import javax.annotation.Generated;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author bastian
 */
@Entity
@Table(name = "PUBLICATION")
public class Publication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Conference conference;

    @ManyToOne
    private User author;

    @ManyToOne
    private Set<Review> reviews;

    private String name;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     public PublicationId getId() {
     return id;
     }

     public void setPublicationId(PublicationId id) {
     this.id = id;
     }
     */
    /*
    @Override
    public int hashCode() {
        int hash = 0;
        hash += ((PublicationId) id).hashCode();
        return hash;
    }
*/
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publication)) {
            return false;
        }
        Publication other = (Publication) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "conference.entity.Publication[ id=" + id + " ]";
    }
/*
    @Embeddable
    class PublicationId implements Serializable {

        int author_id;
        int conference_id;
    }
*/
}
