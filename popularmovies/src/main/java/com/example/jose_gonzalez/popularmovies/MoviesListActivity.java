package com.example.jose_gonzalez.popularmovies;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentByTag;
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

    @FragmentByTag("portfolio_fragment")
    Fragment ffragment;

    @ViewById(resName = "toolbar")
    protected Toolbar mToolbar;
    @ViewById(resName = "recyclerView")
    protected RecyclerView mRecicleView;

    private List<String> dataItems;
    private MovieImageAdapter movieImageAdapter;

    private static final String DEVICE_SIZE = "w92/",
                                DEVICE_SIZE1 = "w154/",
                                DEVICE_SIZE2 = "w185/",
                                DEVICE_SIZE3 = "w342/",
                                DEVICE_SIZE4 = "w500/",
                                DEVICE_SIZE5 = "w780/";
    private static final String BASE_URL = "http://image.tmdb.org/t/p/";

    public static final String KEY = "48b95a671f15deb4851700a9a10b42c8";

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

    //.___ Callback from dataSource, To be called after the asyncTask finishes __./
    @Override
    public void asyncUIExecute(MovieDto movieDto) {
        //.___ Populating our data set __./
        dataItems.clear();
        for (MoviePosterDto moviePosterDto : movieDto.getMovies()) {
            dataItems.add(BASE_URL + DEVICE_SIZE3 + moviePosterDto.getPosterUrl());
        }

        movieImageAdapter = new MovieImageAdapter(getApplicationContext(), dataItems, this);
        mRecicleView.setAdapter(movieImageAdapter);
        mRecicleView.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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

    @Override
    public void itemSelected(int elementPosition) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        //fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.movieListFragment, fragment)
                .commit();

    }

}
