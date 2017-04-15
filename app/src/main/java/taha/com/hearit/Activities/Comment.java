package taha.com.hearit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import taha.com.hearit.R;

import static taha.com.hearit.Activities.Main.myPROFILE;
import static taha.com.hearit.Activities.Main.user;

public class Comment extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private EditText corpse;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        corpse = (EditText)findViewById(R.id.message);
        add = (Button)findViewById(R.id.cnfrmButton);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Log.d("Post",PostDetails.post.toString());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentSth(corpse.getText().toString());
            }
        });
    }
    private void commentSth(String text) {
        String key = mDatabase.child("comments").push().getKey();
        taha.com.hearit.Entity.Comment comment = new taha.com.hearit.Entity.Comment(myPROFILE.getName(), text, myPROFILE.getUps(), PostDetails.post.getKey());

        Map<String, Object> commentValues = comment.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/comments/" + PostDetails.post.getKey() +"/"+key, commentValues);
        childUpdates.put("/user-comments/" + user.getUid() + "/" +PostDetails.post.getKey() +"/"+key, commentValues);
        mDatabase.updateChildren(childUpdates);
    }
}
