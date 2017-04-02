package taha.com.hearit.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

import taha.com.hearit.Activities.Main;
import taha.com.hearit.Entity.Comment;
import taha.com.hearit.Entity.Post;
import taha.com.hearit.R;

/**
 * Created by Taha on 02/04/2017.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.PostsViewHolder> {
    private List<Comment> postList;
    Context ctx;

    public CommentsAdapter(List<Comment> postList,Context context) {
        this.postList = postList;
        this.ctx = context;
    }

    @Override
    public CommentsAdapter.PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentsAdapter.PostsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.one_comment,parent,false));
    }

    @Override
    public void onBindViewHolder(final CommentsAdapter.PostsViewHolder holder, int position) {
        final Comment post=postList.get(position);
        holder.userName.setText(post.getPoster());
        holder.userUps.setText("++"+post.getUps());
        holder.txtCorpse.setText(post.getText());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView userName,numVotes,userUps,txtCorpse;
        protected ImageView playButton;
        public PostsViewHolder(View itemView) {
            super(itemView);
            userName=(TextView)itemView.findViewById(R.id.txtUserName);
            userUps=(TextView)itemView.findViewById(R.id.userUps);
            txtCorpse = (TextView)itemView.findViewById(R.id.txtCorpse);


        }
    }

}
