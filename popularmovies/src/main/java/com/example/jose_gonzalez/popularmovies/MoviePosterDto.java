package com.example.jose_gonzalez.popularmovies;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by jose-gonzalez on 04/11/15.
 */
@Parcel
public class MoviePosterDto implements Serializable { //.___ stands for Data transfer object .__/

    @SerializedName("poster_path")
    protected String posterUrl;

    public String getPosterUrl() {
        return posterUrl;
    }

}
