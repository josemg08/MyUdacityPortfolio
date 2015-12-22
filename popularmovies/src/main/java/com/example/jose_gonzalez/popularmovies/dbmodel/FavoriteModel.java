package com.example.jose_gonzalez.popularmovies.dbmodel;

/**.___
 * Created by jose-gonzalez on 14/12/15.
 __.*/
public class FavoriteModel {
    private long id;
    private String url;
    private String name;

    public FavoriteModel(long id, String url, String name){
        this.id = id;
        this.url = url;
        this.name = name;
    }

    public long getId(){
        return id;
    }

    public String getUrl(){
        return url;
    }

    public String getName(){
        return name;
    }

}

