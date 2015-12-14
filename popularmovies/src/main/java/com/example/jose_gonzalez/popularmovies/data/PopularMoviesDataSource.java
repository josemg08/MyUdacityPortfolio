package com.example.jose_gonzalez.popularmovies.data;

import android.os.AsyncTask;

import com.example.jose_gonzalez.popularmovies.dto.MovieDto;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

/**.___
 * Created by jose-gonzalez on 04/11/15.
 __.*/
@EBean(scope = EBean.Scope.Singleton)
public class PopularMoviesDataSource {
    @RestService
    PopularMoviesClient mApiClient;

    public interface AsyncHost{

        void asyncUIExecute(MovieDto movieDto);

    }

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

}
