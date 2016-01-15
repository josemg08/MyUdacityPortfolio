package com.example.jose_gonzalez.popularmovies.ui;

import com.example.jose_gonzalez.popularmovies.Config;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**.___
 * Created by jose-gonzalez on 13/01/16.
 __.*/

@EFragment(resName = "trailers_list_fragment")
public class TrailersListFragment extends YouTubePlayerSupportFragment {

    @ViewById(resName = "youtube_view")
    protected YouTubePlayerView mYouTubePlayerView;

    @AfterViews
    public void init() {
        mYouTubePlayerView.initialize(Config.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {}

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer
                    player, boolean wasRestored) {
                String id = "3j36xYpE";
                player.loadVideo(id);
            }
        });
    }

}
