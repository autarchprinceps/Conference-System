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
import javax.persistence.Lob;
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
@Table(name = "conference_user_role", schema = "conference_system")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConferenceUserRole.findAll", query = "SELECT c FROM ConferenceUserRole c"),
    @NamedQuery(name = "ConferenceUserRole.findByUserId", query = "SELECT c FROM ConferenceUserRole c WHERE c.conferenceUserRolePK.userId = :userId"),
    @NamedQuery(name = "ConferenceUserRole.findByConferenceId", query = "SELECT c FROM ConferenceUserRole c WHERE c.conferenceUserRolePK.conferenceId = :conferenceId"),
    @NamedQuery(name = "ConferenceUserRole.findByConferenceIdAndUserId", query = "SELECT c FROM ConferenceUserRole c WHERE c.conferenceUserRolePK.conferenceId = :conferenceId AND c.conferenceUserRolePK.userId = :userId"),
    @NamedQuery(name = "ConferenceUserRole.deleteByConferenceId", query = "DELETE FROM ConferenceUserRole c WHERE c.conferenceUserRolePK.conferenceId = :conferenceId"),
    @NamedQuery(name = "ConferenceUserRole.deleteByConferenceIdAndUserId", query = "DELETE FROM ConferenceUserRole c WHERE c.conferenceUserRolePK.conferenceId = :conferenceId AND c.conferenceUserRolePK.userId = :userId")})
public class ConferenceUserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ConferenceUserRolePK conferenceUserRolePK;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "user_role")
    private String userRole;
    @JoinColumn(name = "conference_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Conference conference;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private User user;

    public ConferenceUserRole() {
    }

    public ConferenceUserRole(ConferenceUserRolePK conferenceUserRolePK) {
        this.conferenceUserRolePK = conferenceUserRolePK;
    }

    public ConferenceUserRole(ConferenceUserRolePK conferenceUserRolePK, String userRole) {
        this.conferenceUserRolePK = conferenceUserRolePK;
        this.userRole = userRole;
    }

    public ConferenceUserRole(int userId, int conferenceId) {
        this.conferenceUserRolePK = new ConferenceUserRolePK(userId, conferenceId);
    }

    public ConferenceUserRolePK getConferenceUserRolePK() {
        return conferenceUserRolePK;
    }

    public void setConferenceUserRolePK(ConferenceUserRolePK conferenceUserRolePK) {
        this.conferenceUserRolePK = conferenceUserRolePK;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
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
        hash += (conferenceUserRolePK != null ? conferenceUserRolePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConferenceUserRole)) {
            return false;
        }
        ConferenceUserRole other = (ConferenceUserRole) object;
        return !((this.conferenceUserRolePK == null && other.conferenceUserRolePK != null) || (this.conferenceUserRolePK != null && !this.conferenceUserRolePK.equals(other.conferenceUserRolePK)));
    }

    @Override
    public String toString() {
        return "ooka.conference.entity.ConferenceUserRole[ conferenceUserRolePK=" + conferenceUserRolePK + " ]";
    }

}
