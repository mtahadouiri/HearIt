package taha.com.hearit.Entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Taha on 02/04/2017.
 */

public class Comment {
    private double id;
    private String username,txtComment;
    private int userUps;
    private String postKey;

    public Comment(String username, String txtComment, int userUps, String postID) {

        this.username = username;
        this.txtComment = txtComment;
        this.userUps = userUps;
        this.postKey = postID;
        id=Math.random() * System.currentTimeMillis();
    }

    public Comment() {
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
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

    public int getUserUps() {
        return userUps;
    }

    public void setUserUps(int userUps) {
        this.userUps = userUps;
    }

    public String getPostKey() {
        return postKey;
    }

    public void setPostKey(String postKey) {
        this.postKey = postKey;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", txtComment='" + txtComment + '\'' +
                ", userUps='" + userUps + '\'' +
                ", postKey='" + postKey + '\'' +
                '}';
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("username", username);
        result.put("txtComment", txtComment);
        result.put("userUps", userUps);
        result.put("postKey", postKey);
        return result;
    }
}
