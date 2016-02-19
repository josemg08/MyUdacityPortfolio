package com.example.jose_gonzalez.popularmovies.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jose_gonzalez.popularmovies.R;
import com.example.jose_gonzalez.popularmovies.dto.ReviewDto;

import java.util.List;

/**.___
 * Created by jose-gonzalez on 19/02/16.
 __.*/
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.reviewViewHolder>{

    private List<ReviewDto> reviewDtoList;

    public ReviewAdapter(List<ReviewDto> reviewDtoList) {
        this.reviewDtoList = reviewDtoList;
    }

    @Override
    public int getItemCount() {
        return reviewDtoList.size();
    }

    @Override
    public void onBindViewHolder(reviewViewHolder myViewHolder, int i) {
        ReviewDto reviewDto = reviewDtoList.get(i);
        myViewHolder.reviewContent.setText(reviewDto.getReviewContent());
        myViewHolder.reviewer.setText(reviewDto.getAuthor());
    }

    @Override
    public reviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_review, viewGroup, false);

        return new reviewViewHolder(itemView);
    }

    /**.___
     * ViewHolder class
     __.*/
    public class reviewViewHolder extends RecyclerView.ViewHolder{

        private TextView reviewer;
        private TextView reviewContent;

        public reviewViewHolder(View itemView) {
            super(itemView);
            reviewContent = (TextView) itemView.findViewById(R.id.review_content);
            reviewer = (TextView) itemView.findViewById(R.id.reviewer);
        }

    }

}
