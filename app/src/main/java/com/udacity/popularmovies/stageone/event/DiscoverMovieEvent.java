package com.udacity.popularmovies.stageone.event;

/**
 * This event class is used to represent an event which is triggered to fetch movies from Open Movie DB API.
 * Created by kunaljaggi on 2/18/16.
 */
public class DiscoverMovieEvent {
    private String mSortBy;

    public DiscoverMovieEvent(String sortBy) {
        mSortBy= sortBy;
    }

    public DiscoverMovieEvent() {
    }

    public String getmSortBy() {
        return mSortBy;
    }

    public void setmSortBy(String mSortBy) {
        this.mSortBy = mSortBy;
    }
}
