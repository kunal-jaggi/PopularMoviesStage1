package com.udacity.popularmovies.stageone.view.impl;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.stageone.R;
import com.udacity.popularmovies.stageone.event.DiscoverMovieEvent;
import com.udacity.popularmovies.stageone.event.MovieEvent;
import com.udacity.popularmovies.stageone.network.DiscoverMovieService;
import com.udacity.popularmovies.stageone.network.Movie;
import com.udacity.popularmovies.stageone.network.MovieInfo;
import com.udacity.popularmovies.stageone.network.impl.DiscoverMovieServiceImpl;
import com.udacity.popularmovies.stageone.singleton.PopularMoviesApplication;


import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * A placeholder fragment containing a simple view.
 */
public class PhotoGalleryFragment extends Fragment {
    @Bind(R.id.moviesGrid)
    GridView moviesGrid;

    DiscoverMovieServiceImpl service;
    List<Movie> popularMovies;

    private static final String TAG = "PhotoGalleryFragment";

    public PhotoGalleryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

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
        PopularMoviesApplication.getEventBus().post(produceDiscoverMovieEvent());
    }

    // @Produce
    public DiscoverMovieEvent produceDiscoverMovieEvent() {
        return new DiscoverMovieEvent("popularity.desc");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        setupAdapter();
        return view;
    }

    @Subscribe
    public void onMovieEvent(MovieEvent movieEvent) {
        popularMovies = movieEvent.getMovies();
        setupAdapter();
    }

    private void setupAdapter() {
        if (getActivity() == null || moviesGrid == null) return;

        if (popularMovies != null) {
            moviesGrid.setAdapter(new GalleryItemAdapter(popularMovies));
        } else {
            moviesGrid.setAdapter(null);
        }
    }


    private class GalleryItemAdapter extends ArrayAdapter<Movie> {

        List<Movie> movies;

        public GalleryItemAdapter(List<Movie> items) {
            super(getActivity(), 0, items);
            movies = items;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.gallery_item, parent, false);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.gallery_item_imageView);
            Picasso.with(getActivity())
                    .load("http://image.tmdb.org/t/p/w185/" + movies.get(position).getPosterPath())
                    .into(imageView);
            return convertView;
        }
    }
}

