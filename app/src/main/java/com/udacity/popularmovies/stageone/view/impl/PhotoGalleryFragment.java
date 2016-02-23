package com.udacity.popularmovies.stageone.view.impl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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
 * A placeholder fragment containing a simple view.
 */
public class PhotoGalleryFragment extends Fragment {
    @Bind(R.id.moviesGrid)
    GridView moviesGrid;

    DiscoverMovieServiceImpl service;

    List<Movie> popularMovies;

    private static final String LOG_TAG = PhotoGalleryFragment.class.getSimpleName();

    public PhotoGalleryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true); // fragment should handle menu events
    }

    //TODO in chapter 3 data from BO is retrieved inside onStart callback
    @Override
    public void onPause() {
        super.onPause();
        PopularMoviesApplication.getEventBus().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        service = new DiscoverMovieServiceImpl();
        PopularMoviesApplication.getEventBus().register(this);
        fetchMovies();

    }

    /**
     * Used to fetch movie list from Open Movie DB REST back-end.
     * The sort order is retrieved from Shared Preferences
     */
    private void fetchMovies() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String sortBy = prefs.getString(getString(R.string.pref_sort_key),
                getString(R.string.pref_sort_order_default));
        PopularMoviesApplication.getEventBus().post(produceDiscoverMovieEvent(sortBy));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.galleryfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            fetchMovies();
            return true;
        }
        return super.onOptionsItemSelected(item);


//        switch (item.getItemId()) {
//            case R.id.action_sortby_popularity:
//                PopularMoviesApplication.getEventBus().post(produceDiscoverMovieEvent("popularity.desc"));
//                return true;
//
//            case R.id.action_sortby_ratings:
//                PopularMoviesApplication.getEventBus().post(produceDiscoverMovieEvent("vote_average.desc"));
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
    }

    // @Produce
    public DiscoverMovieEvent produceDiscoverMovieEvent(String queryParam) {
        return new DiscoverMovieEvent(queryParam);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        setupAdapter();
        return view;
    }

    /**
     * Used to navigate to Details screen through explicit intent.
     * @param position
     */
    @OnItemClick(R.id.moviesGrid)
    void onItemClick(int position) {
        Movie selectedMovie= this.popularMovies.get(position);
        Intent intent= new Intent(getContext(), MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE, selectedMovie);
        startActivity(intent);
    }

    @Subscribe
    public void onMovieEvent(MovieEvent movieEvent) {
        popularMovies = movieEvent.getMovies();
        setupAdapter();
    }

    private void setupAdapter() {
        if (getActivity() == null || moviesGrid == null) return;

        if (popularMovies != null) {
            moviesGrid.setAdapter(new GalleryItemAdapter(getContext(), popularMovies));
        } else {
            moviesGrid.setAdapter(null);
        }
    }
}

