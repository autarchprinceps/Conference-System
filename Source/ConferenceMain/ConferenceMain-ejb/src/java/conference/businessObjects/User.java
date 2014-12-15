package conference.businessObjects;

import java.util.HashMap;
import java.util.Map;

public class User {
    private final static Map<Integer, User> table = new HashMap<>();
    private static int id_gen = 0;

    public static Map<Integer, User> getTable() {
        return table;
    }
    
    private final int id;
    private final String name;
    
    public User(final String name) {
        this.id = id_gen++;
        this.name = name;
        
        User.table.put(id, this);
    }
    
    public static User getUserById(final int id) {
        return table.get(id);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

	/**
	 * @return the organizer
	 */
	public boolean isOrganizer() {
		return this instanceof Organizer;
	}
}
