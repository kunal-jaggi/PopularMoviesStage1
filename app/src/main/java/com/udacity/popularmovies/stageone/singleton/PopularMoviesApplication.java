package com.udacity.popularmovies.stageone.singleton;

import android.app.Application;

import com.squareup.otto.Bus;

/**
 * A singleton class that represents App's global application state.
 * Created by kunaljaggi on 2/18/16.
 */

public class PopularMoviesApplication extends Application {

    private static Bus mEventBus;

    public static Bus getEventBus() {

        if (mEventBus == null) {
            mEventBus = new com.squareup.otto.Bus();
        }

        return mEventBus;
    }

}
