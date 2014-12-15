package conference.businessObjects;

import java.util.ArrayList;
import java.util.List;

public class Organizer extends User {
    private final List<Conference> organizedConferences;
    
    public Organizer(final String name) {
        super(name);
        this.organizedConferences = new ArrayList<>();
    }
    
    public void organizeConference(final Conference conference) {
        organizedConferences.add(conference);
    }
    
    public double getRating() {
        return organizedConferences.size() > 0 ?
				organizedConferences.stream()
                .map((conference) -> conference.getRating())
                .reduce(Double::sum)
                .get() / organizedConferences.size()
				: 0;
    }

    public List<Conference> getOrganizedConferences() {
        return organizedConferences;
    }
}
