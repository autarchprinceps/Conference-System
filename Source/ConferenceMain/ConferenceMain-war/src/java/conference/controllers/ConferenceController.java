package conference.controllers;

import conference.businessObjects.Conference;
import conference.businessObjects.Organizer;
import conference.businessObjects.User;
import conference.ejbs.ConferenceManagementLocal;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ConferenceController {
    @EJB
    private ConferenceManagementLocal ejb;
    
    public ConferenceController() {
    }
    
    public Conference[] getConferences() {
        return (Conference[])Conference.getTable().values().toArray();
    }
    
    public User[] getUsers() {
        return (User[])User.getTable().values().toArray();
    }
    
    public Organizer[] getOrganizers() {
        return (Organizer[])
                User.getTable().values().stream()
                .filter((user) -> user instanceof Organizer)
                .toArray();
    }
    
    private String result = "";
    
    private String confName;
    private String confOrganizer;
    private String confMaxPart;
    private String confStartDate; // TODO better
    private String confEndDate; // TODO better
    
    private boolean organizer;
    private String userName;
    
    private String confId;
    private String rating;
    private String userId;
    
    public String addUser() {
        result = "User created with ID: " + ejb.createUser(userName, organizer);
        return "main.xhtml";
    }
    
    public String addConference() {
        try {
            result = "Conference created with ID: " + ejb.createConference(Integer.parseInt(confOrganizer), confName, Integer.parseInt(confMaxPart), new Date(confStartDate), new Date(confEndDate));
        } catch (Exception ex) {
            result = "ERROR: " + ex.toString();
            Logger.getLogger(ConferenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "main.xhtml";
    }
    
    public String registerToConference() {
        try {
            ejb.registerToConference(Integer.parseInt(confId), Integer.parseInt(userId));
            result = "Registered to conference";
        } catch (Exception ex) {
            result = "ERROR: " + ex.toString();
            Logger.getLogger(ConferenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "main.xhtml";
    }
    
    public String rateConference() {
        try {
            ejb.rateConference(Integer.parseInt(confId), Integer.parseInt(userId), Integer.parseInt(rating));
            result = "Conference rated";
        } catch (Exception ex) {
            result = "ERROR: " + ex.toString();
            Logger.getLogger(ConferenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "main.xhtml";
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public String getConfOrganizer() {
        return confOrganizer;
    }

    public void setConfOrganizer(String confOrganizer) {
        this.confOrganizer = confOrganizer;
    }

    public String getConfMaxPart() {
        return confMaxPart;
    }

    public void setConfMaxPart(String confMaxPart) {
        this.confMaxPart = confMaxPart;
    }

    public String getConfStartDate() {
        return confStartDate;
    }

    public void setConfStartDate(String confStartDate) {
        this.confStartDate = confStartDate;
    }

    public String getConfEndDate() {
        return confEndDate;
    }

    public void setConfEndDate(String confEndDate) {
        this.confEndDate = confEndDate;
    }

    public boolean isOrganizer() {
        return organizer;
    }

    public void setOrganizer(boolean organizer) {
        this.organizer = organizer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getConfId() {
        return confId;
    }

    public void setConfId(String confId) {
        this.confId = confId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
