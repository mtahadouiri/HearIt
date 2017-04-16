package taha.com.hearit.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import taha.com.hearit.Adapters.CommentsAdapter;
import taha.com.hearit.Adapters.PostsAdapter;
import taha.com.hearit.Entity.Comment;
import taha.com.hearit.Entity.Post;
import taha.com.hearit.Frags.AddPost;
import taha.com.hearit.Frags.addComment;
import taha.com.hearit.R;

public class PostDetails extends AppCompatActivity {
    Bundle bundle;
    public static Post post;
    private RecyclerView rv;
    private FloatingActionButton fab;
    private CommentsAdapter postsAdapter;
    private List<Comment> commentList;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    TextView userName, numVotes, userUps, txtCorpse;
    protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
    YouTubeThumbnailView youTubeThumbnailView;
    protected ImageView playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        bundle = getIntent().getExtras();
        post = (Post) bundle.get("post");
        Log.d("post", post.toString());
        commentList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("/comments/" + PostDetails.post.getKey());
        // Inflate the layout for this fragment
        rv = (RecyclerView) findViewById(R.id.rvComments);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setReverseLayout(true);
        llm.setStackFromEnd(true);
        rv.setLayoutManager(llm);
        userName = (TextView) findViewById(R.id.txtUserName);
        userName.setText(post.getPoster());
        //numVotes=(TextView)findViewById(R.id.numVotes);
        //numVotes.setText(post.getUps());
        //userUps=(TextView)findViewById(R.id.userUps);
        //userUps.setText(post.getUserUps());
        txtCorpse = (TextView) findViewById(R.id.txtCorpse);
        txtCorpse.setText(post.getText());
        playButton = (ImageView) findViewById(R.id.btnYoutube_player);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = YouTubeStandalonePlayer.createVideoIntent(PostDetails.this, Main.YOUTUBE_API_KEY, post.getUrl());
                startActivity(intent);
            }
        });
        relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) findViewById(R.id.relativeLayout_over_youtube_thumbnail);
        youTubeThumbnailView = (YouTubeThumbnailView) findViewById(R.id.youtube_thumbnail);
        final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            }
        };
        youTubeThumbnailView.initialize(Main.YOUTUBE_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(post.getUrl());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
                Log.d("thumbnail", "success");
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("Failed", "Failed");
            }
        });

        postsAdapter = new CommentsAdapter(commentList, this);
        rv.setAdapter(postsAdapter);
        updateList();
    }

    private void updateList() {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                commentList.add(dataSnapshot.getValue(Comment.class));
                postsAdapter.notifyDataSetChanged();
                Log.d("DatasnapAdded", dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Comment post = dataSnapshot.getValue(Comment.class);
                Log.d("DatasnapChanged", dataSnapshot.getValue().toString());
                int index = getIndex(post);
                commentList.set(index, post);
                postsAdapter.notifyItemChanged(index, post);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Comment post = dataSnapshot.getValue(Comment.class);
                Log.d("DatasnapRemoved", dataSnapshot.getValue().toString());
                int index = getIndex(post);
                Log.d("Index", index + "");
                commentList.remove(index);
                postsAdapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getIndex(Comment post) {
        int index = -1;
        for (int i = 0; i < commentList.size(); i++) {
            if (commentList.get(i).getId() == post.getId()) {
                index = i;
                return i;
            }
        }
        return index;
    }
}
