package ooka.conference.dto;

public class Publication {
    private Conference conference;
    private User author;
    private Review[] reviews;
    private String title;
    private String[] content;
    private boolean delivered;
    
    public String[] getContent() {
        return delivered ? content : new String[]{"Publication not yet delivered"};
    }

    public User getAuthor() {
        return author;
    }

    public Review[] getReviews() {
        return reviews;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDelivered() {
        return delivered;
    }
    
    public Conference getConference() {
        return conference;
    }
}
