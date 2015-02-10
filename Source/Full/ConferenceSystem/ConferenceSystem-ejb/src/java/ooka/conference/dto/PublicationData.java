package ooka.conference.dto;

public class PublicationData {

    private String title;
    private String content;

    public String getContent() {
        return content == null ? content : "### Publication not yet delivered ###";
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
