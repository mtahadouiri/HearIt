package taha.com.hearit.Entity;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PC on 21/03/2017.
 */

public class Post {
    private FirebaseUser poster;
    private String text;
    private Date date;
    private List<String> hashtags;
    private List<Profile> voters;
    private List<Profile> commentors;


    public Post() {
    }

    public Post(FirebaseUser poster, String text, Date date, List<String> hashtags) {
        this.poster = poster;
        this.text = text;
        this.date = date;
        this.hashtags = hashtags;
        this.voters = new ArrayList<>();
        this.commentors = new ArrayList<>();
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtag(List<String> hashtag) {
        this.hashtags = hashtag;
    }
}
