package ooka.conference.viewControllers;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;
import ooka.conference.appControllers.PageController;
import ooka.conference.dto.Role;
import ooka.conference.ejb.PublicationAdministrationLocal;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.ConferenceUserRole;
import ooka.conference.entity.Publication;
import ooka.conference.entity.PublicationRevision;
import ooka.conference.entity.PublicationReview;
import ooka.conference.entity.User;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class PublicationViewController extends AuthenticatedViewController {

    @EJB
    private SearchLocal searchEJB;

    @EJB
    private PublicationAdministrationLocal pubEJB;

    private Publication currentPublication;

    private Part newRevision;

    private Part newReview;

    private int selectedPublicationRevision;

    private Collection<User> selectedUsers;

    private Collection<User> comitteeUsers;

    public Part getNewReview() {
        return newReview;
    }

    public void setNewReview(Part newReview) {
        this.newReview = newReview;
    }

    @PostConstruct
    public void init() {
        int confId = PageController.getParamToInt("confId");
        int userId = PageController.getParamToInt("userId");
        currentPublication = searchEJB.searchForPublication(confId, userId);
        comitteeUsers = new HashSet<>();
        for (ConferenceUserRole role : currentPublication.getConference().getConferenceUserRoleCollection()) {
            if (role.getUserRole().equals(Role.REVIEWER.toString())) {
                comitteeUsers.add(role.getUser());
            }
        }
    }

    public boolean reviewerAlreadySelected() {
        return currentPublication.getReviews().size() > 0;
    }

    public Publication getCurrentPublication() {
        return currentPublication;
    }

    public Collection<PublicationReview> getReviews() {
        return currentPublication.getReviews();
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

    public boolean currentUserCanReview() {

        for (PublicationReview review : currentPublication.getReviews()) {
            if (review.getReview_author().equals(authController.getCurrentUser())) {
                return !review.hasContent();
            }
        }
        return false;
    }

    public void addNewRevision() {
        try {
            byte[] content = new byte[(int) newRevision.getSize()];
            newRevision.getInputStream().read(content);
            Date currentDate = new Date();
            pubEJB.revisePublication(currentPublication.getUser().getId(), currentPublication.getConference().getId(), content, newRevision.getSubmittedFileName(), newRevision.getContentType(), currentDate);
            PageController.redirectTo(PageController.pubViewPage, "confId", String.valueOf(currentPublication.getPublicationPK().getConferenceId()), "userId", String.valueOf(currentPublication.getPublicationPK().getAuthorId()));
        } catch (Exception ex) {
            PageController.message("Error", "Revision could not be added");
        }
    }

    public void addNewReviewer() {
        try {

            for (User reviewer : selectedUsers) {
                pubEJB.addReviewerToPublication(reviewer.getId(), currentPublication.getUser().getId(), currentPublication.getConference().getId());
            }

            PageController.redirectTo(PageController.pubViewPage, "confId", String.valueOf(currentPublication.getPublicationPK().getConferenceId()), "userId", String.valueOf(currentPublication.getPublicationPK().getAuthorId()));
        } catch (Exception ex) {
            PageController.message("Error", "Reviewer could not be added");
        }
    }

    public void addNewReview() {
        try {
            byte[] content = new byte[(int) newReview.getSize()];
            newReview.getInputStream().read(content);
            Date currentDate = new Date();
            pubEJB.reviewPublication(authController.getCurrentUser().getId(), currentPublication.getUser().getId(), currentPublication.getConference().getId(), content, newReview.getSubmittedFileName(), newReview.getContentType(), currentDate);
            PageController.redirectTo(PageController.pubViewPage, "confId", String.valueOf(currentPublication.getPublicationPK().getConferenceId()), "userId", String.valueOf(currentPublication.getPublicationPK().getAuthorId()));
        } catch (Exception ex) {
            PageController.message("Error", "Review could not be added");
        }
    }

    public StreamedContent getRevisionDownload(PublicationRevision revision) {
        return new DefaultStreamedContent(new ByteArrayInputStream(revision.getContent()), revision.getContentType(), revision.getFileName());
    }

    public StreamedContent getReviewDownload(PublicationReview review) {
        return new DefaultStreamedContent(new ByteArrayInputStream(review.getContent()), review.getContentType(), review.getFileName());
    }

    public Collection<User> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(Collection<User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public Collection<User> getCommitteeUsers() {
        return comitteeUsers;
    }

    public void setCommitteeUsers(Collection<User> availableUsers) {
        this.comitteeUsers = availableUsers;
    }

    public void doDelete() {
        try {
            pubEJB.deletePublication(currentPublication.getPublicationPK().getAuthorId(), currentPublication.getPublicationPK().getConferenceId());
        } catch (Exception ex) {
            PageController.message("Error", "Could not delete the publication: " + ex.getMessage());
        } finally {
            PageController.redirectTo(PageController.userViewPage);
        }
    }
}
