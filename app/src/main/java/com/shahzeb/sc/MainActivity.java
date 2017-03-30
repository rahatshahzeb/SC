package com.shahzeb.sc;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shahzeb.sc.adapter.ImageListAdapter;
import com.shahzeb.sc.model.Response.Image;
import com.shahzeb.sc.model.Response.SearchImageResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView, SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progress_bar;

    private MainPresenter presenter;

    private int page = 1;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Image> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new MainPresenterImpl(this, new FindItemsInteractorImpl());

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    public void setAdapter() {
        if (adapter == null) {
            adapter = new ImageListAdapter(this, imageList);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    public void resetPage() {
        page = 1;
    }

    public void nextPage() {
        page++;
    }

    public void previousPage() {
        page--;
    }

    public void clearImageList() {
        imageList.clear();
    }

    public void populateDummyContent() {
        for (int i = 0; i < SCConstants.PAGE_SIZE; i++) {
            imageList.add(new Image());
        }
    }

    public void removeDummyContent() {
        int size = imageList.size();
        for (int i = size - 1; i > size - SCConstants.PAGE_SIZE - 1; i--) {
            imageList.remove(imageList.size() - 1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume(page, SCConstants.PAGE_SIZE, "");

        populateDummyContent();
        setAdapter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void showProgress() {
        progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
        progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void setItems(SearchImageResponse searchImageResponse) {
        removeDummyContent();
        imageList.addAll(searchImageResponse.images);
        nextPage();

        setAdapter();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void callFailed() {
        previousPage();

        removeDummyContent();
        setAdapter();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        resetPage();
        clearImageList();

        presenter.onResume(page, SCConstants.PAGE_SIZE, "");

        populateDummyContent();
        setAdapter();
    }
}
