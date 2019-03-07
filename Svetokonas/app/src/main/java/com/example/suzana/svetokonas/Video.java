package com.example.suzana.svetokonas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayerView;

public class Video extends YouTubeBaseActivity implements OnInitializedListener {


    public static final String API_KEY="AIzaSyBEPGFKpiBpYH4x6xSiLJ_S9APzX3CUXhY";
    public static  String VIDEO_ID="";
    public int broj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Intent i=getIntent();
        broj=i.getIntExtra("br",0);
        //inicijalizacija YouTube Player View
        YouTubePlayerView youTubePlayerView=(YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(API_KEY, this);
    }
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result)
    {
        Toast.makeText(this, "greska pri inicijalizaciji",  Toast.LENGTH_LONG).show();
    }
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,YouTubePlayer player, boolean wasRestored) {
        //dodajemo listener na youtube instancu
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playBackEventListener);
        //start buffering
        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);

        }
    }
    private YouTubePlayer.PlaybackEventListener playBackEventListener=new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {

        }

        @Override
        public void onPaused() {

        }

        @Override
        public void onStopped() {

        }

        @Override
        public void onBuffering(boolean b) {

        }

        @Override
        public void onSeekTo(int i) {

        }
    };
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener=new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {

        }

        @Override
        public void onLoaded(String s) {

        }

        @Override
        public void onAdStarted() {

        }

        @Override
        public void onVideoStarted() {

        }

        @Override
        public void onVideoEnded() {
            finish();
            if(broj==3)
            {
             Intent i=new Intent(Video.this,listaVidea.class);
             i.putExtra("br",3);
             startActivity(i);
            }
            else if(broj==2)
            {
                Intent i=new Intent(Video.this,listaVidea.class);
                i.putExtra("br",2);
                startActivity(i);
            }
            else
            {
                    Intent i=new Intent(Video.this,listaVidea.class);
                    i.putExtra("br",1);
                    startActivity(i);
            }


        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {

        }
    };
}
