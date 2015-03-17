package ooka.conference.viewControllers;

import ooka.conference.appControllers.PageController;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import ooka.conference.ejb.PublicationAdministrationLocal;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.Conference;

@ManagedBean
@ViewScoped
public class PublicationCreateController extends AuthenticatedViewController {

    @EJB
    private PublicationAdministrationLocal pubAdminEJB;

    @EJB
    private SearchLocal searchEJB;

    private Conference currentConference;

    private String newPubTitle;

    @PostConstruct
    public void init() {
        int confId = PageController.getParamToInt("confId");
        currentConference = searchEJB.searchConferenceById(confId);
        if (!authController.isAuthorOfConference(currentConference)) {
            PageController.redirectTo(PageController.userLoginPage);
        }
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
