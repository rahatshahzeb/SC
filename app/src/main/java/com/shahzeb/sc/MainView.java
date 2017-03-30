package com.shahzeb.sc;

import com.shahzeb.sc.model.Response.SearchImageResponse;


public interface MainView {

    void showProgress();

    void hideProgress();

    void setItems(SearchImageResponse searchImageResponse);

    void showMessage(String message);

    void callFailed();
}
