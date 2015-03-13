/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bastian
 */
@Entity
@Table(name = "publication", schema = "conference_system")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publication.findAll", query = "SELECT p FROM Publication p"),
    @NamedQuery(name = "Publication.searchTitleStartingWith", query = "SELECT p FROM Publication p WHERE p.title LIKE ':title%'"),
    @NamedQuery(name = "Publication.findByTitle", query = "SELECT p FROM Publication p WHERE p.title = :title"),
    @NamedQuery(name = "Publication.findByConferenceId", query = "SELECT p FROM Publication p WHERE p.publicationPK.conferenceId = :conferenceId"),
    @NamedQuery(name = "Publication.findByAuthorId", query = "SELECT p FROM Publication p WHERE p.publicationPK.authorId = :authorId"),
    @NamedQuery(name = "Publication.findByConferenceIdAndAuthorId", query = "SELECT p FROM Publication p WHERE p.publicationPK.authorId = :authorId AND p.publicationPK.conferenceId = :conferenceId"),
    @NamedQuery(name = "Publication.deleteByConferenceIdAndAuthorId", query = "DELETE FROM Publication c WHERE c.publicationPK.conferenceId = :conferenceId AND c.publicationPK.authorId = :userId")})
public class Publication implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PublicationPK publicationPK;
    @Size(max = 255)
    @Column(name = "title")
    private String title;
    @JoinColumn(name = "conference_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Conference conference;
    @JoinColumn(name = "author_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumns({
        @JoinColumn(name = "author_id", referencedColumnName = "author_id", insertable = false, updatable = false),
        @JoinColumn(name = "conference_id", referencedColumnName = "conference_id", insertable = false, updatable = false)
    })
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<PublicationRevision> revisions;

    
    public Publication() {
    }

    public Publication(PublicationPK publicationPK) {
        this.publicationPK = publicationPK;
    }

    public Publication(int conferenceId, int authorId) {
        this.publicationPK = new PublicationPK(conferenceId, authorId);
    }

    public PublicationPK getPublicationPK() {
        return publicationPK;
    }

    public void setPublicationPK(PublicationPK publicationPK) {
        this.publicationPK = publicationPK;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return publicationPK != null ? publicationPK.hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publication)) {
            return false;
        }
        Publication other = (Publication) object;
        return !((this.publicationPK == null && other.publicationPK != null) || (this.publicationPK != null && !this.publicationPK.equals(other.publicationPK)));
    }

    @Override
    public String toString() {
        return "ooka.conference.entity.Publication[ publicationPK=" + publicationPK + " ]";
    }

    public Collection<PublicationRevision> getRevisions() {
        return revisions;
    }

    public void setRevisions(Collection<PublicationRevision> revisions) {
        this.revisions = revisions;
    }

}
