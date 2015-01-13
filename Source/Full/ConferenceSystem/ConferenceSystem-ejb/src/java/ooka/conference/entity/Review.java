/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bastian
 */
@Entity
@Table(name = "publication_review", schema = "conference_system")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
    @NamedQuery(name = "Review.findByAuthorId", query = "SELECT r FROM Review r WHERE r.reviewPK.authorId = :authorId"),
    @NamedQuery(name = "Review.findByConferenceId", query = "SELECT r FROM Review r WHERE r.reviewPK.conferenceId = :conferenceId"),
    @NamedQuery(name = "Review.findByText", query = "SELECT r FROM Review r WHERE r.text = :text")})
public class Review implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReviewPK reviewPK;
    @Size(max = 2147483647)
    @Column(name = "text")
    private String text;
    @JoinColumn(name = "conference_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conference conference;
    @JoinColumn(name = "author_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    public Review() {
    }

    public Review(ReviewPK reviewPK) {
        this.reviewPK = reviewPK;
    }

    public Review(int authorId, int conferenceId) {
        this.reviewPK = new ReviewPK(authorId, conferenceId);
    }

    public ReviewPK getReviewPK() {
        return reviewPK;
    }

    public void setReviewPK(ReviewPK reviewPK) {
        this.reviewPK = reviewPK;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        hash += (reviewPK != null ? reviewPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.reviewPK == null && other.reviewPK != null) || (this.reviewPK != null && !this.reviewPK.equals(other.reviewPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooka.conference.entity.Review[ reviewPK=" + reviewPK + " ]";
    }
    
}
