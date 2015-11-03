package com.example.jose_gonzalez.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.EFragment;

/**.___
 * Created by jose-gonzalez on 02/11/15.
 __.*/
@EFragment(resName = "movies_list_fragment")
public class MoviesListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movies_list_fragment, container, false);
    }

}