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
public class ConferenceUserRolePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conference_id")
    private int conferenceId;

    public ConferenceUserRolePK() {
    }

    public ConferenceUserRolePK(int userId, int conferenceId) {
        this.userId = userId;
        this.conferenceId = conferenceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        hash += (int) userId;
        hash += (int) conferenceId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConferenceUserRolePK)) {
            return false;
        }
        ConferenceUserRolePK other = (ConferenceUserRolePK) object;
        return this.userId == other.userId && this.conferenceId == other.conferenceId;
    }

    @Override
    public String toString() {
        return "ooka.conference.entity.ConferenceUserRolePK[ userId=" + userId + ", conferenceId=" + conferenceId + " ]";
    }
    
}
