package com.example.jose_gonzalez.popularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**.___
 * Created by jose-gonzalez on 02/11/15.
 __.*/
@EActivity(resName = "movies_list_activity")
public class MoviesListActivity extends AppCompatActivity {

    @ViewById(resName = "toolbar")
    protected Toolbar mToolbar;
    @ViewById(resName = "movieImage")
    protected ImageView mMovieImage;
    @ViewById(resName = "recyclerView")
    RecyclerView mRecicleView;

    private static final String URL = "http://ecx.images-amazon.com/images/I/41xfBGxgdNL.jpg";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void init() {
        mToolbar.setTitle("Hola");
        setSupportActionBar(mToolbar);

        //Glide sample
        /*Glide.with(this)
                .load(URL)
                .into(mMovieImage);*/


        //Implementing recycle view sample

        // Populating our data set
        List<ItemMovie> dataItems = new ArrayList<>();
        dataItems.add(new ItemMovie("Indigo", "#3F51B5"));
        dataItems.add(new ItemMovie("Pink", "#E91E63"));
        dataItems.add(new ItemMovie("Orange", "#FF5722"));
        dataItems.add(new ItemMovie("Green", "#4CAF50"));
        dataItems.add(new ItemMovie("Grey", "#607D8B"));
        dataItems.add(new ItemMovie("Cyan", "#00BCD4"));
        dataItems.add(new ItemMovie("Amber", "#FFC107"));
        dataItems.add(new ItemMovie("Brown", "#795548"));
        dataItems.add(new ItemMovie("Blue", "#03A9F4"));
        dataItems.add(new ItemMovie("Red", "#F44336"));

        // Creating new adapter object
        MyAdapter myAdapter = new MyAdapter(dataItems);
        mRecicleView.setAdapter(myAdapter);

        // Setting the layoutManager
        mRecicleView.setLayoutManager(new GridLayoutManager(this, 2));
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
     * Custom recicle view adapter
    __.*/

    public class MyAdapter extends RecyclerView.Adapter {

        private List<ItemMovie> dataItems;

        // Adapter constructor
        public MyAdapter(List<ItemMovie> dataItems) {

            this.dataItems = dataItems;
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

            ItemMovie dataItem = dataItems.get(position);
            // Casting the viewHolder to MyViewHolder so I could interact with the views
            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            myViewHolder.colorBlock.setBackgroundColor(dataItem.getColor());
            myViewHolder.colorName.setText(dataItem.getName());
        }

        @Override
        public int getItemCount() {

            return dataItems.size();
        }

        /** This is our ViewHolder class */
        public class MyViewHolder extends RecyclerView.ViewHolder {

            public TextView colorName;
            public View colorBlock;

            public MyViewHolder(View itemView) {
                super(itemView); // Must call super() first
                colorName = (TextView) itemView.findViewById(R.id.colorName);
                colorBlock = (View) itemView.findViewById(R.id.colorBlock);

            }
        }
    }

}
