package com.example.jose_gonzalez.popularmovies.dbmodel;

/**.___
 * Created by jose-gonzalez on 14/12/15.
 __.*/
public class FavoriteModel {
    private long id;
    private String name;

    public FavoriteModel(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

}

