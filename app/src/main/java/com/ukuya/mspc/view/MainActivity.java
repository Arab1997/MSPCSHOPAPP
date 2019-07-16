package com.ukuya.mspc.view;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.blankj.utilcode.util.Utils;
import com.google.android.material.navigation.NavigationView;
import com.ukuya.mspc.R;
import com.ukuya.mspc.databinding.ActivityMainBinding;
import com.ukuya.mspc.view.fragment.EventFragment;
import com.ukuya.mspc.view.fragment.OtherFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding binding;
    private Fragment containerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        init();

    }

    private void init() {

        Utils.init(MainActivity.this);
        setSupportActionBar(binding.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.toolbar, R.string.app_name, R.string.app_name);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setDrawerSlideAnimationEnabled(true);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_dehaze_black_24dp, getTheme());
        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    binding.drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        binding.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                                                   @Override
                                                   public void onDrawerSlide(View drawerView, float slideOffset) {

                                                       // Scale the View based on current slide offset
                                                       final float diffScaledOffset = slideOffset * (1 - 0.7f);
                                                       final float offsetScale = 1 - diffScaledOffset;
                                                       binding.contentView.setScaleX(offsetScale);
//                                         contentView.setScaleY(offsetScale);

                                                       // Translate the View, accounting for the scaled width
                                                       final float xOffset = drawerView.getWidth() * slideOffset;
                                                       final float xOffsetDiff = binding.contentView.getWidth() * diffScaledOffset / 2;
                                                       final float xTranslation = xOffset - xOffsetDiff;
                                                       binding.contentView.setTranslationX(xTranslation);
                                                   }

                                                   @Override
                                                   public void onDrawerClosed(View drawerView) {
                                                   }
                                               }
        );

        binding.navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        containerFragment = new EventFragment();
        ft.replace(R.id.contentView, containerFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            } else {
                super.onBackPressed();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (id == R.id.navEvents) {
            if (!(containerFragment instanceof EventFragment)) {
                clearFragmentStack();
                containerFragment = new EventFragment();
                ft.replace(R.id.contentView, containerFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        } else {
            if (!(containerFragment instanceof OtherFragment)) {
                clearFragmentStack();
                containerFragment = new OtherFragment();
                ft.replace(R.id.contentView, containerFragment);
                ft.addToBackStack(null);
                ft.commit();
            }

        }

        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void clearFragmentStack() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }
}
