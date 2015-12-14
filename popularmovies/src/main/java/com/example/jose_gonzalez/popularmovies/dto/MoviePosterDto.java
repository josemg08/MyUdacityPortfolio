package com.example.jose_gonzalez.popularmovies.dto;

import android.os.Parcelable;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**.___
 * Created by jose-gonzalez on 04/11/15.
 __.*/
@Parcel
public class MoviePosterDto implements Serializable, Parcelable{ //.___ stands for Data transfer object .__/

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

    protected MoviePosterDto(android.os.Parcel in) {
        posterUrl = in.readString();
        backDropUrl = in.readString();
        title = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        popularity = in.readFloat();
        voteAverage = in.readFloat();
        adult = in.readByte() != 0;
    }

    public static final Creator<MoviePosterDto> CREATOR = new Creator<MoviePosterDto>() {
        @Override
        public MoviePosterDto createFromParcel(android.os.Parcel in) {
            return new MoviePosterDto(in);
        }

        @Override
        public MoviePosterDto[] newArray(int size) {
            return new MoviePosterDto[size];
        }
    };

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

    public String getYear(){
        String year = "";
        for(int i = 0; i < 4; i++){
            year += getReleaseDate().charAt(i);
        }
        return year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel, int i) {
        parcel.writeString(posterUrl);
        parcel.writeString(backDropUrl);
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(releaseDate);
        parcel.writeFloat(popularity);
        parcel.writeFloat(voteAverage);
        parcel.writeByte((byte) (adult ? 1 : 0));
    }

}
