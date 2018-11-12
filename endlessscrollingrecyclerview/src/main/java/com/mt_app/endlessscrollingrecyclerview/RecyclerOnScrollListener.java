package com.mt_app.endlessscrollingrecyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public abstract class RecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private int totalItem;
    private int lastVisibleItem;
    private View progressbar;
    private RecyclerView.LayoutManager layoutManager;

    private ScrollObject object;

    public RecyclerOnScrollListener(ScrollObject object) {
        this.object = object;

        layoutManager = object.getRecyclerView().getLayoutManager();
        progressbar = object.getProgressBar();

    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        totalItem = layoutManager.getItemCount();
        lastVisibleItem = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

        if (object.isLoading() && totalItem <= (object.getVisibleThreshold() + lastVisibleItem)){
            onLoadMore(object.getCurrentPage() , totalItem);
            object.startLoading();
        }
    }


    protected abstract void onLoadMore(int currentPage , int totalItem);


}
