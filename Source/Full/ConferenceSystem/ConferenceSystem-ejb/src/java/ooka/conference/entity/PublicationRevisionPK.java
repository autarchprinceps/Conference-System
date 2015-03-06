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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Bastian
 */
@Embeddable
public class PublicationRevisionPK implements Serializable {

    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "author_id")
    private int authorId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "conference_id")
    private int conferenceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "file_name")
    private String fileName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "content_type")
    private String contentType;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public PublicationRevisionPK() {
    }

    public PublicationRevisionPK(int id, int authorId, int conferenceId, String fileName, String contentType) {
        this.id = id;
        this.authorId = authorId;
        this.conferenceId = conferenceId;
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id + authorId + conferenceId + fileName.hashCode() + contentType.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PublicationRevisionPK)) {
            return false;
        }
        PublicationRevisionPK other = (PublicationRevisionPK) object;

        return
                this.id == other.id &&
                this.authorId == other.authorId &&
                this.conferenceId == other.conferenceId &&
                this.fileName.equals(other.fileName) &&
                this.contentType.equals(other.contentType);
    }

    @Override
    public String toString() {
        return "ooka.conference.entity.PublicationRevisionPK[ id=" + id + ", authorId=" + authorId + ", conferenceId=" + conferenceId + ", fileName=" + fileName + ", contentType=" + contentType + " ]";
    }

}
