package taha.com.hearit.Entity;

import android.os.UserManager;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by PC on 21/03/2017.
 */

public class Profile {
    private String name;
    private String email;
    private String id;
    private int ups;

    public Profile( String email,String name, String id,int ups) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.ups=ups;
    }

    public Profile() {
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
