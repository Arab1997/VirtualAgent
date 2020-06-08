package myway.virtualagent.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import myway.virtualagent.R;
import myway.virtualagent.adapters.Adapter;
import myway.virtualagent.adapters.PostAdapter;
import myway.virtualagent.adapters.ProductDetailsAdapter;
import myway.virtualagent.api.RetrofitClient;
import myway.virtualagent.dialogs.OrderSheetDialog2;
import myway.virtualagent.dialogs.ProductDetailsSheetDialog;
import myway.virtualagent.models.order.Post;
import myway.virtualagent.models.products.Products;
import myway.virtualagent.models.products.Results;
import myway.virtualagent.utils.OnClickListener;
import myway.virtualagent.utils.PaginationScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    //   private SwipeRefreshLayout swipeRefreshLayout;


    private List<Results> results;
    private List<Post> posts;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private ProductDetailsAdapter productDetailsAdapter;
   // private BottomSheetAdapter bottomsheetadapter;
    private PostAdapter postAdapter;
    private static String token;
    private View progressBar;

    private LinearLayout mLinearLayout;
    private Button order, orderbtn;
    private EditText producted;
    private TextView back;
    private TextView typeed;
    private EditText quantityed;
    private static final int OBJECTS_IN_PAGE = 10;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 1;
    private LinearLayout bottom_sheet;
    private Results productInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.recycler);
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
                getProduct();
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
        progressBar = view.findViewById(R.id.progress_circular);
        order = (Button) view.findViewById(R.id.order);
        back =  view.findViewById(R.id.back);

        getProduct();


        return view;
    }
    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            HomeFragment.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
    private void runOnUiThread(Runnable runnable) {
    }
    // Get Product
    private void getProduct() {
        isLoading = true;
        Call<Products> call = RetrofitClient.getInstance().getApi().getResults();
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(@NonNull Call<Products> call, @NonNull Response<Products> response) {
                log("onResponse: current page: " + currentPage);
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body().getResults() != null) {
                    results = response.body().getResults();
                    adapter = new Adapter(getContext(), results);
                    isLoading = false;
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    adapter.setListener(new OnClickListener() {
                        @Override
                        public void onClickListener(int i) {
                            productDetailsAdapter = new ProductDetailsAdapter(getContext(), results);
                            //View view = getLayoutInflater ().inflate (R.layout.bottom_sheet, null);
                            new ProductDetailsSheetDialog(getContext(), productDetailsAdapter.getItem(i)).show();// bitta product tanlab undagei malumotni ialog show
                        }
                        @Override
                        public void onOrderClick(int position) {
                            productDetailsAdapter = new ProductDetailsAdapter(getContext(), results);
                          //  View view = getLayoutInflater ().inflate (R.layout.bottom_sheet_layout, null);
                            new OrderSheetDialog2(getContext(), productDetailsAdapter.getItem(position)).show();// bitta product tanlab undagei malumotni ialog show
                            // new MyBottomSheetDialog(getContext(), productDetailsAdapter.getItem(position)).show();// bitta product tanlab undagei malumotni ialog show
                        }
                    });
                }
            }
            @Override
            public void onFailure(@NonNull Call<Products> call, @NonNull Throwable t) {
                Log.e("MainActivity", "Ошибка соединения", t);
                Log.e("MainActivity", "current page: " + currentPage, t);
                isLoading = false;
                isLastPage = true;
            }
        });
    }
    private void log(String message) {
        Log.d("HomeFragment", message);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("");
    }
}







