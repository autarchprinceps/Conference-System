package conference.businessObjects;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Conference {
    private final static Map<Integer, Conference> table = new HashMap<>();
    private static int id_gen = 0;

    public static Map<Integer, Conference> getTable() {
        return table;
    }
    
    private final int id;
    private final String name;
    private final Date start;
    private final Date end;
    private final Organizer organizer;
    private final Map<Integer, Integer> userVote;
    
    public Conference(final String name, final Date start, final Date end, final Organizer organizer) {
        this.id = id_gen++;
        this.name = name;
        this.start = start;
        this.end = end;
        this.organizer = organizer;
        this.userVote = new HashMap<>();
        
        Conference.table.put(id, this);
    }
    
    public static Conference getConferenceById(final int id) {
        return table.get(id);
    }
    
    public void register(final int userId) throws Exception {
        if(userVote.containsKey(userId)) {
            throw new Exception("register: This user is already registered to the given conference");
        } else {
            userVote.put(userId, 0);
        }
    }
    
    public void rate(final int userId, final int rating) {
        userVote.replace(userId, rating);
    }
    
    public double getRating() {
        return userVote.size() > 0 ?
				(1.0 * userVote.values().stream()
                .reduce(Integer::sum)
                .get()) / userVote.size()
				: 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Organizer getOrganizer() {
        return organizer;
    }
}
