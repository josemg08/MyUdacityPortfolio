package com.example.jose_gonzalez.popularmovies.data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jose_gonzalez.popularmovies.dto.MovieDto;
import com.example.jose_gonzalez.popularmovies.dto.MoviePosterDto;
import com.example.jose_gonzalez.popularmovies.dto.ReviewListDto;
import com.example.jose_gonzalez.popularmovies.dto.TrailerListDto;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;

/**.___
 * Created by jose-gonzalez on 04/11/15.
 __.*/
@EBean(scope = EBean.Scope.Singleton)
public class PopularMoviesDataSource {
    @RestService
    PopularMoviesClient mApiClient;

    /**.___
     *  MovieAsyncHost callback interface for movie list
     __.*/
    public interface MovieAsyncHost {

        void movieAsyncUIExecute(MovieDto movieDto);

        void movieAsyncUIExecute(ArrayList<MoviePosterDto> moviePosterList);

    }

    /**.___
     *  TrailerAsyncHost callback interface for trailers
     __.*/
    public interface TrailerAsyncHost{

        void trailerAsyncUIExecute(TrailerListDto trailerListDto);

    }

    /**.___
     *  ReviewAsyncHost callback interface for review list
     __.*/
    public interface ReviewAsyncHost{

        void reviewAsyncUIExecute(ReviewListDto reviewListDto);

    }


    /**.___
     * getPopularMovies list in MovieDto
     __.*/
    public void getPopularMovies(final MovieAsyncHost asyncHost) {

        //.___ Async task bring info from API __./
        new AsyncTask<MovieDto, MovieDto, MovieDto>() {
            @Override
            protected MovieDto doInBackground(MovieDto... movieDtos) {
                return mApiClient.getMoviePosterByPopularity();
            }

            //.___ Update list ui after process finished __./
            @Override
            protected void onPostExecute(MovieDto result) {
                asyncHost.movieAsyncUIExecute(result);
            }
        }.execute();

    }

    /**.___
     * getMostVotedMovies list in MovieDto
     __.*/
    public void getMostVotedMovies(final MovieAsyncHost asyncHost) {

        //.___ Async task bring info from API __./
        new AsyncTask<MovieDto, MovieDto, MovieDto>() {
            @Override
            protected MovieDto doInBackground(MovieDto... movieDtos) {
                return mApiClient.getMoviePosterByMostVotes();
            }

            //.___ Update list ui after process finished __./
            @Override
            protected void onPostExecute(MovieDto result) {
                asyncHost.movieAsyncUIExecute(result);
            }
        }.execute();

    }

    /**.___
     * getLatestReleasedMovies list in MovieDto
     __.*/
    public void getLatestReleasedMovies(final MovieAsyncHost asyncHost) {

        //.___ Async task bring info from API __./
        new AsyncTask<MovieDto, MovieDto, MovieDto>() {
            @Override
            protected MovieDto doInBackground(MovieDto... movieDtos) {
                return mApiClient.getMoviePosterByReleaseDate();
            }

            //.___ Update list ui after process finished __./
            @Override
            protected void onPostExecute(MovieDto result) {
                asyncHost.movieAsyncUIExecute(result);
            }
        }.execute();

    }

    /**.___
     * getFavoriteMovies in ArrayList
     __.*/
    @SuppressWarnings("all")
    public void getFavoriteMovies(final MovieAsyncHost asyncHost, final Context context) {

        //.___ Async task bring info from API __./
        new AsyncTask<ArrayList<MoviePosterDto>, ArrayList<MoviePosterDto>, ArrayList<MoviePosterDto>>() {
            @Override
            protected ArrayList<MoviePosterDto> doInBackground(ArrayList<MoviePosterDto>... list) {
                DBHelper db = DBHelper.getHelper(context);
                ArrayList<MoviePosterDto> list1 = new ArrayList();
                for(String movie : db.getFavoriteMoviesPosterIDs()){
                    list1.add(mApiClient.getMovieById(movie));
                }
                return list1;
            }

            //.___ Update list ui after process finished __./
            @Override
            protected void onPostExecute(ArrayList<MoviePosterDto> result) {
                asyncHost.movieAsyncUIExecute(result);
            }

        }.execute();

    }

    /**.___
     * Get Trailer by movie id
     __.*/
    public void getMovieTrailer(final TrailerAsyncHost asyncHost, final String movieID) {

        //.___ Async task bring info from API __./
        new AsyncTask<TrailerListDto, TrailerListDto, TrailerListDto>() {
            @Override
            protected TrailerListDto doInBackground(TrailerListDto... trailerListDtos) {
                return mApiClient.getMovieTrailerById(movieID);
            }

            //.___ Update trailer __./
            @Override
            protected void onPostExecute(TrailerListDto result) {
                asyncHost.trailerAsyncUIExecute(result);
            }
        }.execute();

    }

    /**.___
     * Get reviews for movie
     __.*/
    public void getReviews(final ReviewAsyncHost asyncHost, final String movieID) {

        //.___ Async task bring info from API __./
        new AsyncTask<ReviewListDto, ReviewListDto, ReviewListDto>() {
            @Override
            protected ReviewListDto doInBackground(ReviewListDto... reviewListDtos) {
                return mApiClient.getMovieReviews(movieID);
            }

            //.___ Update list ui after process finished __./
            @Override
            protected void onPostExecute(ReviewListDto result) {
                asyncHost.reviewAsyncUIExecute(result);
            }
        }.execute();

    }

}
