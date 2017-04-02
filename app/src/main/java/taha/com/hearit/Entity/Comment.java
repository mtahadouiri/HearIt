package taha.com.hearit.Entity;

import java.util.HashMap;
import java.util.Map;

import taha.com.hearit.Activities.Main;

/**
 * Created by Taha on 02/04/2017.
 */

public class Comment {
    private String id;
    private String username,txtComment,userUps,postID;

    public Comment(String username, String txtComment, String userUps, String postID) {

        this.username = username;
        this.txtComment = txtComment;
        this.userUps = userUps;
        this.postID = postID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTxtComment() {
        return txtComment;
    }

    public void setTxtComment(String txtComment) {
        this.txtComment = txtComment;
    }

    public String getUserUps() {
        return userUps;
    }

    public void setUserUps(String userUps) {
        this.userUps = userUps;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", txtComment='" + txtComment + '\'' +
                ", userUps='" + userUps + '\'' +
                ", postID='" + postID + '\'' +
                '}';
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("username", username);
        result.put("txtComment", txtComment);
        result.put("userUps", userUps);
        result.put("postID",postID);
        return result;
    }
}
