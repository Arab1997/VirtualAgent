package myway.virtualagent.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Pagination
 * Created by Suleiman19 on 10/15/16.
 * Copyright (c) 2016. Suleiman Ali Shakir. All rights reserved.
 */
public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {
    public static final int PAGE_START = 1;
    private LinearLayoutManager layoutManager;
    private static final int PAGE_SIZE = 7;
    /**
     * Supporting only LinearLayoutManager for now.
     *
     * @param layoutManager LinearLayoutManager or its child.
     */
    protected PaginationScrollListener(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = (LinearLayoutManager) layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

      /*  if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= getTotalPageCount()) {
                loadMoreItems();
            }
        }

    }*/


      if (!isLoading() && !isLastPage()) {
        if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                && firstVisibleItemPosition >= 0
                && totalItemCount >= PAGE_SIZE) {
            loadMoreItems();
        }
    }
    }




    protected abstract void loadMoreItems();


    public abstract int getTotalPageCount();


    public abstract boolean isLastPage();


    public abstract boolean isLoading();


}
