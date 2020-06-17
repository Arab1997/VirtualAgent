package myway.virtualagent.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import myway.virtualagent.R;
import myway.virtualagent.adapters.MAdapter;
import myway.virtualagent.adapters.SliderPagerAdapter;
import myway.virtualagent.api.RetrofitClient;
import myway.virtualagent.models.Slide;
import myway.virtualagent.models.manfacturer.Manufacturer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManufacturerFragment extends Fragment {
    private List<Manufacturer.ManfacturerResults> results;
    private RecyclerView recyclerView;
    private MAdapter adapter;
    private View progressBar;
    private static String token;

    private ViewPager sliderpager;
    private TabLayout indicator;
    private List<Slide> lstSlides;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manufacturer, container, false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView = view.findViewById(R.id.recyclerv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        results = new ArrayList<>();
        recyclerView.setAdapter(adapter);
        progressBar = view.findViewById(R.id.progress_barr);
        sliderpager = view.findViewById(R.id.slider_pager);
        indicator = view.findViewById(R.id.indicator);


        SharedPreferences preferences = getContext().getSharedPreferences("my_shared_preff", Context.MODE_PRIVATE);
        token = preferences.getString("auth_token", "");

        // Manufacturer  get Запрос
        Call<Manufacturer> call = RetrofitClient.getInstance().getApi().getResult();
        call.enqueue(new Callback<Manufacturer>() {

            @Override
            public void onResponse(@NonNull Call<Manufacturer> call, @NonNull Response<Manufacturer> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body().getResults() != null) {
                    results = response.body().getResults();
                    adapter = new MAdapter(results, ManufacturerFragment.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    //swipeRefreshLayout.setRefreshing(false);
                }
            }
            @Override
            public void onFailure(@NonNull Call<Manufacturer> call, @NonNull Throwable t) {
                Log.e("", " ", t);
                // swipeRefreshLayout.setRefreshing(false);
            }
        });
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

}

