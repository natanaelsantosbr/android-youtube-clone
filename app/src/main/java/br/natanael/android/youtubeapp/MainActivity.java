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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = findViewById(R.id.viewYoutubePlayer);

        youTubePlayerView.initialize(GOOGLE_API_KEY, this);

    }



    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean foiRestaurado) {





        if(!foiRestaurado)
        {
            //Carregar apenas a miniatura
            youTubePlayer.cueVideo("SNl6bn-K1LU");
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
        Toast.makeText(this, "Erro ao iniciar o Player", Toast.LENGTH_SHORT).show();
    }
}
