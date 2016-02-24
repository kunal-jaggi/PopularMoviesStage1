package com.udacity.popularmovies.stageone.network.service;

import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.udacity.popularmovies.stageone.event.DiscoverMovieEvent;
import com.udacity.popularmovies.stageone.event.MovieEvent;
import com.udacity.popularmovies.stageone.network.model.Movie;
import com.udacity.popularmovies.stageone.network.model.MovieInfo;
import com.udacity.popularmovies.stageone.singleton.PopularMoviesApplication;
import com.udacity.popularmovies.stageone.util.Constants;

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

    private Bus mEventBus;
    private List<Movie> mMovieList;

    private static final String LOG_TAG = DiscoverMovieServiceImpl.class.getSimpleName();

    public DiscoverMovieServiceImpl(Bus eventBus) {
        mEventBus = eventBus;
        eventBus.register(this);
    }

    public DiscoverMovieServiceImpl() {
        PopularMoviesApplication.getEventBus().register(this);
    }

    /**
     * Used to make a async call to movies DB to fetch a list of popular movies.
     *
     * @param event
     */
    @Subscribe
    public void onDiscoverMovieEvent(DiscoverMovieEvent event) {

        Retrofit client = new Retrofit.Builder()
                .baseUrl(Constants.MOVIE_DB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DiscoverMovieService api = client.create(DiscoverMovieService.class);

        Call<MovieInfo> restCall = api.getPopularMovies(event.getmSortBy(), Constants.MOVIE_DB_API_KEY);

        restCall.enqueue(new Callback<MovieInfo>() {
            @Override
            public void onResponse(Response<MovieInfo> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    // request successful (status code 200, 201)
                    MovieInfo movieInfo = response.body();
                    mMovieList = movieInfo.getmMovieList();
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

    public MovieEvent produceMovieEvent() {
        return new MovieEvent(mMovieList);
    }
}
