package com.example.jose_gonzalez.popularmovies.dto;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.io.Serializable;

/**.___
 * Created by jose-gonzalez on 02/02/16.
 __.*/

@Parcel
public class TrailerDto implements Serializable {

    @SerializedName("name")
    protected String name;

    @SerializedName("key")
    protected String key;

    @SerializedName("site")
    protected String site;

    @SerializedName("type")
    protected String type;

    public String getKey() {
        return key;
    }

    public String getSite() {
        return site;
    }

}
