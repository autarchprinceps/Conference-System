/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ooka.conference.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bastian
 */
@Entity
@Table(name = "publication_review", schema = "conference_system")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PublicationReview.findAll", query = "SELECT r FROM PublicationReview r"),
    @NamedQuery(name = "PublicationReview.findByUserId", query = "SELECT r FROM PublicationReview r WHERE r.reviewPK.reviewerId = :reviewerId"),
    @NamedQuery(name = "PublicationReview.findByConferenceId", query = "SELECT r FROM PublicationReview r WHERE r.reviewPK.conferenceId = :conferenceId"),
    @NamedQuery(name = "PublicationReview.findByPublication", query = "SELECT r FROM PublicationReview r WHERE r.reviewPK.conferenceId = :conferenceId AND r.reviewPK.authorId = :authorId"),
    @NamedQuery(name = "PublicationReview.deleteByConferenceIdAndReviewerId", query = "DELETE FROM PublicationReview c WHERE c.reviewPK.conferenceId = :conferenceId AND c.reviewPK.reviewerId = :reviewerId")
})
public class PublicationReview implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PublicationReviewPK reviewPK;

    @Lob
    @Column(name = "content")
    private byte[] content;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "content_type")
    private String contentType;

    @JoinColumn(name = "conference_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Conference conference;

    @JoinColumn(name = "author_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User pub_author;

    @JoinColumn(name = "reviewer_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User review_author;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public PublicationReview() {
    }

    public PublicationReview(PublicationReviewPK reviewPK) {
        this.reviewPK = reviewPK;
    }

    public User getReview_author() {
        return review_author;
    }

    public void setReview_author(User review_author) {
        this.review_author = review_author;
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

    public PublicationReviewPK getReviewPK() {
        return reviewPK;
    }

    public void setReviewPK(PublicationReviewPK reviewPK) {
        this.reviewPK = reviewPK;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public User getPub_author() {
        return pub_author;
    }

    public void setPub_author(User pub_author) {
        this.pub_author = pub_author;
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
        if (!(object instanceof PublicationReview)) {
            return false;
        }
        PublicationReview other = (PublicationReview) object;
        return !((this.reviewPK == null && other.reviewPK != null) || (this.reviewPK != null && !this.reviewPK.equals(other.reviewPK)));
    }

    @Override
    public String toString() {
        return "ooka.conference.entity.Review[ reviewPK=" + reviewPK + " ]";
    }

    public boolean hasContent() {
        return date != null;
    }
}
