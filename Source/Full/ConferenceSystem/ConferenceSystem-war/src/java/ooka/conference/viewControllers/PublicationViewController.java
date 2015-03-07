package ooka.conference.viewControllers;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import ooka.conference.appControllers.AuthenticationController;
import ooka.conference.appControllers.PageController;
import ooka.conference.ejb.PublicationAdministrationLocal;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.Publication;
import ooka.conference.entity.PublicationRevision;
import ooka.conference.entity.Review;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class PublicationViewController {

    @ManagedProperty(value = "#{authenticationController}")
    private AuthenticationController authEJB;

    @EJB
    private SearchLocal searchEJB;

    @EJB
    private PublicationAdministrationLocal pubEJB;

    private Publication currentPublication;

    private Part newRevision;
    
    private Part newReview;

    
    private int selectedPublicationRevision;

    public Part getNewReview() {
        return newReview;
    }

    public void setNewReview(Part newReview) {
        this.newReview = newReview;
    }

    @PostConstruct
    public void init() {
        Map<String, String> context = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        currentPublication = (Publication) searchEJB.searchForPublication(Integer.parseInt(context.get("confId")), Integer.parseInt(context.get("userId")));
    }

    public Publication getCurrentPublication() {
        return currentPublication;
    }

    public boolean currentUserIsAuthor() {
        // TODO FIX new 1
        return currentPublication.getUser().equals(authEJB.getCurrentUser());
    }

    public boolean isCurrentUserReviewer() {
        return authEJB.isCurrentUserReviewer(currentPublication.getConference());
    }

    public AuthenticationController getAuthEJB() {
        return authEJB;
    }

    public void setAuthEJB(AuthenticationController authEJB) {
        this.authEJB = authEJB;
    }
    
    public Collection<Review> getReviews() {
        return searchEJB.searchReviewsForPublication(currentPublication.getPublicationPK().getAuthorId(), currentPublication.getPublicationPK().getConferenceId());
    }

    public int getSelectedPublicationRevision() {
        return selectedPublicationRevision;
    }

    public void setSelectedPublicationRevision(int selectedPublicationRevision) {
        this.selectedPublicationRevision = selectedPublicationRevision;
    }

    public Part getNewRevision() {
        return newRevision;
    }

    public void setNewRevision(Part newRevision) {
        this.newRevision = newRevision;
    }

    public void addNewRevision() {
        try {
            byte[] content = new byte[(int) newRevision.getSize()];
            newRevision.getInputStream().read(content);
            pubEJB.revisePublication(currentPublication.getUser().getId(), currentPublication.getConference().getId(), content, newRevision.getSubmittedFileName(), newRevision.getContentType());
        } catch (Exception ex) {
        } finally {
            PageController.redirectTo(PageController.confViewPage, "confId", currentPublication.getConference().getId().toString());
        }
    }
    
    public void addNewReview() {
        try {
            byte[] content = new byte[(int) newRevision.getSize()];
            newRevision.getInputStream().read(content);
            pubEJB.revisePublication(currentPublication.getUser().getId(), currentPublication.getConference().getId(), content, newRevision.getSubmittedFileName(), newRevision.getContentType());
        } catch (Exception ex) {
        } finally {
            PageController.redirectTo(PageController.confViewPage, "confId", currentPublication.getConference().getId().toString());
        }
    }
    
    public StreamedContent getRevisionDownload(PublicationRevision revision) {
        return new DefaultStreamedContent(new ByteArrayInputStream(revision.getContent()), revision.getContentType(), revision.getFileName());
    }
    
    public StreamedContent getReviewDownload(Review review) {
        return new DefaultStreamedContent(new ByteArrayInputStream(review.getContent()), review.getContentType(), review.getFileName());
    }
}
