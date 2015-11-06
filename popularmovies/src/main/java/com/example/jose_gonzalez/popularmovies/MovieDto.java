package com.example.jose_gonzalez.popularmovies;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jose-gonzalez on 06/11/15.
 */
@Parcel
public class MovieDto implements Serializable {

    @SerializedName("results")
    protected List<MoviePosterDto> movies;

    public List<MoviePosterDto> getMovies() {
        return movies;
    }

}
