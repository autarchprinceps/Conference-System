package ooka.conference.viewControllers;

import java.util.Collection;
import ooka.conference.appControllers.PageController;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import ooka.conference.appControllers.AuthenticationController;
import ooka.conference.dto.ConferenceData;
import ooka.conference.ejb.ConferenceAdministrationLocal;
import ooka.conference.ejb.SearchLocal;
import ooka.conference.entity.User;

@ManagedBean
@ViewScoped
public class ConferenceCreateController {

    @EJB
    private ConferenceAdministrationLocal confAdminEJB;

    @EJB
    private SearchLocal searchEJB;

    @ManagedProperty(value = "#{authenticationController}")
    private AuthenticationController authEJB;

    private String newConfName;
    private Date newConfDate;
    private int newConfParticipantLimit = 25;
    private Collection<User> selectedUsers;
    private Collection<User> availableUsers;

    @PostConstruct
    public void init() {
        availableUsers = searchEJB.searchForUsers();
        availableUsers.remove(authEJB.getCurrentUser());
    }

    public AuthenticationController getAuthEJB() {
        return authEJB;
    }

    public void setAuthEJB(AuthenticationController authEJB) {
        this.authEJB = authEJB;
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

    public Collection<User> getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(Collection<User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public Collection<User> getAvailableUsers() {
        return availableUsers;
    }

    public void doCreate() {
        ConferenceData data = new ConferenceData();
        data.setName(newConfName);
        data.setDate(newConfDate);
        data.setParticipantLimit(newConfParticipantLimit);
        data.setComittee(selectedUsers);

        try {
            confAdminEJB.createConference(authEJB.getCurrentUser().getId(), data);
            PageController.redirectTo(PageController.userViewPage);
        } catch (Exception e) {
            PageController.message("Could not create the Conference");
        }
    }

}
