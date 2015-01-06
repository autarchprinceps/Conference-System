package conference.dataTransferal;

import java.util.Date;

public class Conference {
    private final int id;
    private final String name;
    
    private final int organizerId;
    private final String organizerName;
    
    private final Date start;
    private final Date end;
    
    private final int maxParticipants;
    
    public Conference(int id, String name, int organizerId, String organizerName, Date start, Date end, int maxParticipants) {
        this.id = id;
        this.name = name;
        this.organizerId = organizerId;
        this.organizerName = organizerName;
        this.start = start;
        this.end = end;
        this.maxParticipants = maxParticipants;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the organizerId
     */
    public int getOrganizerId() {
        return organizerId;
    }

    /**
     * @return the organizerName
     */
    public String getOrganizerName() {
        return organizerName;
    }

    /**
     * @return the start
     */
    public Date getStart() {
        return start;
    }

    /**
     * @return the end
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @return the maxParticipants
     */
    public int getMaxParticipants() {
        return maxParticipants;
    }
}
