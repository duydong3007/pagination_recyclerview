package com.example.pagination_recyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class paginationScrollListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager linearLayoutManager;

    public paginationScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemcount=linearLayoutManager.getItemCount();
        int totalItemcount=linearLayoutManager.getItemCount();
        int firstVisibleItemposition=linearLayoutManager.findFirstVisibleItemPosition();

        if(isloading()||islastpage()){
            return;
        }
        if(firstVisibleItemposition>=0&&(visibleItemcount+firstVisibleItemposition)>=totalItemcount){
         loadmoreItem();
        }

    }
    public abstract void loadmoreItem();
    public abstract boolean isloading();
    public abstract boolean islastpage();

}
