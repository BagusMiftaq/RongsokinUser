package com.edicoding.picodiploma.rongsokinuser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.edicoding.picodiploma.rongsokinuser.fragment.DashboardFragment;
import com.edicoding.picodiploma.rongsokinuser.fragment.HomeFragment;
import com.edicoding.picodiploma.rongsokinuser.fragment.NotivicationFragment;
import com.edicoding.picodiploma.rongsokinuser.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeAct extends AppCompatActivity {

    FloatingActionButton btn_tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_1);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
//        FloatingActionButton btn_tambah = findViewById(R.id.btn_tambah);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectFrag = null;

                    switch (menuItem.getItemId()) {
                        case R.id.navigation_home:
                            selectFrag = new HomeFragment();
                            break;
                        case R.id.navigation_dashboard:
                            selectFrag = new DashboardFragment();
                            break;
                        case R.id.navigation_notifications:
                            selectFrag = new NotivicationFragment();
                            break;

                        case R.id.navigation_profile:
                            selectFrag = new ProfileFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, selectFrag).commit();

                    return true;
                }
            };

}




