package ooka.conference.viewControllers;

import ooka.conference.appControllers.PageController;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
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
    private AuthenticationController authController;

    private Conference currentConference;

    private String newPubTitle;

    @PostConstruct
    public void init() {
        int confId = PageController.getParamToInt("confId");
        currentConference = searchEJB.searchConferenceById(confId);
    }

    public AuthenticationController getAuthController() {
        return authController;
    }

    public void setAuthController(AuthenticationController authController) {
        this.authController = authController;
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
            pubAdminEJB.createPublication(authController.getCurrentUser().getId(), currentConference.getId(), newPubTitle);
            PageController.redirectTo(PageController.pubViewPage, "confId", currentConference.getId().toString(), "userId", authController.getCurrentUser().getId().toString());

        } catch (Exception e) {
            PageController.message("Error", "Could not create the Publication");
        }
    }

}
