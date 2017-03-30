package com.shahzeb.sc;

import com.shahzeb.sc.model.Response.SearchImageResponse;

public interface FindItemsInteractor {

    interface OnFinishedListener {
        void onFinished(SearchImageResponse searchImageResponse);
        void onFailed(String message);
    }

    void findItems(NetworkService networkService, OnFinishedListener listener, int page, int page_size, String phrase);
}
