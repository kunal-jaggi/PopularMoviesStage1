package com.udacity.popularmovies.stageone.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.squareup.otto.Subscribe;
import com.udacity.popularmovies.stageone.R;
import com.udacity.popularmovies.stageone.adapter.GalleryItemAdapter;
import com.udacity.popularmovies.stageone.event.DiscoverMovieEvent;
import com.udacity.popularmovies.stageone.event.MovieEvent;
import com.udacity.popularmovies.stageone.network.model.Movie;
import com.udacity.popularmovies.stageone.network.service.DiscoverMovieServiceImpl;
import com.udacity.popularmovies.stageone.singleton.PopularMoviesApplication;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MovieGalleryFragment extends Fragment {

    private static final String LOG_TAG = MovieGalleryFragment.class.getSimpleName();

    @Bind(R.id.moviesGrid) GridView mMovieGrid;
    private DiscoverMovieServiceImpl mMovieService;
    private List<Movie> mMovieList;

    public MovieGalleryFragment() {
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
        mMovieService = new DiscoverMovieServiceImpl();
        PopularMoviesApplication.getEventBus().register(this);
        fetchMovies();
    }

    /**
     * Used to fire an event to the Bus that will fetch movie list from Open Movie DB REST back-end.
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

    }

    public DiscoverMovieEvent produceDiscoverMovieEvent(String queryParam) {
        return new DiscoverMovieEvent(queryParam);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moviegallery, container, false);
        ButterKnife.bind(this, view);
        setupAdapter();
        return view;
    }

    /**
     * Used to navigate to Details screen through explicit intent.
     *
     * @param position grid item position clicked by the user.
     */
    @OnItemClick(R.id.moviesGrid)
    void onItemClick(int position) {
        Movie selectedMovie = mMovieList.get(position);
        Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_MOVIE, selectedMovie);
        startActivity(intent);
    }

    @Subscribe
    public void onMovieEvent(MovieEvent movieEvent) {
        mMovieList = movieEvent.getmMovieList();
        setupAdapter();
    }

    private void setupAdapter() {
        if (getActivity() == null || mMovieGrid == null) return;

        if (mMovieList != null) {
            mMovieGrid.setAdapter(new GalleryItemAdapter(getContext(), mMovieList));
        } else {
            mMovieGrid.setAdapter(null);
        }
    }
}

