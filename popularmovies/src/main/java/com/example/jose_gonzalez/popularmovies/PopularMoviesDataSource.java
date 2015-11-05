package com.example.jose_gonzalez.popularmovies;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by jose-gonzalez on 04/11/15.
 */
@EBean(scope = EBean.Scope.Singleton)
public class PopularMoviesDataSource {
    @RestService
    PopularMoviesClient mApiClient;

    public String getPopularMovies() {
        return mApiClient.getMoviePoster();
    }

}
