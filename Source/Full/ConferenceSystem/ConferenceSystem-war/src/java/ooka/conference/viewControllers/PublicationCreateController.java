package ooka.conference.viewControllers;

import java.util.Collection;
import ooka.conference.appControllers.PageController;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import ooka.conference.appControllers.AuthenticationController;
import ooka.conference.dto.ConferenceData;
import ooka.conference.dto.PublicationData;
import ooka.conference.ejb.ConferenceAdministrationLocal;
import ooka.conference.ejb.PublicationAdministrationLocal;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.Conference;
import ooka.conference.entity.User;
import ooka.conference.util.Redirector;

@ManagedBean
@ViewScoped
public class PublicationCreateController {

    @ManagedProperty(value = "#{pageController}")
    private PageController pageController;

    @EJB
    private PublicationAdministrationLocal pubAdminEJB;

    @EJB
    private SearchLocal searchEJB;

    @ManagedProperty(value = "#{authenticationController}")
    private AuthenticationController authEJB;

    private Conference currentConference;
    private String newPubTitle;
    private String newPubContent;

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

    public PageController getPageController() {
        return pageController;
    }

    public void setPageController(PageController pageController) {
        this.pageController = pageController;
    }

    public String getNewPubTitle() {
        return newPubTitle;
    }

    public void setNewPubTitle(String newPubTitle) {
        this.newPubTitle = newPubTitle;
    }

    public String getNewPubContent() {
        return newPubContent;
    }

    public void setNewPubContent(String newPubContent) {
        this.newPubContent = newPubContent;
    }

    public Conference getCurrentConference() {
        return currentConference;
    }

    public void doCreate() {

        try {
            pubAdminEJB.createPublication(newPubTitle, authEJB.getCurrentUser().getId(), currentConference.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        PageController.redirectTo(pageController.getConfViewPage(), "confId", currentConference.getId().toString());
    }

}
