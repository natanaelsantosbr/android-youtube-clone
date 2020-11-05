package br.natanael.android.youtubeapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import br.natanael.android.youtubeapp.R;
import br.natanael.android.youtubeapp.adapter.AdapterVideo;
import br.natanael.android.youtubeapp.api.IYoutubeService;
import br.natanael.android.youtubeapp.helper.RetrofitConfig;
import br.natanael.android.youtubeapp.helper.YoutubeConfig;
import br.natanael.android.youtubeapp.listener.RecyclerItemClickListener;
import br.natanael.android.youtubeapp.model.Item;
import br.natanael.android.youtubeapp.model.Resultado;
import br.natanael.android.youtubeapp.model.Video;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerVideo;

    private List<Item> videos = new ArrayList<>();
    private Resultado resultado;

    private AdapterVideo adapterVideo;
    private MaterialSearchView searchView;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerVideo = findViewById(R.id.recyclerVideo);
        searchView = findViewById(R.id.searchView);

        //Configuracoes Iniciais
        retrofit = RetrofitConfig.getRetrofit();


        //Configurar toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Youtube");
        setSupportActionBar(toolbar);

        //Recupera videos
        recuperarVideos();


        //configura metodos para SearchView
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });
    }

    private void recuperarVideos() {
        IYoutubeService youtubeService = retrofit.create(IYoutubeService.class);

        youtubeService.recuperarVideos(
                YoutubeConfig.CANAL_ID,
                "date",
                "10",
                "snippet",
                YoutubeConfig.CHAVE_YOUTUBE_API
        ).enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                Log.d("resultado", "onResponse: resultado: " + response.toString());

                if(response.isSuccessful())
                {
                    Resultado resultado = response.body();
                    videos = resultado.items;

                    configurarRecyclerView();

                    Log.d("resultado", "onResponse: resultado: " + resultado.items.get(0).id.videoId);
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });
    }

    public void configurarRecyclerView() {
        adapterVideo = new AdapterVideo(videos, this);
        recyclerVideo.setHasFixedSize(true);
        recyclerVideo.setLayoutManager(new LinearLayoutManager(this));
        recyclerVideo.setAdapter(adapterVideo);

        //Configurar evento de cliques
        recyclerVideo.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerVideo, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Item video = videos.get(position);
                String idVideo = video.id.videoId;

                Intent i = new Intent(MainActivity.this, PlayerActivity.class);
                i.putExtra("idVideo", idVideo);
                startActivity(i);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        searchView.setMenuItem(item);

        return true;

    }
}
