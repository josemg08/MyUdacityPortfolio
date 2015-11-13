package com.example.jose_gonzalez.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**.___
 * Created by jose-gonzalez on 02/11/15.
 __.*/
@EActivity(resName = "movies_list_activity")
public class MoviesListActivity extends AppCompatActivity implements PopularMoviesDataSource.AsyncHost, MovieImageAdapter.RecicleCallback{

    @Bean
    protected PopularMoviesDataSource mDataSource;

    @ViewById(resName = "toolbar")
    protected Toolbar mToolbar;
    @ViewById(resName = "recyclerView")
    protected RecyclerView mRecicleView;

    private List<String> dataItems;
    private MovieImageAdapter movieImageAdapter;
    private List<MoviePosterDto> movieList;

    //.___ Available sizes: w92/, w154/, w185/, w342/, w500/, w780/ __./
    private static final String DEVICE_SIZE3 = "w342/";
    private static final String BASE_URL = "http://image.tmdb.org/t/p/";

    //.___ KEY that grants permission to interact whit API __./
    public static final String KEY = "Add your key here!";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //.___ Async task bring info from API __./
        mDataSource.getPopularMovies(this);
    }

    @AfterViews
    protected void init() {
        mToolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(mToolbar);

        //.___ Setting the layoutManager __./
        mRecicleView.setLayoutManager(new GridLayoutManager(this, 2));

        dataItems = new ArrayList<>();
        movieImageAdapter = new MovieImageAdapter(getApplicationContext(), dataItems, this);
        mRecicleView.setAdapter(movieImageAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //.___ Callback from dataSource, To be called after the asyncTask finishes __./
    @Override
    public void asyncUIExecute(MovieDto movieDto) {
        //.___ Populating our data set __./
        dataItems.clear();
        movieList = movieDto.getMovies();
        for (MoviePosterDto moviePosterDto : movieList) {
            dataItems.add(BASE_URL + DEVICE_SIZE3 + moviePosterDto.getPosterUrl());
        }

        movieImageAdapter = new MovieImageAdapter(getApplicationContext(), dataItems, this);
        mRecicleView.setAdapter(movieImageAdapter);
        mRecicleView.getAdapter().notifyDataSetChanged();
    }

    //.___ Callback from RecycleView, To respond to item selection __./
    @Override
    public void itemSelected(int elementPosition) {
        //MovieDetailFragment fragment = MovieDetailFragment_.builder().mMoviePosterDto(movieList.get(elementPosition)); //TODO send DTO as fragmentarg
        MovieDetailFragment fragment = MovieDetailFragment_.builder().build();
        fragment.setMoviePosterDto(movieList.get(elementPosition));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.portfolio_fragment, fragment)
                .addToBackStack("tag")
                .commit();
    }

    //.___ Action bar menu __./

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.sort_by_popularity_id:
                mDataSource.getPopularMovies(this);
                return true;
            case R.id.sort_by_votes_id:
                mDataSource.getMostVotedMovies(this);
                return true;
            case R.id.sort_by_release_id:
                mDataSource.getLatestReleasedMovies(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
