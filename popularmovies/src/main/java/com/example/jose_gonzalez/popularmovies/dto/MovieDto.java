package com.example.jose_gonzalez.popularmovies.dto;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;

/**.___
 * Created by jose-gonzalez on 06/11/15.
 __.*/
@Parcel
public class MovieDto implements Serializable {

    @SerializedName("results")
    protected ArrayList<MoviePosterDto> movies;

    public ArrayList<MoviePosterDto> getMovies() {
        return movies;
    }

}
