package com.udacity.popularmovies.stageone.event;

import com.udacity.popularmovies.stageone.network.Movie;

import java.util.List;

/**
 * Created by kunaljaggi on 2/19/16.
 */
public class MovieEvent {

    List<Movie> movies;

    public MovieEvent() {
    }

    public MovieEvent(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies= movies;
    }
}
