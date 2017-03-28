package taha.com.hearit.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

import taha.com.hearit.Activities.Main;
import taha.com.hearit.Entity.Post;
import taha.com.hearit.R;

/**
 * Created by Taha on 05/03/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {
    private List<Post> postList;
    Context ctx;

    public PostsAdapter(List<Post> postList,Context context) {
        this.postList = postList;
        this.ctx = context;
    }

    @Override
    public PostsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.one_post,parent,false));
    }

    @Override
    public void onBindViewHolder(PostsViewHolder holder, int position) {
        final Post post=postList.get(position);
        holder.userName.setText(post.getPoster());
        //holder.numVotes.setText(post.ups);
        holder.userUps.setText("++"+post.getUps());
        holder.txtCorpse.setText(post.getText());
        Log.d("Post",post.toString());
        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) ctx, Main.YOUTUBE_API_KEY, post.getUrl());
                ctx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView userName,numVotes,userUps,txtCorpse;
        protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView youTubeThumbnailView;
        protected ImageView playButton;
        TextView txtMessage,txtUrl;
        public PostsViewHolder(View itemView) {
            super(itemView);
            userName=(TextView)itemView.findViewById(R.id.txtUserName);
            numVotes=(TextView)itemView.findViewById(R.id.numVotes);
            userUps=(TextView)itemView.findViewById(R.id.userUps);
            txtCorpse = (TextView)itemView.findViewById(R.id.txtCorpse);
            playButton =(ImageView)itemView.findViewById(R.id.btnYoutube_player);

            relativeLayoutOverYouTubeThumbnailView = (RelativeLayout)itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail);
            youTubeThumbnailView =(YouTubeThumbnailView)itemView.findViewById(R.id.youtube_thumbnail);
        }
    }

}
