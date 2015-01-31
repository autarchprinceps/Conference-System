package ooka.conference.dto;

public class ReviewData {
    private String[] content;
    private boolean delivered;

    public boolean isDelivered() {
        return delivered;
    }

    public String[] getContent() {
        return delivered ? content : new String[]{"Review not yet delivered"};
    }
}
