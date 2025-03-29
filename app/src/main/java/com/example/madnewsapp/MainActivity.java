package com.example.madnewsapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageButton buttonDrawerToggle;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView);
        bottomNavView = findViewById(R.id.bottomNavView);

        if (savedInstanceState == null) {
            loadFragment(new NewsFragment());
            bottomNavView.setSelectedItemId(R.id.nav_news);
            navigationView.setCheckedItem(R.id.navhome);
        }

        buttonDrawerToggle.setOnClickListener(view -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.navhome) {
                selectedFragment = new NewsFragment();
                bottomNavView.setSelectedItemId(R.id.nav_news);
            } else if (item.getItemId() == R.id.navnotification) {
                selectedFragment = new NotificationsFragment();
            } else if (item.getItemId() == R.id.navsettings) {
                selectedFragment = new SettingsFragment();
            } else if (item.getItemId() == R.id.navshare) {
                selectedFragment = new ShareFragment();
            } else if (item.getItemId() == R.id.navaboutus) {
                selectedFragment = new AboutUsFragment();
            } else if (item.getItemId() == R.id.navcontactus) {
                selectedFragment = new ContactUsFragment();
            } else if (item.getItemId() == R.id.navfeedback) {
                selectedFragment = new FeedbackFragment();
            } else if (item.getItemId() == R.id.nav_view_feedback) {
                selectedFragment = new ViewFeedbackFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        bottomNavView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            if (item.getItemId() == R.id.nav_news) {
                selectedFragment = new NewsFragment();
                navigationView.setCheckedItem(R.id.navhome);
            } else if (item.getItemId() == R.id.nav_papers) {
                selectedFragment = new PaperFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .addToBackStack(null)
                .commit();
    }
}