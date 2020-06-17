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

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import myway.virtualagent.R;
import myway.virtualagent.adapters.Adapter;
import myway.virtualagent.adapters.PostAdapter;
import myway.virtualagent.adapters.ProductDetailsAdapter;
import myway.virtualagent.adapters.SliderPagerAdapter;
import myway.virtualagent.api.RetrofitClient;
import myway.virtualagent.dialogs.OrderSheetDialog2;
import myway.virtualagent.dialogs.ProductDetailsSheetDialog;
import myway.virtualagent.models.Slide;
import myway.virtualagent.models.order.Post;
import myway.virtualagent.models.products.Products;
import myway.virtualagent.models.products.Results;
import myway.virtualagent.utils.OnClickListener;
import myway.virtualagent.utils.PaginationScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private List<Results> results;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private ProductDetailsAdapter productDetailsAdapter;
    private PostAdapter postAdapter;
    private static String token;
    private View progressBar;
    private Button order;
    private TextView back;
    private static final int OBJECTS_IN_PAGE = 7;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 1;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private List<Slide> lstSlides;

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
                isLoading = true;
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
        sliderpager = view.findViewById(R.id.slider_pager);
        indicator = view.findViewById(R.id.indicator);

        getProduct();

        initSlider();
        return view;
    }
    private void initSlider() {
        // prepare a list of slides ..
        lstSlides = new ArrayList<>();
        lstSlides.add(new Slide(R.drawable.mobile1, ""));
        lstSlides.add(new Slide(R.drawable.mobile2, ""));
        lstSlides.add(new Slide(R.drawable.mobile3, ""));
        SliderPagerAdapter sliderAdapter = new SliderPagerAdapter(getContext(), lstSlides);
        sliderpager.setAdapter(sliderAdapter);
        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
        indicator.setupWithViewPager(sliderpager, true);
    }
    public class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if(getActivity() == null)
                return;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem() < lstSlides.size() - 1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem() + 1);
                    } else {
                        sliderpager.setCurrentItem(0);
                    }
                }
            });
        }
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
                    isLastPage = false;

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







