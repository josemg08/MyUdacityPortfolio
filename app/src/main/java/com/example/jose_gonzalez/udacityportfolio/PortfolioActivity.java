package com.example.jose_gonzalez.udacityportfolio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**.___
 * Created by Jose Gonzalez, 28/10/2015
 __.*/
@EActivity(resName = "portfolio_activity")
public class PortfolioActivity extends AppCompatActivity {

    @ViewById(resName = "toolbar")
    protected Toolbar mToolbar;
    @ViewById(resName = "description_text")
    protected TextView mDescriptionText;
    @ViewById(resName = "list_view")
    protected ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void init(){
        mToolbar.setTitle(R.string.my_udacity_portfolio);
        setSupportActionBar(mToolbar);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                R.layout.item_primary_button,
                getResources().getStringArray(R.array.nonodegree_apps_array) );

        mListView.setAdapter(arrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_portfolio, menu);
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

}
