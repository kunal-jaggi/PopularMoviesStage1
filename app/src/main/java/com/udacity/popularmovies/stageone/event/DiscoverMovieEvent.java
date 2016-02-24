package com.udacity.popularmovies.stageone.event;

/**
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
