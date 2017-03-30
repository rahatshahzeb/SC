package com.shahzeb.sc;

import com.shahzeb.sc.model.Response.SearchImageResponse;

public class MainPresenterImpl implements MainPresenter, FindItemsInteractor.OnFinishedListener {

    private MainView mainView;
    private FindItemsInteractor findItemsInteractor;

    public MainPresenterImpl(MainView mainView, FindItemsInteractor findItemsInteractor) {
        this.mainView = mainView;
        this.findItemsInteractor = findItemsInteractor;
    }

    @Override
    public void onResume(int page, int pageSize, String phrase) {
        if (mainView != null) {
            mainView.showProgress();
        }

        findItemsInteractor.findItems(new NetworkService(), this, page, pageSize, phrase);
    }

    @Override
    public void onItemClicked(int position) {
        if (mainView != null) {
            mainView.showMessage(String.format("Position %d clicked", position + 1));
        }
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onFinished(SearchImageResponse searchImageResponse) {
        if (mainView != null) {
            mainView.setItems(searchImageResponse);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailed(String message) {
        if (mainView != null) {
            mainView.hideProgress();
            mainView.callFailed();
            mainView.showMessage(message);
        }
    }

    public MainView getMainView() {
        return mainView;
    }
}
