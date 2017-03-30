package com.shahzeb.sc;

import android.content.res.Resources;

import com.shahzeb.sc.model.Response.SearchImageResponse;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindItemsInteractorImpl implements FindItemsInteractor {

    @Override
    public void findItems(final NetworkService networkService, final OnFinishedListener listener, int page, int page_size, String phrase) {
        networkService
                .getAPI()
                .listImages(NetworkService.API_KEY, page, page_size, phrase)
                .enqueue(new Callback<SearchImageResponse>() {
                    @Override
                    public void onResponse(Call<SearchImageResponse> call, Response<SearchImageResponse> response) {
                        if (response.body() != null && response.code() == HttpURLConnection.HTTP_OK) {
                            listener.onFinished(response.body());
                        } else {
                            listener.onFailed(SCApplication.getContext().getString(R.string.error_network));
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchImageResponse> call, Throwable t) {
                        try {
                            throw  new InterruptedException("Error occurred due to network problem");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        listener.onFailed(SCApplication.getContext().getString(R.string.error_network));
                    }
                });
    }

}
