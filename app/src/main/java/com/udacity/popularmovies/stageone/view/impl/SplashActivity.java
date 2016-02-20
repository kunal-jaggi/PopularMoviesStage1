package com.udacity.popularmovies.stageone.view.impl;

import android.content.Intent;
import android.os.Bundle;

import com.udacity.popularmovies.stageone.presenter.SplashPresenter;
import com.udacity.popularmovies.stageone.view.ISplashView;

/**
 * Created by kunaljaggi on 2/14/16.
 */
public class SplashActivity extends BaseActivity implements ISplashView {

    private SplashPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SplashPresenter(getApplicationContext());
        mPresenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.didFinishLoading();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showNoInetErrorMsg() {

    }

    /**
     * Routes user to the main Activity
     */
    @Override
    public void moveToMainView() {
        Intent mainActivity = new Intent(this, MovieGalleryActivity.class);
        startActivity(mainActivity);
        finish();
    }
}
