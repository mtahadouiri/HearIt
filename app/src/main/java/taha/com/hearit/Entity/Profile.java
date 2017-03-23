package taha.com.hearit.Entity;

import android.os.UserManager;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by PC on 21/03/2017.
 */

public class Profile  {
    private FirebaseUser user;
    private String name;

    public Profile(FirebaseUser user, String name) {
        this.user = user;
        this.name = name;
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


}
