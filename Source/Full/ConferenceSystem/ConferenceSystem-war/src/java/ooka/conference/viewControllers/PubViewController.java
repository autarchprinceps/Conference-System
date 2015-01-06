package ooka.conference.viewControllers;

import ooka.conference.dto.Review;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class PubViewController {

    @ManagedProperty(value = "#{param.authorId}")
    private int authorId;

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    @ManagedProperty(value = "#{param.conferenceId}")
    private int conferenceId;

    public String getAuthorName() {
        // TODO
        return "Max Muster";
    }
    
    public List<Review> getReviews() {
        // TODO
        return new ArrayList<>();
    }
    
    public String[] getPublicationContent() {
        // TODO
        return new String[] { "TODO" };
    }
    
    public boolean isPublicationAvailable() {
        // TODO
        return false;
    }
    
    public boolean isCurrentUserAuthor() {
        // TODO
        return true;
    }
    
    public boolean isCurrentUserReviewer(int reviewerId) {
        // TODO
        return false;
    }
}
