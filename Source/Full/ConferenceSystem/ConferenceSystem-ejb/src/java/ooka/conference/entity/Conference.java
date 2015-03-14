/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bastian
 */
@Entity
@Table(name = "conference", schema = "conference_system")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conference.findAll", query = "SELECT c FROM Conference c"),
    @NamedQuery(name = "Conference.searchNameStartingWith", query = "SELECT p FROM Publication p WHERE p.title LIKE :name"),
    @NamedQuery(name = "Conference.findById", query = "SELECT c FROM Conference c WHERE c.id = :id"),
    @NamedQuery(name = "Conference.findByName", query = "SELECT c FROM Conference c WHERE c.name = :name"),
    @NamedQuery(name = "Conference.findByParticipantLimit", query = "SELECT c FROM Conference c WHERE c.participantLimit = :participantLimit"),
    @NamedQuery(name = "Conference.findByDate", query = "SELECT c FROM Conference c WHERE c.date = :date")})
public class Conference implements Serializable {

    @Column(name = "participant_limit")
    private Integer participantLimit;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conference", fetch = FetchType.LAZY)
    private Collection<Publication> publicationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conference", fetch = FetchType.LAZY)
    private Collection<PublicationReview> reviewCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conference", fetch = FetchType.LAZY)
    private Collection<ConferenceUserRole> conferenceUserRoleCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conference", fetch = FetchType.LAZY)
    private Collection<ConferenceRating> conferenceRatingCollection;

    public Conference() {
    }

    public Conference(Integer id) {
        this.id = id;
    }

    public Conference(Integer id, String name, int participantLimit, Date date) {
        this.id = id;
        this.name = name;
        this.participantLimit = participantLimit;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParticipantLimit() {
        return participantLimit;
    }

    public void setParticipantLimit(int participantLimit) {
        this.participantLimit = participantLimit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public Collection<Publication> getPublicationCollection() {
        return publicationCollection;
    }

    public void setPublicationCollection(Collection<Publication> publicationCollection) {
        this.publicationCollection = publicationCollection;
    }

    @XmlTransient
    public Collection<PublicationReview> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<PublicationReview> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    @XmlTransient
    public Collection<ConferenceUserRole> getConferenceUserRoleCollection() {
        return conferenceUserRoleCollection;
    }

    public void setConferenceUserRoleCollection(Collection<ConferenceUserRole> conferenceUserRoleCollection) {
        this.conferenceUserRoleCollection = conferenceUserRoleCollection;
    }

    @XmlTransient
    public Collection<ConferenceRating> getConferenceRatingCollection() {
        return conferenceRatingCollection;
    }

    public void setConferenceRatingCollection(Collection<ConferenceRating> conferenceRatingCollection) {
        this.conferenceRatingCollection = conferenceRatingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conference)) {
            return false;
        }
        Conference other = (Conference) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "ooka.conference.entity.Conference[ id=" + id + " ]";
    }

    public void setParticipantLimit(Integer participantLimit) {
        this.participantLimit = participantLimit;
    }

}
