package myway.virtualagent.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;



import java.util.ArrayList;
import java.util.List;

import myway.virtualagent.R;
import myway.virtualagent.adapters.OrderAdapter;
import myway.virtualagent.api.RetrofitClient;
import myway.virtualagent.models.order.OrderList;
import myway.virtualagent.utils.PaginationScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private List<OrderList.OrderResults> results;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private OrderAdapter adapter;
    private View progressBar;
    private static final int OBJECTS_IN_PAGE = 10;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orderlist, container, false);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.recyclervv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        results = new ArrayList<>();
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                currentPage++;

            }
            @Override
            public int getTotalPageCount() {
                return OBJECTS_IN_PAGE;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        progressBar = view.findViewById(R.id.progress);
        getOrderList();

        return view;
    }

    private void getOrderList() {
        Call<OrderList> call = RetrofitClient.getInstance().getApi().getOrder(currentPage);
        call.enqueue(new Callback<OrderList>() {

            @Override
            public void onResponse(@NonNull Call<OrderList> call, @NonNull Response<OrderList> response) {
                isLoading = true;
                swipeRefreshLayout.setRefreshing(false);
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body().getResults() != null) {
                    results = response.body().getResults();
                    adapter = new OrderAdapter(results, OrdersListFragment.this);
                    recyclerView.setAdapter(adapter);
                    adapter.addItems(response.body().getResults());
                    adapter.notifyDataSetChanged();
                    log("onResponse: current page: " + currentPage);

                    isLoading = false;
                }
            }
            @Override
            public void onFailure(@NonNull Call<OrderList> call, @NonNull Throwable t) {

                Log.e("MainActivity", "current page: " + currentPage, t);
                swipeRefreshLayout.setRefreshing(false);
                isLoading = false;
                isLastPage = true;
            }
        });
    }

    private void log(String message) {
        Log.d("MainActivity", message);
    }


    private void onLoadingSwipeRefresh(final String currentPage) {

        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        getOrderList();
                    }
                }
        );
    }
    @Override
    public void onRefresh() {
        getOrderList();
    }
}

