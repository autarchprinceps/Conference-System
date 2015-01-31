package ooka.conference.dto;

public enum Role {

    AUTHOR, REVIEWER, VIEWER, ORGANIZER;

    public static Role[] getAllRoles() {
        return Role.values();
    }

    public static Role[] getParticipantRoles() {
        return new Role[]{AUTHOR, REVIEWER, VIEWER};
    }
}
