package com.udacity.popularmovies.stageone.event;

import com.udacity.popularmovies.stageone.network.model.Movie;

import java.util.List;

/**
 * Ths event class is used to represent response returned by discover endpoint of  Open Movie DB API.
 * Created by kunaljaggi on 2/19/16.
 */
public class MovieEvent {

    List<Movie> mMovieList;

    public MovieEvent() {
    }

    public MovieEvent(List<Movie> mMovieList) {
        this.mMovieList = mMovieList;
    }

    public List<Movie> getmMovieList() {
        return mMovieList;
    }

    public void setmMovieList(List<Movie> mMovieList) {
        this.mMovieList = mMovieList;
    }
}
