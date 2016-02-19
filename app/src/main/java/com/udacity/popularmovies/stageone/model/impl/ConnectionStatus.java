package com.udacity.popularmovies.stageone.model.impl;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.udacity.popularmovies.stageone.model.IConnectionStatus;

public class ConnectionStatus implements IConnectionStatus {

    private Context context;

    public ConnectionStatus() {
    }

    public ConnectionStatus(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Check if there is a NetworkConnection
     *
     * @return boolean
     */
    @Override
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }

    }

}