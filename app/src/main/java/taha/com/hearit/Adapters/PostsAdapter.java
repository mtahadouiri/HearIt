package taha.com.hearit.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Taha on 05/03/2017.
 */

public class PostsAdapter {


    class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView txtMessage,txtUrl;
        public PostsViewHolder(View itemView) {
            super(itemView);
        }
    }

}
