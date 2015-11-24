package com.example.jose_gonzalez.popularmovies;

import android.content.res.Configuration;
import android.graphics.Bitmap;
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
    private ArrayList<Bitmap> dataItemsBitmap;
    private MovieImageAdapter movieImageAdapter;
    private List<MoviePosterDto> movieList;
    private boolean resurrected = false;

    //.___ Available sizes: w92/, w154/, w185/, w342/, w500/, w780/ __./
    public static final String DEVICE_SIZE = "w342/";
    public static final String BASE_URL = "http://image.tmdb.org/t/p/";
    //.___ KEY that grants permission to interact whit API __./
    public static final String KEY = "48b95a671f15deb4851700a9a10b42c8";
    public static final String BITMAP_MOVIE_LIST = "bitmap_movie_list";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null || !savedInstanceState.containsKey(BITMAP_MOVIE_LIST)) {
            //.___ Async task bring info from API __./
            mDataSource.getPopularMovies(this);
            dataItems = new ArrayList<>();
            resurrected = false;
        }
        else{
            dataItemsBitmap = savedInstanceState.getParcelableArrayList(BITMAP_MOVIE_LIST);
            resurrected = true;
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(BITMAP_MOVIE_LIST, movieImageAdapter.getBitmapList());
        super.onSaveInstanceState(outState);
    }

    @AfterViews
    protected void init() {
        mToolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(mToolbar);

        //.___ Setting the layoutManager __./
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mRecicleView.setLayoutManager(new GridLayoutManager(this, 2));
        }
        else{
            mRecicleView.setLayoutManager(new GridLayoutManager(this, 3));
        }

        if(!resurrected){
            movieImageAdapter = new MovieImageAdapter(getApplicationContext(), dataItems, this);
        }
        else{
            movieImageAdapter = new MovieImageAdapter(getApplicationContext(), dataItemsBitmap, this);
        }
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
            dataItems.add(BASE_URL + DEVICE_SIZE + moviePosterDto.getPosterUrl());
        }

        movieImageAdapter = new MovieImageAdapter(getApplicationContext(), dataItems, this);
        mRecicleView.setAdapter(movieImageAdapter);
        mRecicleView.getAdapter().notifyDataSetChanged();
    }

    //.___ Callback from RecycleView, To respond to item selection __./
    @Override
    public void itemSelected(int elementPosition) {
        MovieDetailFragment fragment = MovieDetailFragment_.builder()
                .mMoviePosterDto(movieList.get(elementPosition)).build();
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
