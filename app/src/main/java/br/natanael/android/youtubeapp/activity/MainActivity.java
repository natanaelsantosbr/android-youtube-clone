package br.natanael.android.youtubeapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

import br.natanael.android.youtubeapp.R;
import br.natanael.android.youtubeapp.adapter.AdapterVideo;
import br.natanael.android.youtubeapp.model.Video;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerVideo;
    private List<Video> videos = new ArrayList<>();
    private AdapterVideo adapterVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerVideo = findViewById(R.id.recyclerVideo);


        //Configurar toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Youtube");
        setSupportActionBar(toolbar);

        recuperarVideos();
        adapterVideo = new AdapterVideo(videos, this);
        recyclerVideo.setHasFixedSize(true);
        recyclerVideo.setLayoutManager(new LinearLayoutManager(this));
        recyclerVideo.setAdapter(adapterVideo);
    }

    private void recuperarVideos() {
        Video video1 = new Video();
        video1.setTitulo("Video 1 muito interessante");
        videos.add(video1);

        Video video2 = new Video();
        video2.setTitulo("Video 2 muito interessante");
        videos.add(video2);
    }
}
