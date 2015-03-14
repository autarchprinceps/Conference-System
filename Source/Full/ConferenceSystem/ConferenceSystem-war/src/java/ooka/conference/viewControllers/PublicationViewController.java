package ooka.conference.viewControllers;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import ooka.conference.appControllers.AuthenticationController;
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
        currentPublication = (Publication) searchEJB.searchForPublication(confId, userId);
        //comitteeUsers = currentPublication.getConference().getConferenceUserRoleCollection().stream().filter(r -> r.getUserRole().equals(Role.REVIEWER.toString())).map(r -> r.getUser()).collect(Collectors.toList());
        comitteeUsers = searchEJB.searchForUsers();
    }

    public Publication getCurrentPublication() {
        return currentPublication;
    }

    public boolean currentUserIsAuthor() {
        // TODO FIX new 1
        return authEJB.getCurrentUser().equals(currentPublication.getUser());
    }

    public boolean isCurrentUserReviewer() {
        return authEJB.isReviewerOfConference(currentPublication.getConference());
    }

    public AuthenticationController getAuthEJB() {
        return authEJB;
    }

    public void setAuthEJB(AuthenticationController authEJB) {
        this.authEJB = authEJB;
    }

    public Collection<PublicationReview> getReviews() {
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
            pubEJB.reviewPublication(authEJB.getCurrentUser().getId(), currentPublication.getUser().getId(), currentPublication.getConference().getId(), content, newReview.getSubmittedFileName(), newReview.getContentType(), currentDate);
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

}
