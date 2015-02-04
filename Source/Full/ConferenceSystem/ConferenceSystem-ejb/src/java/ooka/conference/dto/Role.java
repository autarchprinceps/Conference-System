package ooka.conference.dto;

import java.io.Serializable;

public enum Role implements Serializable {

    AUTHOR, REVIEWER, VIEWER, ORGANIZER;

    public static Role[] getAllRoles() {
        return Role.values();
    }

    public static Role[] getParticipantRoles() {
        return new Role[]{AUTHOR, VIEWER};
    }
}
