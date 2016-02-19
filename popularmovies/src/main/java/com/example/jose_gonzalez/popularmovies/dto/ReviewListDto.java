package com.example.jose_gonzalez.popularmovies.dto;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;

/**.___
 * Created by jose-gonzalez on 19/02/16.
 __.*/
@Parcel
public class ReviewListDto implements Serializable {

    @SerializedName("results")
    protected ArrayList<ReviewDto> reviewDtos;

    public ArrayList<ReviewDto> getReviewDtos() {
        return reviewDtos;
    }

}
