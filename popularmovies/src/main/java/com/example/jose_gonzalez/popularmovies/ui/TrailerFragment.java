package com.example.jose_gonzalez.popularmovies.ui;

import android.os.Bundle;

import com.example.jose_gonzalez.popularmovies.Configuration;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

/**.___
 * Created by jose-gonzalez on 13/01/16.
 __.*/

public class TrailerFragment extends YouTubePlayerSupportFragment{
    private YouTubePlayer activePlayer;

    public static TrailerFragment newInstance(String url) {
        TrailerFragment playerYouTubeFrag = new TrailerFragment();

        Bundle bundle = new Bundle();
        bundle.putString("url", url);

        playerYouTubeFrag.setArguments(bundle);
        playerYouTubeFrag.init();

        return playerYouTubeFrag;
    }

    private void init() {
        initialize(Configuration.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) { }

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                activePlayer = player;
                activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                if (!wasRestored) {
                    activePlayer.loadVideo(getArguments().getString("url"), 0);
                }
            }
        });
    }

}
