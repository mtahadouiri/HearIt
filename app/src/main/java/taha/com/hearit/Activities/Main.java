package taha.com.hearit.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import taha.com.hearit.Frags.SignIn;
import taha.com.hearit.Frags.SignUp;
import taha.com.hearit.R;

public class Main extends AppCompatActivity implements SignIn.OnFragmentInteractionListener, SignUp.OnFragmentInteractionListener {
    private FrameLayout frameLayout;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("onAuthStateChanged", "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent i = new Intent(Main.this, Home.class);
                    startActivity(i);
                } else {
                    // User is signed out
                    Log.d("onAuthStateChanged", "onAuthStateChanged:signed_out");
                    // Create a new Fragment to be placed in the activity layout
                    SignIn signIn = new SignIn();

                    // In case this activity was started with special instructions from an
                    // Intent, pass the Intent's extras to the fragment as arguments
                    signIn.setArguments(getIntent().getExtras());

                    // Add the fragment to the 'fragment_container' FrameLayout
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.frameLayout, signIn).commit();
                }
            }
        };

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("FragmentInteraction", uri.getFragment());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
