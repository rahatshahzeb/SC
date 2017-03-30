package com.shahzeb.sc;

public interface MainPresenter {

    void onResume(int page, int pageSize, String phrase);

    void onItemClicked(int position);

    void onDestroy();
}
