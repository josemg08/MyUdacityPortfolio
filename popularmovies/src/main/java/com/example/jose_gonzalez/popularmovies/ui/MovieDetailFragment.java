package com.example.jose_gonzalez.popularmovies.ui;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jose_gonzalez.popularmovies.R;
import com.example.jose_gonzalez.popularmovies.dto.MoviePosterDto;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.text.DecimalFormat;

/**.___
 * Created by jose-gonzalez on 09/11/15.
 __.*/
@EFragment(resName = "movie_detail_fragment")
public class MovieDetailFragment extends Fragment {

    @FragmentArg
    public MoviePosterDto mMoviePosterDto;

    @ViewById(resName = "title")
    protected TextView mTitle;
    @ViewById(resName = "year")
    protected TextView mYear;
    @ViewById(resName = "votes")
    protected TextView mVotes;
    @ViewById(resName = "overview")
    protected TextView mOverview;
    @ViewById(resName = "movie_image")
    protected ImageView mMovieImage;
    @ViewById(resName = "favorite")
    protected ImageView mFavorite;

    private boolean isFavorite;

    @AfterViews
    public void init(){
        mTitle.setText(mMoviePosterDto.getTitle());
        mYear.setText(mMoviePosterDto.getYear());
        //.___ Format to show only 2 decimals __./
        mVotes.setText(String.format(getResources().getString(
                R.string.vote_average),
                "" + new DecimalFormat("##.##").format(mMoviePosterDto.getVoteAverage())));
        mOverview.setText(mMoviePosterDto.getOverview());

        //.___ Glide image load __./
        Glide.with(getContext())
                .load(MoviesListActivity.BASE_URL
                        + MoviesListActivity.DEVICE_SIZE
                        + mMoviePosterDto.getPosterUrl())
                .into(mMovieImage);
    }

    @Click(resName = "favorite")
    void favorite() {
        if(isFavorite){
            mFavorite.setImageResource(android.R.drawable.btn_star_big_off);
            isFavorite = false;
        }
        else{
            mFavorite.setImageResource(android.R.drawable.btn_star_big_on);
            isFavorite = true;
        }
    }

}

