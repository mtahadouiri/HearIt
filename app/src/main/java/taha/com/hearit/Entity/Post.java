package taha.com.hearit.Entity;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PC on 21/03/2017.
 */

public class Post {
    private String poster;
    private String text,url;
    private Date date;
    public int ups = 0;
    private List<String> hashtags;
    private List<Profile> voters;
    private List<Profile> commentors;

    public Post(String poster, String text, String URL, Date date) {
        this.poster = poster;
        this.text = text;
        this.url = URL;
        this.date = date;
    }

    public Post() {
    }


    public Post(String poster, String text, Date date, List<String> hashtags) {
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

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public List<Profile> getVoters() {
        return voters;
    }

    public void setVoters(List<Profile> voters) {
        this.voters = voters;
    }

    public List<Profile> getCommentors() {
        return commentors;
    }

    public void setCommentors(List<Profile> commentors) {
        this.commentors = commentors;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Post{" +
                "poster=" + poster +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", hashtags=" + hashtags +
                ", voters=" + voters +
                ", commentors=" + commentors +
                '}';
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", poster);
        result.put("body", text);
        result.put("date", date);
        result.put("url", url);
        result.put("ups", 0);
        return result;
    }
}
