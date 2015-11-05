package com.example.jose_gonzalez.popularmovies;

import android.os.AsyncTask;
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

/**
 * .___
 * Created by jose-gonzalez on 02/11/15.
 * __.
 */
@EActivity(resName = "movies_list_activity")
public class MoviesListActivity extends AppCompatActivity {

    @Bean
    protected PopularMoviesDataSource mDataSource;

    @ViewById(resName = "toolbar")
    protected Toolbar mToolbar;
    @ViewById(resName = "recyclerView")
    protected RecyclerView mRecicleView;

    //TODO add different variants for every size (w185/ ...)
    private static final String API_KEY = "48b95a671f15deb4851700a9a10b42c8";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void init() {
        mToolbar.setTitle("Hola");
        setSupportActionBar(mToolbar);

        //Implementing recycle view sample

        // Populating our data set
        List<String> dataItems = new ArrayList<>();
        //dataItems.add(new String(BASE_URL + "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg@"));

        // Creating new adapter object
        MovieImageAdapter movieImageAdapter = new MovieImageAdapter(dataItems);
        mRecicleView.setAdapter(movieImageAdapter);

        // Setting the layoutManager
        mRecicleView.setLayoutManager(new GridLayoutManager(this, 2));

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                mDataSource.getPopularMovies();
                return null;
            }
        };
        asyncTask.execute();

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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

            //.___ Glide sample __./
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
