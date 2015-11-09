package com.example.jose_gonzalez.popularmovies;

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
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**.___
 * Created by jose-gonzalez on 02/11/15.
 __.*/
@EActivity(resName = "movies_list_activity")
public class MoviesListActivity extends AppCompatActivity implements PopularMoviesDataSource.AsyncHost{

    @Bean
    protected PopularMoviesDataSource mDataSource;

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

    public static final String KEY = "Add your key!";

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
        movieImageAdapter = new MovieImageAdapter(dataItems);
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

        movieImageAdapter = new MovieImageAdapter(dataItems);
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

    /**.___
     * Custom recycle view adapter
     __.*/

    public class MovieImageAdapter extends RecyclerView.Adapter {

        private List<String> urls;

        // Adapter constructor
        public MovieImageAdapter(List<String> urls) {
            this.urls = urls;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

            View layoutView = LayoutInflater
                    .from(viewGroup.getContext())
                    .inflate(R.layout.item_movie, null);
            return new MyViewHolder(layoutView);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

            String dataItem = urls.get(position);
            // Casting the viewHolder to MyViewHolder so I could interact with the views
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;

            //.___ Glide image load __./
            Glide.with(getApplicationContext())
                    .load(urls.get(position))
                    .into(myViewHolder.imageView);
        }

        @Override
        public int getItemCount() {
            return urls.size();
        }

        /**
         * This is our ViewHolder class
         */
        public class MyViewHolder extends RecyclerView.ViewHolder {

            public ImageView imageView;

            public MyViewHolder(View itemView) {
                super(itemView); // Must call super() first
                imageView = (ImageView) itemView.findViewById(R.id.movieBlock);
            }
        }
    }

}
