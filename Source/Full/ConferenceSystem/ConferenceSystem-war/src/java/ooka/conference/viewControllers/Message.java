package ooka.conference.viewControllers;

public class Message {
    private final String title;
    private final String content;
    private boolean read;

    public boolean isRead() {
        return read;
    }
    
    public Message(String title, String content) {
        this.title = title;
        this.content = content;
        this.read = false;
    }

    public String getTitle() {
        read = true;
        return title;
    }

    public String getContent() {
        read = true;
        return content;
    }

}
