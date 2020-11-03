package br.natanael.android.youtubeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubePlayerView;
    private static final String GOOGLE_API_KEY = "AIzaSyBf7K-Eqt1QYt1VXQCgaM0xUYST0dk_Qas";

    private YouTubePlayer.PlaybackEventListener playbackEventListener;
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
            @Override
            public void onLoading() {
                Toast.makeText(MainActivity.this, "Video carregando", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoaded(String s) {
                Toast.makeText(MainActivity.this, "Video carregado", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAdStarted() {
                Toast.makeText(MainActivity.this, "Propaganda inicicou", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoStarted() {
                Toast.makeText(MainActivity.this, "Video comecando", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVideoEnded() {
                Toast.makeText(MainActivity.this, "Video chegou ao final", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(YouTubePlayer.ErrorReason errorReason) {
                Toast.makeText(MainActivity.this, "Erro: " + errorReason.toString(), Toast.LENGTH_SHORT).show();
            }
        };

        playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {
                Toast.makeText(MainActivity.this, "Play", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPaused() {
                Toast.makeText(MainActivity.this, "Pause", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopped() {
                Toast.makeText(MainActivity.this, "Stop", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onBuffering(boolean b) {
                Toast.makeText(MainActivity.this, "video pre carregando", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSeekTo(int i) {
                Toast.makeText(MainActivity.this, "Seek: " + i, Toast.LENGTH_SHORT).show();

            }
        };
        youTubePlayerView = findViewById(R.id.viewYoutubePlayer);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this);


    }



    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean foiRestaurado) {
        if(!foiRestaurado)
        {
            //Carregar apenas a miniatura
            //youTubePlayer.cueVideo("SNl6bn-K1LU");
            youTubePlayer.cuePlaylist("PL4o29bINVT4EG_y-k5jGoOu3-Am8Nvi10");
            //youTubePlayer.setPlaybackEventListener(playbackEventListener );
            youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        }
        else
        {
            //Carregar video automaticamente
            //youTubePlayer.loadVideo("SNl6bn-K1LU");
        }

        Toast.makeText(this, "Player iniciado com sucesso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Erro ao iniciar o Player: " + youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
    }
}
