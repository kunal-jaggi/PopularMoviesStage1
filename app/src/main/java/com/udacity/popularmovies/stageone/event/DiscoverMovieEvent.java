package com.udacity.popularmovies.stageone.event;

/**
 * Created by kunaljaggi on 2/18/16.
 */
public class DiscoverMovieEvent {
    private String sortBy;

    public DiscoverMovieEvent(String sortBy) {
        this.sortBy = sortBy;
    }

    public DiscoverMovieEvent() {
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
