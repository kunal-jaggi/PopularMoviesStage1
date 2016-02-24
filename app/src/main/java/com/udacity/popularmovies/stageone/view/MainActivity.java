package com.udacity.popularmovies.stageone.view;

import android.os.Bundle;

import com.udacity.popularmovies.stageone.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add Fragment in code
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PhotoGalleryFragment())
                    .commit();
        }
    }

}
