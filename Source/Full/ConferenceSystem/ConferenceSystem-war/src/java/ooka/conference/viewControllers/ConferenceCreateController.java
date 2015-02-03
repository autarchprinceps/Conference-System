package ooka.conference.viewControllers;

import java.util.Arrays;
import java.util.Collection;
import ooka.conference.appControllers.PageController;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import ooka.conference.dto.ConferenceData;
import ooka.conference.ejb.ConferenceAdministrationLocal;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.User;

@ManagedBean
@ViewScoped
public class ConferenceCreateController {

    @ManagedProperty(value = "#{pageController}")
    private PageController pageController;

    @EJB
    private ConferenceAdministrationLocal confAdminEJB;

    @EJB
    private SearchLocal searchEJB;

    private int userId;

    private String newConfName;
    private Date newConfDate;
    private int newConfParticipantLimit = 25;
    private Collection<User> selectedUsers;
    private Collection<User> availableUsers;

    @PostConstruct
    public void init() {

        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        userId = Integer.parseInt(params.get("userId"));

        availableUsers = searchEJB.searchForUsers();
    }

    public String getNewConfName() {
        return newConfName;
    }

    public void setNewConfName(String newConfName) {
        this.newConfName = newConfName;
    }

    public Date getNewConfDate() {
        return newConfDate;
    }

    public void setNewConfDate(Date newConfDate) {
        this.newConfDate = newConfDate;
    }

    public int getNewConfParticipantLimit() {
        return newConfParticipantLimit;
    }

    public void setNewConfParticipantLimit(int newConfParticipantLimit) {
        this.newConfParticipantLimit = newConfParticipantLimit;
    }

    public PageController getPageController() {
        return pageController;
    }

    public void setPageController(PageController pageController) {
        this.pageController = pageController;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Collection<User> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(Collection<User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public Collection<User> getAvailableUsers() {
        return availableUsers;
    }

    public String doCreate() {
        ConferenceData data = new ConferenceData();
        data.setName(newConfName);
        data.setDate(newConfDate);
        data.setParticipantLimit(newConfParticipantLimit);
        data.setComittee(selectedUsers);
        try {
            confAdminEJB.createConference(userId, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageController.getIndexPage();
    }

}
