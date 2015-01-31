package ooka.conference.dto;

public class PublicationData {
    private ConferenceData conference;
    private ReviewData[] reviews;
    private String title;
    private String[] content;
    private boolean delivered;
    
    public String[] getContent() {
        return delivered ? content : new String[]{"Publication not yet delivered"};
    }

    public ReviewData[] getReviews() {
        return reviews;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDelivered() {
        return delivered;
    }
    
    public ConferenceData getConference() {
        return conference;
    }
}
