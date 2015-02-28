package ooka.conference.viewControllers;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import ooka.conference.appControllers.AuthenticationController;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.Publication;
import org.primefaces.model.StreamedContent;

@ManagedBean
@ViewScoped
public class PublicationViewController {

    private Publication currentPublication;

    @ManagedProperty(value = "#{authenticationController}")
    private AuthenticationController authEJB;

    @EJB
    private SearchLocal searchEJB;

    @PostConstruct
    public void init() {
        Map<String, String> context = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        currentPublication = (Publication) searchEJB.searchForPublication(Integer.parseInt(context.get("confId")), Integer.parseInt(context.get("userId")));
    }

    public Publication getCurrentPublication() {
        return currentPublication;
    }

    public boolean currentUserIsAuthor() {
        return currentPublication.getUser().getId().equals(authEJB.getCurrentUser().getId());
    }

    public boolean isCurrentUserReviewer(int reviewerId) {
        // TODO
        return false;
    }

    public AuthenticationController getAuthEJB() {
        return authEJB;
    }

    public void setAuthEJB(AuthenticationController authEJB) {
        this.authEJB = authEJB;
    }

    private int selectedPublicationRevision;

    public int getSelectedPublicationRevision() {
        return selectedPublicationRevision;
    }

    public void setSelectedPublicationRevision(int selectedPublicationRevision) {
        this.selectedPublicationRevision = selectedPublicationRevision;
    }
    
    public int getMaxRevisionCount() {
        // TODO
        return 3;
    }
    
    public void changeRevision() {
        // TODO param?
    }
    
    private StreamedContent revision; // TODO init

    public StreamedContent getRevision() {
        return revision;
    }

    public void setRevision(StreamedContent revision) {
        this.revision = revision;
    }
    
}
