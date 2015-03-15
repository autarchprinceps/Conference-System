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
 * @author Bastian
 */
@Embeddable
public class PublicationPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "conference_id")
    private int conferenceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "author_id")
    private int authorId;

    public PublicationPK() {
    }

    public PublicationPK(int conferenceId, int authorId) {
        this.conferenceId = conferenceId;
        this.authorId = authorId;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) conferenceId;
        hash += (int) authorId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicationPK)) {
            return false;
        }
        PublicationPK other = (PublicationPK) object;
        return this.conferenceId == other.conferenceId && this.authorId == other.authorId;
    }

    @Override
    public String toString() {
        return "ooka.conference.entity.PublicationPK[ conferenceId=" + conferenceId + ", authorId=" + authorId + " ]";
    }
    
}
