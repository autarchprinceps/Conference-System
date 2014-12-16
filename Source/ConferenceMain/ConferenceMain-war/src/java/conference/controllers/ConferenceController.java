package conference.controllers;

import conference.businessObjects.Conference;
import conference.businessObjects.Organizer;
import conference.businessObjects.User;
import conference.ejbs.ConferenceManagementLocal;
import java.util.ArrayList;
import java.util.Collection;
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
    
    public Collection<Conference> getConferences() {
        return Conference.getTable().values();
    }
    
    public Collection<User> getUsers() {
        return User.getTable().values();
    }
    
    public ArrayList<User> getOrganizers() {
		ArrayList<User> tmp = new ArrayList<>();
        User.getTable().values().stream().filter((user) -> user instanceof Organizer).forEach((user) -> tmp.add(user));
		return tmp;
    }
    
    private String result = "";
    
    private String confName;
    private String confOrganizer;
    private String confMaxPart;
    private String confStartDateY;
	private String confStartDateM;
	private String confStartDateD;
    private String confEndDateY;
	private String confEndDateM;
	private String confEndDateD;
    
    private boolean organizer;
    private String userName;
    
    private String confId;
    private String rating;
    private String userId;
    
    public String addUser() {
        result = "User created with ID: " + ejb.createUser(userName, !organizer);
        return Pages.main;
    }
    
    public String addConference() {
        try {
            result = "Conference created with ID: " + ejb.createConference(Integer.parseInt(confOrganizer), confName, Integer.parseInt(confMaxPart), new Date(Integer.parseInt(confStartDateY), Integer.parseInt(confStartDateM), Integer.parseInt(confStartDateD)), new Date(Integer.parseInt(confEndDateY), Integer.parseInt(confEndDateM), Integer.parseInt(confEndDateD)));
        } catch (Exception ex) {
            result = "ERROR: " + ex.toString();
            Logger.getLogger(ConferenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Pages.main;
    }
    
    public String registerToConference() {
        try {
            ejb.registerToConference(Integer.parseInt(confId), Integer.parseInt(userId));
            result = "Registered to conference";
        } catch (Exception ex) {
            result = "ERROR: " + ex.toString();
            Logger.getLogger(ConferenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Pages.main;
    }
    
    public String rateConference() {
        try {
            ejb.rateConference(Integer.parseInt(confId), Integer.parseInt(userId), Integer.parseInt(rating));
            result = "Conference rated: " + Conference.getConferenceById(Integer.parseInt(confId)).getName() + " " + User.getUserById(Integer.parseInt(userId)).getName() + " " + Integer.parseInt(rating);
        } catch (Exception ex) {
            result = "ERROR: " + ex.toString();
            Logger.getLogger(ConferenceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Pages.main;
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

	/**
	 * @return the confStartDateY
	 */
	public String getConfStartDateY() {
		return confStartDateY;
	}

	/**
	 * @param confStartDateY the confStartDateY to set
	 */
	public void setConfStartDateY(String confStartDateY) {
		this.confStartDateY = confStartDateY;
	}

	/**
	 * @return the confStartDateM
	 */
	public String getConfStartDateM() {
		return confStartDateM;
	}

	/**
	 * @param confStartDateM the confStartDateM to set
	 */
	public void setConfStartDateM(String confStartDateM) {
		this.confStartDateM = confStartDateM;
	}

	/**
	 * @return the confStartDateD
	 */
	public String getConfStartDateD() {
		return confStartDateD;
	}

	/**
	 * @param confStartDateD the confStartDateD to set
	 */
	public void setConfStartDateD(String confStartDateD) {
		this.confStartDateD = confStartDateD;
	}

	/**
	 * @return the confEndDateY
	 */
	public String getConfEndDateY() {
		return confEndDateY;
	}

	/**
	 * @param confEndDateY the confEndDateY to set
	 */
	public void setConfEndDateY(String confEndDateY) {
		this.confEndDateY = confEndDateY;
	}

	/**
	 * @return the confEndDateM
	 */
	public String getConfEndDateM() {
		return confEndDateM;
	}

	/**
	 * @param confEndDateM the confEndDateM to set
	 */
	public void setConfEndDateM(String confEndDateM) {
		this.confEndDateM = confEndDateM;
	}

	/**
	 * @return the confEndDateD
	 */
	public String getConfEndDateD() {
		return confEndDateD;
	}

	/**
	 * @param confEndDateD the confEndDateD to set
	 */
	public void setConfEndDateD(String confEndDateD) {
		this.confEndDateD = confEndDateD;
	}
}
