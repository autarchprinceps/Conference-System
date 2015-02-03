package ooka.conference.dto;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import ooka.conference.entity.User;

public class ConferenceData implements Serializable {

    private String name;
    private Date date;
    private int participantLimit;
    private Collection<User> comittee;
    private int rating;

    public Collection<User> getComittee() {
        return comittee;
    }

    public void setComittee(Collection<User> comittee) {
        this.comittee = comittee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getParticipantLimit() {
        return participantLimit;
    }

    public void setParticipantLimit(int participantLimit) {
        this.participantLimit = participantLimit;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
