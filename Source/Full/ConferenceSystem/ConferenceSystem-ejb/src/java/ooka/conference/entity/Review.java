/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Review.findByUserId", query = "SELECT r FROM Review r WHERE r.reviewPK.reviewerId = :reviewerId"),
    @NamedQuery(name = "Review.findByConferenceId", query = "SELECT r FROM Review r WHERE r.reviewPK.conferenceId = :conferenceId"),
    @NamedQuery(name = "Review.findByPublication", query = "SELECT r FROM Review r WHERE r.reviewPK.conferenceId = :conferenceId AND r.reviewPK.authorId = :authorId")
})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReviewPK reviewPK;
    @Lob
    @Column(name = "content")
    private byte[] content;
    @Basic(optional = false)
    @NotNull
    @Column(name = "file_name")
    private String fileName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "content_type")
    private String contentType;

    @JoinColumn(name = "conference_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conference conference;
    @JoinColumn(name = "author_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Review() {
    }

    public Review(ReviewPK reviewPK) {
        this.reviewPK = reviewPK;
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public ReviewPK getReviewPK() {
        return reviewPK;
    }

    public void setReviewPK(ReviewPK reviewPK) {
        this.reviewPK = reviewPK;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        return !((this.reviewPK == null && other.reviewPK != null) || (this.reviewPK != null && !this.reviewPK.equals(other.reviewPK)));
    }

    @Override
    public String toString() {
        return "ooka.conference.entity.Review[ reviewPK=" + reviewPK + " ]";
    }

}
