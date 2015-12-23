package com.example.jose_gonzalez.popularmovies.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jose_gonzalez.popularmovies.R;

import java.util.ArrayList;

/**.___
 *
 * Created by jose-gonzalez on 11/11/15.
 *
 * Custom recycle view adapter
 *
 __.*/
public class MovieImageAdapter extends RecyclerView.Adapter{

    private ArrayList<String> urls;
    private RecycleCallback callback;
    private Context context;

    //.___ To get images from API __./
    public MovieImageAdapter(Context context, ArrayList<String> urls, RecycleCallback callback) {
        this.urls = urls;
        this.callback = callback;
        this.context = context;
    }

    public interface RecycleCallback {
        void itemSelected(int elementPosition);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View layoutView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_movie, viewGroup, false);
        return new MyViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;

        //.___ Glide image load and cache __./
        Glide.with(context)
                .load(urls.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public ArrayList<String> getUrlList(){
        return urls;
    }

    /**.___
     * ViewHolder class
     __.*/
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.movieBlock);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            callback.itemSelected(this.getLayoutPosition());
        }
    }

}