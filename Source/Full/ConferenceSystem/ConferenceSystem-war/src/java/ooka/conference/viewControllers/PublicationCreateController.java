package ooka.conference.viewControllers;

import ooka.conference.appControllers.PageController;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import ooka.conference.appControllers.AuthenticationController;
import ooka.conference.ejb.PublicationAdministrationLocal;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.Conference;

@ManagedBean
@ViewScoped
public class PublicationCreateController {

    @EJB
    private PublicationAdministrationLocal pubAdminEJB;

    @EJB
    private SearchLocal searchEJB;

    @ManagedProperty(value = "#{authenticationController}")
    private AuthenticationController authEJB;

    private Conference currentConference;

    private String newPubTitle;

    @PostConstruct
    public void init() {
        String confId = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("confId");
        currentConference = searchEJB.searchConferenceById(Integer.parseInt(confId));
    }

    public AuthenticationController getAuthEJB() {
        return authEJB;
    }

    public void setAuthEJB(AuthenticationController authEJB) {
        this.authEJB = authEJB;
    }

    public String getNewPubTitle() {
        return newPubTitle;
    }

    public void setNewPubTitle(String newPubTitle) {
        this.newPubTitle = newPubTitle;
    }

    public Conference getCurrentConference() {
        return currentConference;
    }

    public void doCreate() {
        try {
            pubAdminEJB.createPublication(authEJB.getCurrentUser().getId(), currentConference.getId(), newPubTitle);

        } catch (Exception e) {
            e.printStackTrace();
        }

        PageController.redirectTo(PageController.confViewPage, "confId", currentConference.getId().toString());
    }

}
