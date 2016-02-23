package com.example.jose_gonzalez.popularmovies.ui;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**.___
 * Created by jose-gonzalez on 13/01/16.
 __.*/

@EFragment(resName = "reviews_list_fragment")
public class ReviewListFragment extends Fragment{

    @ViewById(resName = "review_recycler_view")
    protected RecyclerView mRecycleView;

    @AfterViews
    public void init(){
        //.___ Setting the layoutManager __./
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
    }

    public void setReviewAdapter(ReviewAdapter reviewAdapter){
        mRecycleView.setAdapter(reviewAdapter);
    }

}
