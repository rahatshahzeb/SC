package com.shahzeb.sc.activity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.shahzeb.sc.BaseActivity;
import com.shahzeb.sc.FindItemsInteractorImpl;
import com.shahzeb.sc.MainPresenter;
import com.shahzeb.sc.MainPresenterImpl;
import com.shahzeb.sc.MainView;
import com.shahzeb.sc.R;
import com.shahzeb.sc.SCConstants;
import com.shahzeb.sc.adapter.ImageListAdapter;
import com.shahzeb.sc.model.Response.Image;
import com.shahzeb.sc.model.Response.SearchImageResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainView,
        SwipeRefreshLayout.OnRefreshListener,
        SearchView.OnQueryTextListener,
        ImageListAdapter.ImageListAdapterListener{

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private SearchView searchView;
    private MenuItem menuItem;

    private MainPresenter presenter;
    private String phrase = null;
    private int page = 1;
    private boolean isLoading = true;
    private int pastVisiblesItems;
    private int visibleItemCount;
    private int totalItemCount;

    private RecyclerView.OnScrollListener recyclerViewScrollListener;

    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    private List<Image> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        presenter = new MainPresenterImpl(this, new FindItemsInteractorImpl());

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        setScrollListener();

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.addOnScrollListener(recyclerViewScrollListener);
    }

    public void setScrollListener() {
        recyclerViewScrollListener = new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                    if (isLoading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            isLoading = false;

                            presenter.onResume(page, SCConstants.PAGE_SIZE, phrase);

                            populateDummyContent();
                            setAdapter();
                        }
                    }
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        menuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        return true;
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

    public void clearImageList() {
        imageList.clear();
    }

    public void populateDummyContent() {
        for (int i = 0; i < SCConstants.PAGE_SIZE; i++) {
            imageList.add(new Image());
        }
    }

    public void removeDummyContent() {
        if (imageList.size() > 0) {
            for (int i = 0; i < SCConstants.PAGE_SIZE; i++) {
                imageList.remove(imageList.size() - 1);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume(page, SCConstants.PAGE_SIZE, phrase);

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
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setItems(SearchImageResponse searchImageResponse) {
        isLoading = true;
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
        isLoading = true;

        removeDummyContent();
        setAdapter();
    }

    @Override
    public void onRefresh() {
        isLoading = true;
        swipeRefreshLayout.setRefreshing(true);
        resetPage();
        clearImageList();

        presenter.onResume(page, SCConstants.PAGE_SIZE, phrase);

        populateDummyContent();
        setAdapter();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        menuItem.collapseActionView();
        isLoading = true;
        resetPage();
        clearImageList();

        phrase = query;
        presenter.onResume(page, SCConstants.PAGE_SIZE, phrase);

        populateDummyContent();
        setAdapter();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onItemClick(ImageView imageView, Image image) {
        navigateDetailActivity(imageView, image);
    }

    public void navigateDetailActivity(ImageView imageView, Image image) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(SCConstants.KEY_IMAGE, image);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, imageView, "ImageDetail");
        startActivity(intent, options.toBundle());
    }
}
