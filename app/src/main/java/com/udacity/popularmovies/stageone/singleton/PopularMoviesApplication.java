package com.udacity.popularmovies.stageone.singleton;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * Created by kunaljaggi on 2/18/16.
 */

public class PopularMoviesApplication extends Application {

    private static Bus eventBus;

    public static Bus getEventBus() {
        if(eventBus==null) {
            eventBus = new com.squareup.otto.Bus();

        }
        return eventBus;
    }

}
