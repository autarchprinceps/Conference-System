package ooka.conference.dto;

public class UserConferenceConnection {
    private final int userId;
    private final String userName;
    
    private final int conferenceId;
    private final String conferenceName;
    
    private final Role role;
    
    public UserConferenceConnection(int userId, String userName, int conferenceId, String conferenceName, Role role) {
        this.userId = userId;
        this.userName = userName;
        this.conferenceId = conferenceId;
        this.conferenceName = conferenceName;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public Role getRole() {
        return role;
    }
}
