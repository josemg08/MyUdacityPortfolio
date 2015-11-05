package com.example.jose_gonzalez.popularmovies;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**.___
 * Created by jose-gonzalez on 04/11/15.
 *
 * Check https://github.com/excilys/androidannotations/wiki/Rest-API
 __.*/

@Rest(rootUrl = "http://api.themoviedb.org/3",
        converters = {
        StringHttpMessageConverter.class
})
public interface PopularMoviesClient {

    @Get("/discover/movie?sort_by=popularity.desc&api_key=48b95a671f15deb4851700a9a10b42c8")
    String getMoviePoster(); //TODO

}
