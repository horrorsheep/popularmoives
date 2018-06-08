
package com.example.ziyangruan.popularmovies;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private static final String TAG = MovieAdapter.class.getSimpleName();
    final private ItemClickListener mItemClickListener;
    private int mNumberItems;
    private static int viewHolderCount;
    private Context context;

    public MovieAdapter(ItemClickListener mItemClickListener, int mNumberItems, Context context) {
        this.mItemClickListener = mItemClickListener;
        this.mNumberItems = mNumberItems;
        viewHolderCount = 0;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutIdForItem = R.layout.recyclerview_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForItem, parent, shouldAttachToParentImmediately);
        MovieHolder viewHolder = new MovieHolder(view);
        viewHolderCount++;
        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: "
                + viewHolderCount);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Log.d(TAG, "#" + position);
        Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(holder.mImageView);

        //holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;

        MovieHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.movie_poster);
            itemView.setOnClickListener(this);
        }

        void bind(int pos) {
            Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(mImageView);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(int clickedItemIndex);
    }
}


