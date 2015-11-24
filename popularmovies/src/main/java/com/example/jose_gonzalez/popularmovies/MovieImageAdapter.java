package com.example.jose_gonzalez.popularmovies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**.___
 *
 * Created by jose-gonzalez on 11/11/15.
 *
 * Custom recycle view adapter
 *
 __.*/
public class MovieImageAdapter extends RecyclerView.Adapter{

    private List<String> urls;
    private ArrayList<Bitmap> images;
    private RecicleCallback callback;
    private Context context;

    //.___ To get images from API __./
    public MovieImageAdapter(Context context, List<String> urls, RecicleCallback callback) {
        this.urls = urls;
        this.callback = callback;
        this.context = context;
        images = new ArrayList<>();
    }

    //.___ To use saved instance images __./
    public MovieImageAdapter(Context context, ArrayList<Bitmap> images, RecicleCallback callback) {
        urls = null;
        this.callback = callback;
        this.context = context;
        this.images = images;
    }

    public interface RecicleCallback{

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
        //String dataItem = urls.get(position);
        // Casting the viewHolder to MyViewHolder so I could interact with the views
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;

        if(urls != null) {
            //.___ Glide image load __./
            Glide.with(context)
                    .load(urls.get(position))
                    .into(myViewHolder.imageView);
            myViewHolder.imageView.buildDrawingCache();
            images.add(myViewHolder.imageView.getDrawingCache());
        }
        else{
            myViewHolder.imageView.setImageBitmap(images.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if(urls != null) {
            return urls.size();
        }
        else{
            return images.size();
        }
    }

    public ArrayList<Bitmap> getBitmapList(){
        return images;
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