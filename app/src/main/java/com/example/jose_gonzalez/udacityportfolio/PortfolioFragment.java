package com.example.jose_gonzalez.udacityportfolio;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**.___
 * Created by Jose Gonzalez, 28/10/2015
 *
 * A placeholder fragment containing a simple view.
 __.*/
public class PortfolioFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.portfolio_fragment, container, false);
    }

}
