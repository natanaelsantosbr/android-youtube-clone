package br.natanael.android.youtubeapp.api;

import br.natanael.android.youtubeapp.model.Resultado;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IYoutubeService {

    @GET("search")
    Call<Resultado> recuperarVideos(@Query("channelId") String channelId,
                                    @Query("order") String order,
                                    @Query("maxResults") String maxResults,
                                    @Query("part") String part,
                                    @Query("key") String key,
                                    @Query("q") String q);
}
