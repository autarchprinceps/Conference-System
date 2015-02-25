/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bastian
 */
@Entity
@Table(name = "conference_rating", schema = "conference_system")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConferenceRating.findAll", query = "SELECT c FROM ConferenceRating c"),
    @NamedQuery(name = "ConferenceRating.findByUserId", query = "SELECT c FROM ConferenceRating c WHERE c.conferenceRatingPK.userId = :userId"),
    @NamedQuery(name = "ConferenceRating.findByConferenceId", query = "SELECT c FROM ConferenceRating c WHERE c.conferenceRatingPK.conferenceId = :conferenceId"),
    @NamedQuery(name = "ConferenceRating.findByRating", query = "SELECT c FROM ConferenceRating c WHERE c.rating = :rating"),
    @NamedQuery(name = "ConferenceRating.deleteByConferenceId", query = "DELETE FROM ConferenceRating c WHERE c.conferenceRatingPK.conferenceId = :conferenceId"),
})
public class ConferenceRating implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConferenceRatingPK conferenceRatingPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "rating")
    private int rating;
    @JoinColumn(name = "conference_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conference conference;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    public ConferenceRating() {
    }

    public ConferenceRating(ConferenceRatingPK conferenceRatingPK) {
        this.conferenceRatingPK = conferenceRatingPK;
    }

    public ConferenceRating(ConferenceRatingPK conferenceRatingPK, int rating) {
        this.conferenceRatingPK = conferenceRatingPK;
        this.rating = rating;
    }

    public ConferenceRating(int userId, int conferenceId) {
        this.conferenceRatingPK = new ConferenceRatingPK(userId, conferenceId);
    }

    public ConferenceRatingPK getConferenceRatingPK() {
        return conferenceRatingPK;
    }

    public void setConferenceRatingPK(ConferenceRatingPK conferenceRatingPK) {
        this.conferenceRatingPK = conferenceRatingPK;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conferenceRatingPK != null ? conferenceRatingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConferenceRating)) {
            return false;
        }
        ConferenceRating other = (ConferenceRating) object;
        if ((this.conferenceRatingPK == null && other.conferenceRatingPK != null) || (this.conferenceRatingPK != null && !this.conferenceRatingPK.equals(other.conferenceRatingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooka.conference.entity.ConferenceRating[ conferenceRatingPK=" + conferenceRatingPK + " ]";
    }

}
