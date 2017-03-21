package taha.com.hearit.Entity;

import android.os.UserManager;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by PC on 21/03/2017.
 */

public class Profile  {
    private FirebaseUser user;
    private String name,description;

    public Profile(FirebaseUser user, String name, String description) {
        this.user = user;
        this.name = name;
        this.description = description;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
