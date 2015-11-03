package com.example.jose_gonzalez.udacityportfolio;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.jar.Attributes;

/**.___
 * Created by Jose Gonzalez, 28/10/2015
 __.*/
@EActivity(resName = "portfolio_activity")
public class PortfolioActivity extends AppCompatActivity {

    @ViewById(resName = "toolbar")
    protected Toolbar mToolbar;
    @ViewById(resName = "list_view")
    protected ListView mListView;
    @ViewById(resName = "description_text")
    protected TextView mDescriptionText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void init() {
        mToolbar.setTitle(R.string.my_udacity_portfolio);
        setSupportActionBar(mToolbar);

        PortfolioButtonAdapter adapter = new PortfolioButtonAdapter();
        adapter.items = getResources().getStringArray(R.array.nonodegree_apps_array);
        mListView.setAdapter(adapter);

        mDescriptionText.setText(R.string.my_nanodegree_apps);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "App", Toast.LENGTH_SHORT).show();
            }
        });*/
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

    /**.___
     * Custom portfolioButtonAdapter
     __.*/
    private class PortfolioButtonAdapter extends BaseAdapter {

        private String[] items;

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int i) {
            return items[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_primary_button, null);
            ((Button) view.findViewById(R.id.button)).setText(items[i]);

            final int element = i;

            view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (i == 0) {
                        Intent intent;
                        PackageManager manager = getPackageManager();
                        try {
                            intent = manager.getLaunchIntentForPackage("com.example.jose_gonzalez.popularmovies");
                            if (intent == null)
                                throw new PackageManager.NameNotFoundException();
                            intent.addCategory(Intent.CATEGORY_LAUNCHER);
                            startActivity(intent);
                        } catch (PackageManager.NameNotFoundException e) {
                            //lol
                        }
                    } else {
                        Toast.makeText(view.getContext(), " " + items[element], Toast.LENGTH_LONG).show();
                    }
                }
            });

            return view;
        }
    }

}
