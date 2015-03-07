/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author bastian
 */
@Embeddable
public class ReviewPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "reviewer_id")
    private int reviewerId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "author_id")
    private int authorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conference_id")
    private int conferenceId;

    public ReviewPK() {
    }

    public ReviewPK(int reviewerId, int authorId, int conferenceId) {
        this.reviewerId = reviewerId;
        this.authorId = authorId;
        this.conferenceId = conferenceId;
    }

    public int getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) authorId;
        hash += (int) conferenceId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReviewPK)) {
            return false;
        }
        ReviewPK other = (ReviewPK) object;
        if (this.authorId != other.authorId) {
            return false;
        }
        if (this.conferenceId != other.conferenceId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooka.conference.entity.ReviewPK[ authorId=" + authorId + ", conferenceId=" + conferenceId + " ]";
    }
    
}
