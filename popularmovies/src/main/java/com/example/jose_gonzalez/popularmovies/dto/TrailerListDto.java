package com.example.jose_gonzalez.popularmovies.dto;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;

/**.___
 * Created by jose-gonzalez on 02/02/16.
 __.*/

@Parcel
public class TrailerListDto implements Serializable{

    @SerializedName("results")
    protected ArrayList<TrailerDto> trailers;

    public ArrayList<TrailerDto> getTrailers() {
        return trailers;
    }

}
