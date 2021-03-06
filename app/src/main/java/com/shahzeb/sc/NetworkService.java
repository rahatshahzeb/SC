package com.shahzeb.sc;

import com.shahzeb.sc.model.Response.SearchImageResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public class NetworkService {

    private static String BASE_URL = "https://api.gettyimages.com:443/v3/";
    public final static String API_KEY = "tdbcme3b79r59gdsa8mktgch"; //cp2dhxm3jtcv4tqvtunttqza

    public interface SCNetworkAPI {

        @GET("search/images")
        Call<SearchImageResponse> listImages(
                @Header("Api-Key") String apiKey,
                @Query("page") int page,
                @Query("page_size") int pageSize,
                @Query("phrase") String phrase );
    }

    public SCNetworkAPI getAPI(){
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(SCNetworkAPI.class);
    }
}

