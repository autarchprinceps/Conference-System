package ooka.conference.dto;

public class Review {
    private User reviewer;
    private String[] content;
    private boolean delivered;

    public boolean isDelivered() {
        return delivered;
    }

    public User getReviewer() {
        return reviewer;
    }

    public String[] getContent() {
        return delivered ? content : new String[]{"Review not yet delivered"};
    }
}
