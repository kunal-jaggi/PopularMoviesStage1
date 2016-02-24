package com.udacity.popularmovies.stageone.view;

import android.os.Bundle;

import com.udacity.popularmovies.stageone.R;

/**
 * This is the main launcher Activity for the app. This Activity registers an intent-filter with launcher app.
 * Created by kunaljaggi on 2/14/16.
 */

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MovieGalleryFragment())
                    .commit();
        }
    }

}
