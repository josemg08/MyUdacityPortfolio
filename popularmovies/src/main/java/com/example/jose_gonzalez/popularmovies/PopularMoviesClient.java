package com.example.jose_gonzalez.popularmovies;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**.___
 * Created by jose-gonzalez on 04/11/15.
 *
 * Check https://github.com/excilys/androidannotations/wiki/Rest-API
 __.*/

@Rest(rootUrl = "http://api.themoviedb.org/3",
        converters = {
        GsonHttpMessageConverter.class,
        StringHttpMessageConverter.class
})
public interface PopularMoviesClient {

    //.___ Get movie list order by most popular decedent __./
    @Get("/discover/movie?sort_by=popularity.desc&api_key=" + MoviesListActivity.KEY)
    MovieDto getMoviePosterByPopularity();

    //.___ Get movie list order by most voted decedent __./
    @Get("/discover/movie?sort_by=vote_average.desc&api_key=" + MoviesListActivity.KEY)
    MovieDto getMoviePosterByMostVotes();

    //.___ Get movie list order by release date ascendant __./
    @Get("/discover/movie?sort_by=release_date.asc&api_key=" + MoviesListActivity.KEY)
    MovieDto getMoviePosterByReleaseDate();

}
