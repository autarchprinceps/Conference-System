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
 * @author Bastian
 */
@Entity
@Table(name = "publication_revision", schema = "conference_system")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PublicationRevision.findAll", query = "SELECT p FROM PublicationRevision p"),
    @NamedQuery(name = "PublicationRevision.findById", query = "SELECT p FROM PublicationRevision p WHERE p.publicationRevisionPK.id = :id"),
    @NamedQuery(name = "PublicationRevision.findByAuthorId", query = "SELECT p FROM PublicationRevision p WHERE p.publicationRevisionPK.authorId = :authorId"),
    @NamedQuery(name = "PublicationRevision.findByConferenceId", query = "SELECT p FROM PublicationRevision p WHERE p.publicationRevisionPK.conferenceId = :conferenceId")})
public class PublicationRevision implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PublicationRevisionPK publicationRevisionPK;
    @Lob
    @Column(name = "content")
    private byte[] content;
    @JoinColumn(name = "conference_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Conference conference;
    @JoinColumn(name = "author_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @Basic(optional = false)
    @NotNull
    @Column(name = "file_name")
    private String fileName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "content_type")
    private String contentType;

    public PublicationRevision() {
    }

    public PublicationRevision(PublicationRevisionPK publicationRevisionPK) {
        this.publicationRevisionPK = publicationRevisionPK;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public PublicationRevisionPK getPublicationRevisionPK() {
        return publicationRevisionPK;
    }

    public void setPublicationRevisionPK(PublicationRevisionPK publicationRevisionPK) {
        this.publicationRevisionPK = publicationRevisionPK;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
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
        return publicationRevisionPK != null ? publicationRevisionPK.hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicationRevision)) {
            return false;
        }
        PublicationRevision other = (PublicationRevision) object;
        if ((this.publicationRevisionPK == null && other.publicationRevisionPK != null) || (this.publicationRevisionPK != null && !this.publicationRevisionPK.equals(other.publicationRevisionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooka.conference.entity.PublicationRevision[ publicationRevisionPK=" + publicationRevisionPK + " ]";
    }
    
}
