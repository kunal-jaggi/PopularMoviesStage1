package com.udacity.popularmovies.stageone.view;

import android.os.Bundle;

import com.udacity.popularmovies.stageone.R;

/**
 * This Activity is used to show movie details.
 * Created by kunaljaggi on 2/20/16.
 */
public class DetailsActivity extends BaseActivity {

    public static final String EXTRA_MOVIE = "com.udacity.popularmovies.EXTRA_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detailsContainer, new DetailsFragment())
                    .commit();
        }
    }

}