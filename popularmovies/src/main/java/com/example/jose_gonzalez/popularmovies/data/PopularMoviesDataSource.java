package com.example.jose_gonzalez.popularmovies.data;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jose_gonzalez.popularmovies.dto.MovieDto;
import com.example.jose_gonzalez.popularmovies.dto.MoviePosterDto;
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
     *  AsyncHost callback interface for movie list
     __.*/
    public interface AsyncHost{

        void asyncUIExecute(MovieDto movieDto);

        void asyncUIExecute(ArrayList<MoviePosterDto> moviePosterList);

    }

    /**.___
     *  AsyncHost callback interface for trailer
     __.*/
    public interface TrailerAsyncHost{

        void asyncUIExecute(TrailerListDto trailerListDto);

    }

    /**.___
     * getPopularMovies list in MovieDto
     __.*/
    public void getPopularMovies(final AsyncHost asyncHost) {

        //.___ Async task bring info from API __./
        new AsyncTask<MovieDto, MovieDto, MovieDto>() {
            @Override
            protected MovieDto doInBackground(MovieDto... movieDtos) {
                return mApiClient.getMoviePosterByPopularity();
            }

            //.___ Update list ui after process finished __./
            @Override
            protected void onPostExecute(MovieDto result) {
                asyncHost.asyncUIExecute(result);
            }
        }.execute();

    }

    /**.___
     * getMostVotedMovies list in MovieDto
     __.*/
    public void getMostVotedMovies(final AsyncHost asyncHost) {

        //.___ Async task bring info from API __./
        new AsyncTask<MovieDto, MovieDto, MovieDto>() {
            @Override
            protected MovieDto doInBackground(MovieDto... movieDtos) {
                return mApiClient.getMoviePosterByMostVotes();
            }

            //.___ Update list ui after process finished __./
            @Override
            protected void onPostExecute(MovieDto result) {
                asyncHost.asyncUIExecute(result);
            }
        }.execute();

    }

    /**.___
     * getLatestReleasedMovies list in MovieDto
     __.*/
    public void getLatestReleasedMovies(final AsyncHost asyncHost) {

        //.___ Async task bring info from API __./
        new AsyncTask<MovieDto, MovieDto, MovieDto>() {
            @Override
            protected MovieDto doInBackground(MovieDto... movieDtos) {
                return mApiClient.getMoviePosterByReleaseDate();
            }

            //.___ Update list ui after process finished __./
            @Override
            protected void onPostExecute(MovieDto result) {
                asyncHost.asyncUIExecute(result);
            }
        }.execute();

    }

    /**.___
     * getFavoriteMovies in ArrayList
     __.*/
    public void getFavoriteMovies(final AsyncHost asyncHost, final Context context) {

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
                asyncHost.asyncUIExecute(result);
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
                asyncHost.asyncUIExecute(result);
            }
        }.execute();

    }

}
