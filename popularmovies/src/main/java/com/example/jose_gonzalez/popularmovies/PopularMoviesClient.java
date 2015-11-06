package com.example.jose_gonzalez.popularmovies;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**.___
 * Created by jose-gonzalez on 04/11/15.
 *
 * Check https://github.com/excilys/androidannotations/wiki/Rest-API
 __.*/

@Rest(rootUrl = "http://api.themoviedb.org/3",
        converters = {
        GsonHttpMessageConverter.class,
        StringHttpMessageConverter.class//TODO Converters for json
})
public interface PopularMoviesClient {

    String KEY = "Cant share this key add yours";

    @Get("/discover/movie?sort_by=popularity.desc&api_key=" + KEY)
    MovieDto getMoviePoster(); //TODO change to moviePosterDto, check API to know the "$#&/ works

}
