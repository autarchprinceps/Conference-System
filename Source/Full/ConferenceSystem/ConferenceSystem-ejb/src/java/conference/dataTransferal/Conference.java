package conference.dataTransferal;

public class Conference {
    private final int id;
    private final String name;
    
    private final int organizerId;
    private final String organizerName;
    
    public Conference(int id, String name, int organizerId, String organizerName) {
        this.id = id;
        this.name = name;
        this.organizerId = organizerId;
        this.organizerName = organizerName;
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
}
