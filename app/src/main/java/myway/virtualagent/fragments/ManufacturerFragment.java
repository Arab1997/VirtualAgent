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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import myway.virtualagent.R;
import myway.virtualagent.adapters.MAdapter;
import myway.virtualagent.api.RetrofitClient;
import myway.virtualagent.models.manfacturer.Manufacturer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ManufacturerFragment extends Fragment {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;

    private List<Manufacturer.Results> results;
    private RecyclerView recyclerView;
    private MAdapter adapter;
    private LinearLayout mLinearLayout;
    private View progressBar;
    private static String token;
    ImageView navmenu;

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
        return view;
    }
    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            ManufacturerFragment.this.runOnUiThread(new Runnable() {
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


  /*  // Слайдер
    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPDcLWa5Yj7zncJtMCE54kRUyTEtdGREunqKwPH6-BoutCc7saAw&s"));
        fragments.add(FragmentSlider.newInstance("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkJpEeRocLTtxHhWv31IDDKuFw7ErwmbtgipYtXL7QMZI9o18b&s"));

        fragments.add(FragmentSlider.newInstance("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTsLvFFazNRFmBL95wbuA9QVENDgB3QHbkOjtfhB0S_yqcPXpsfFg&s"));
        fragments.add(FragmentSlider.newInstance("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-KI9N24-1a7kuuLzUf-c5HyUM4p4RjBuFCon-PORPRjxWh-0J&s"));
        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(getContext(), mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }*/
}

