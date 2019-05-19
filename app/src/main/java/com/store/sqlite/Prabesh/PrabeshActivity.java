package com.store.sqlite.Prabesh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.store.sqlite.R;

public class PrabeshActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prabesh);

        if (findViewById(R.id.frame_layout) != null) {
            if (savedInstanceState != null) {
                return;
            }

            HomeFragment homeFragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, homeFragment).commit();
        }
    }



}
