package com.example.jose_gonzalez.popularmovies.dto;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;

/**.___
 * Created by jose-gonzalez on 19/02/16.
 __.*/
@Parcel
public class ReviewDto implements Serializable{

    @SerializedName("author")
    protected String author;

    @SerializedName("content")
    protected String reviewContent;

    public String getAuthor() {
        return author;
    }

    public String getReviewContent() {
        return reviewContent;
    }

}
