package taha.com.hearit.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import taha.com.hearit.Entity.Profile;
import taha.com.hearit.Frags.AddPost;
import taha.com.hearit.Frags.Feed;
import taha.com.hearit.R;

public class Home extends AppCompatActivity implements Feed.OnFragmentInteractionListener, AddPost.OnFragmentInteractionListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Get the intent that started this activity
        Intent intent = getIntent();
        Uri data = intent.getData();

        try {
            if (intent.getType().equals("text/plain")) {
                Log.d("Intent",intent.getClipData().getItemAt(0).toString());
            }
        }
        catch (Exception e){
        e.printStackTrace();
        }
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        if (f == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new Feed()).commit();
        }
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}