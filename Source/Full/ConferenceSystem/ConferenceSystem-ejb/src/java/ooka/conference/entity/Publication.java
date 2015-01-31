/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bastian
 */
@Entity
@Table(name = "publication", schema = "conference_system")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publication.findAll", query = "SELECT p FROM Publication p"),
    @NamedQuery(name = "Publication.findByAuthorId", query = "SELECT p FROM Publication p WHERE p.publicationPK.authorId = :authorId"),
    @NamedQuery(name = "Publication.findByConferenceId", query = "SELECT p FROM Publication p WHERE p.publicationPK.conferenceId = :conferenceId"),
    @NamedQuery(name = "Publication.findByConferenceIdAndAuthorId", query = "SELECT p FROM Publication p WHERE p.publicationPK.conferenceId = :conferenceId AND p.publicationPK.authorId = :authorId"),
    @NamedQuery(name = "Publication.findByTitle", query = "SELECT p FROM Publication p WHERE p.title = :title"),
    @NamedQuery(name = "Publication.findByText", query = "SELECT p FROM Publication p WHERE p.text = :text")})
public class Publication implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PublicationPK publicationPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "title")
    private String title;
    @Size(max = 2147483647)
    @Column(name = "text")
    private String text;
    @JoinColumn(name = "conference_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conference conference;
    @JoinColumn(name = "author_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    public Publication() {
    }

    public Publication(PublicationPK publicationPK) {
        this.publicationPK = publicationPK;
    }

    public Publication(PublicationPK publicationPK, String title) {
        this.publicationPK = publicationPK;
        this.title = title;
    }

    public Publication(int authorId, int conferenceId) {
        this.publicationPK = new PublicationPK(authorId, conferenceId);
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
        hash += (publicationPK != null ? publicationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publication)) {
            return false;
        }
        Publication other = (Publication) object;
        if ((this.publicationPK == null && other.publicationPK != null) || (this.publicationPK != null && !this.publicationPK.equals(other.publicationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooka.conference.entity.Publication[ publicationPK=" + publicationPK + " ]";
    }

}
