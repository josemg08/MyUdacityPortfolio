package com.example.jose_gonzalez.popularmovies.ui;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jose_gonzalez.popularmovies.R;
import com.example.jose_gonzalez.popularmovies.data.PopularMoviesDataSource;
import com.example.jose_gonzalez.popularmovies.dto.MoviePosterDto;
import com.example.jose_gonzalez.popularmovies.dto.ReviewListDto;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Vector;

/**.___
 * Created by jose-gonzalez on 09/11/15.
 __.*/
@EFragment(resName = "movie_detail_fragment")
public class MovieDetailFragment extends Fragment implements PopularMoviesDataSource.ReviewAsyncHost{

    @Bean
    protected PopularMoviesDataSource mDataSource;

    @FragmentArg
    public MoviePosterDto mMoviePosterDto;
    @FragmentArg
    public boolean isFavorite;
    @FragmentArg
    public String trailerKey;

    @ViewById(resName = "title")
    protected TextView mTitle;
    @ViewById(resName = "year")
    protected TextView mYear;
    @ViewById(resName = "votes")
    protected TextView mVotes;
    @ViewById(resName = "overview")
    protected TextView mOverview;
    @ViewById(resName = "movie_image")
    protected ImageView mMovieImage;
    @ViewById(resName = "favorite")
    protected ImageView mFavorite;
    @ViewById(resName = "detail_view_pager")
    protected ViewPager mPager;
    @ViewById(resName = "tab_layout")
    protected TabLayout mTabLayout;

    private int mTabTitles[] = new int[]{R.string.trailers, R.string.reviews};

    private Callback mCallback;
    private List<Fragment> mFragmentPages;

    public interface Callback{
        void favoriteSelected(MoviePosterDto moviePosterDto, boolean favorite);
    }

    @AfterViews
    public void init(){
        mTitle.setText(mMoviePosterDto.getTitle());
        mYear.setText(mMoviePosterDto.getYear());
        //.___ Format to show only 2 decimals __./
        mVotes.setText(String.format(getResources().getString(
                        R.string.vote_average),
                "" + new DecimalFormat("##.##").format(mMoviePosterDto.getVoteAverage())));
        mOverview.setText(mMoviePosterDto.getOverview());

        favorite(isFavorite);

        //.___ Glide image load __./
        Glide.with(getContext())
                .load(MoviesListActivity.BASE_URL
                        + MoviesListActivity.DEVICE_SIZE
                        + mMoviePosterDto.getPosterUrl())
                .into(mMovieImage);

        initialisePaging(trailerKey);
        mDataSource.getReviews(this, mMoviePosterDto.getId() + "");
    }

    @Override
    public void reviewAsyncUIExecute(ReviewListDto reviewListDto) {
        ReviewAdapter mReviewAdapter = new ReviewAdapter(reviewListDto.getReviewDtos());
        ((ReviewListFragment)mFragmentPages.get(1)).setReviewAdapter(mReviewAdapter);
    }

    /**.___
     * To init the callback
     __.*/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (Callback) context;
        } catch (ClassCastException e) {
            //TODO catch
        }
    }

    @Click(resName = "favorite")
    void favorite() {
        if(isFavorite){
            mFavorite.setImageResource(android.R.drawable.btn_star_big_off);
            isFavorite = false;
        }
        else{
            mFavorite.setImageResource(android.R.drawable.btn_star_big_on);
            isFavorite = true;
        }

        mCallback.favoriteSelected(mMoviePosterDto, isFavorite);
    }

    void favorite(boolean favorite) {
        if(!favorite){
            mFavorite.setImageResource(android.R.drawable.btn_star_big_off);
        }
        else{
            mFavorite.setImageResource(android.R.drawable.btn_star_big_on);
        }
    }

    //.___ Fragment pager __./

    private void initialisePaging(String urlKey){
        mFragmentPages = new Vector<>();

        mFragmentPages.add(TrailerFragment.newInstance(urlKey));
        mFragmentPages.add(Fragment.instantiate(getContext(), ReviewListFragment_.class.getName()));
        PagerAdapter mPagerAdapter =
                new PagerAdapter(getChildFragmentManager(), mFragmentPages);
        mPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(mPagerAdapter.getTabView(i));
        }
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }

        public View getTabView(int position) {
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.tab_layout, null);
            textView.setText(mTabTitles[position]);
            return textView;
        }
    }

}

