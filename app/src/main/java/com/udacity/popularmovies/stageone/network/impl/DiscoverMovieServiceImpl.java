package com.udacity.popularmovies.stageone.network.impl;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import com.udacity.popularmovies.stageone.event.DiscoverMovieEvent;
import com.udacity.popularmovies.stageone.event.MovieEvent;
import com.udacity.popularmovies.stageone.network.DiscoverMovieService;
import com.udacity.popularmovies.stageone.network.Movie;
import com.udacity.popularmovies.stageone.network.MovieInfo;
import com.udacity.popularmovies.stageone.singleton.PopularMoviesApplication;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by kunaljaggi on 2/18/16.
 */
public class DiscoverMovieServiceImpl {

    private Bus eventBus;
    List<Movie> popularMovies;
    private final String baseUrl = "https://api.themoviedb.org";
    private final String apiKey = "YOUR_API_KEY";

    private static final String LOG_TAG = DiscoverMovieServiceImpl.class.getSimpleName();

    public DiscoverMovieServiceImpl(Bus eventBus) {
        this.eventBus = eventBus;
        eventBus.register(this);
    }

    public DiscoverMovieServiceImpl() {
        PopularMoviesApplication.getEventBus().register(this);
    }

    /**
     * Used to make a async call to movies DB to fetch a list of popular movies.
     * @param event
     */
    @Subscribe
    public void onDiscoverMovieEvent(DiscoverMovieEvent event) {

        Retrofit client = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DiscoverMovieService api = client.create(DiscoverMovieService.class);

        Call<MovieInfo> restCall = api.getPopularMovies(event.getSortBy(), apiKey);

        restCall.enqueue(new Callback<MovieInfo>() {
            @Override
            public void onResponse(Response<MovieInfo> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    MovieInfo movieInfo = response.body();
                    popularMovies = movieInfo.getMovieList();
                    PopularMoviesApplication.getEventBus().post(produceMovieEvent());
                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                    Log.d(LOG_TAG, "Web call error");
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    //@Produce
    public MovieEvent produceMovieEvent() {
        return new MovieEvent(popularMovies);
    }
}
