package myway.virtualagent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import myway.virtualagent.fragments.HomeFragment;
import myway.virtualagent.fragments.ManufacturerFragment;
import myway.virtualagent.fragments.OrdersListFragment;
import myway.virtualagent.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity
        implements
        SwipeRefreshLayout.OnRefreshListener{
    TextView textMessage;
    BottomNavigationView bottomNavigationView;
    Fragment selectFragment = null;
    ImageView navmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectFragment = new HomeFragment();
                            break;
                        case R.id.nav_categories:
                           selectFragment = new OrdersListFragment();
                            break;
                        case R.id.nav_shortlist:
                          selectFragment = new ManufacturerFragment();
                            break;
                        case R.id.nav_profile:
                           selectFragment = new ProfileFragment();
                            break;
                    }

                    if (selectFragment != null) {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                selectFragment).commit();

                    }
                    return true;
                }
            };

  /*  @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
     *//*   if (id == R.id.nav_order) {
        } else if (id == R.id.nav_bonus) {
        } else if (id == R.id.nav_place) {
        } else if (id == R.id.nav_settingss) {
        }*//*
       // DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }*/
    @Override
    public void onRefresh() {

    }


}
