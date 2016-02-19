package com.udacity.popularmovies.stageone.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.udacity.popularmovies.stageone.R;
import com.udacity.popularmovies.stageone.adapter.MoviesAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieGalleryActivity extends BaseActivity {

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
