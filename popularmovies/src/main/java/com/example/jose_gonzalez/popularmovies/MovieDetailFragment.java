package com.example.jose_gonzalez.popularmovies;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
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
                        + mMoviePosterDto.posterUrl)
                .into(mMovieImage);
    }

}

