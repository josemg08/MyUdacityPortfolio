package com.example.jose_gonzalez.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**.___
 * Created by jose-gonzalez on 09/11/15.
 __.*/
@EFragment(resName = "movie_detail_fragment")
public class MovieDetailFragment extends Fragment {

    @FragmentArg
    public MoviePosterDto mMoviePosterDto;

    @ViewById(resName = "title")
    protected TextView mTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movie_detail_fragment, container, false);
    }

    @AfterViews
    public void init(){
        mTitle.setText(mMoviePosterDto.getTitle());
    }

    public void setMoviePosterDto(MoviePosterDto moviePosterDto){
        mMoviePosterDto = moviePosterDto;
    }

}

