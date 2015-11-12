package com.example.jose_gonzalez.popularmovies;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**.___
 * Created by jose-gonzalez on 04/11/15.
 __.*/
@Parcel
public class MoviePosterDto implements Serializable { //.___ stands for Data transfer object .__/

    @SerializedName("poster_path")
    protected String posterUrl;
    @SerializedName("backdrop_path")
    protected String backDropUrl;
    @SerializedName("title")
    protected String title;
    @SerializedName("overview")
    protected String overview;
    @SerializedName("release_date")
    protected String releaseDate;
    @SerializedName("popularity")
    protected float popularity;
    @SerializedName("vote_average")
    protected float voteAverage;
    @SerializedName("adult")
    protected boolean adult;

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getBackDropUrl() {
        return backDropUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public float getPopularity() {
        return popularity;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public boolean getAdult() {
        return adult;
    }

}
