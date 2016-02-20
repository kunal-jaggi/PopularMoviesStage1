package com.udacity.popularmovies.stageone.view.impl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.udacity.popularmovies.stageone.R;
import com.udacity.popularmovies.stageone.adapter.GalleryItemAdapter;
import com.udacity.popularmovies.stageone.event.DiscoverMovieEvent;
import com.udacity.popularmovies.stageone.event.MovieEvent;
import com.udacity.popularmovies.stageone.network.Movie;
import com.udacity.popularmovies.stageone.network.impl.DiscoverMovieServiceImpl;
import com.udacity.popularmovies.stageone.singleton.PopularMoviesApplication;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by kunaljaggi on 2/20/16.
 */
public class DetailsFragment extends Fragment {

    private static final String TAG = DetailsFragment.class.getName();

    @Bind(R.id.movieTitle)
    TextView movieTile;

    public DetailsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        Intent intent= getActivity().getIntent();
        movieTile.setText(intent.getStringExtra("MOVIE_TITLE"));
        return view;
    }

}
