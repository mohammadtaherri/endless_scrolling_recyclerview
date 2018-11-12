package com.mt_app.endlessscrollingrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ScrollObject {

    private Context context;
    private int currentPage;
    private int visibleThreshold;
    private View progressBar;
    private RecyclerView recyclerView;
    private boolean  loading;


    private ScrollObject(Context context, int currentPage, int visibleThreshold, View progressBar, RecyclerView recyclerView) {
        this.context = context;
        this.currentPage = currentPage;
        this.visibleThreshold = visibleThreshold;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;

        recyclerView.setLayoutManager(new CustomLinearLayoutManager(context));
        setLoading(true);
    }

    private void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    private void setLoading(boolean loading) {
        this.loading = loading;
    }

    public Context getContext() {
        return context;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getVisibleThreshold() {
        return visibleThreshold;
    }

    public View getProgressBar() {
        return progressBar;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public boolean isLoading() {
        return loading;
    }


    void startLoading(){
        setLoading(false);
        setCurrentPage(getCurrentPage()+1);
        stopScrollig();
        showProgressbar();
    }

    public void loadSuccess(boolean isLastPage){
        setLoading(!isLastPage);
        runScrolling();
        hideProgressbar();
    }

    public void loadFailed(){
        setLoading(true);
        runScrolling();
        hideProgressbar();
        setCurrentPage(getCurrentPage()-1);
    }

    private RecyclerView.LayoutManager getRecyclerLayoutManager(){
        return recyclerView.getLayoutManager();
    }

    private boolean checkLayoutManager(){
        if (getRecyclerLayoutManager() != null){
            if (getRecyclerLayoutManager() instanceof CustomLinearLayoutManager)
                return true;
            else
                return false;
        }

        return false;
    }

    private void stopScrollig(){
        if (checkLayoutManager())
            ((CustomLinearLayoutManager) getRecyclerLayoutManager()).setScrollEnable(false);
    }

    private void runScrolling(){
        if (checkLayoutManager())
            ((CustomLinearLayoutManager) getRecyclerLayoutManager()).setScrollEnable(true);
    }

    private void showProgressbar(){
        if (progressBar != null)
            progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar(){
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
    }

    public static class Builder{

        private Context context;
        private int currentPage;
        private int visibleThreshold;
        private View progressBar;
        private RecyclerView recyclerView;

        public Builder setContext(Context value){
            this.context = value;
            return this;
        }

        public Builder setCurrentPage(int value){
            this.currentPage = value;
            return this;
        }

        public Builder setVisibleThreshold(int value){
            this.visibleThreshold = value;
            return this;
        }

        public Builder setProgressbar(View view){
            this.progressBar = view;
            return this;
        }

        public Builder setRecyclerView(RecyclerView recyclerView){
            this.recyclerView = recyclerView;
            return this;
        }

        public ScrollObject build(){
            if (context == null)
                throw new NullPointerException("endless_scrolling_recyclerview : contect is null");

            if (recyclerView == null)
                throw new NullPointerException("endless_scrolling_recyclerview : recyclerview is null");

            if (visibleThreshold <= 0)
                throw new IllegalArgumentException("endless_scrolling_recyclerview : visibleThreshold must be gratet than 0");

            return new ScrollObject(context,currentPage,visibleThreshold,progressBar,recyclerView);
        }
    }
}
