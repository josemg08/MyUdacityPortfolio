package com.example.jose_gonzalez.popularmovies;

import android.graphics.Color;

/**.___
 * Created by jose-gonzalez on 02/11/15.
 __.*/
public class ItemMovie {
    private String name;
    private int color;

    public ItemMovie(String name, String color) {
        this.name = name;
        this.color = Color.parseColor(color);
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

}
