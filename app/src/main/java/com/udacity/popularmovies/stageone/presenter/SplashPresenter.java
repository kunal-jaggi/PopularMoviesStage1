package com.udacity.popularmovies.stageone.presenter;

import android.content.Context;

import com.udacity.popularmovies.stageone.model.IConnectionStatus;
import com.udacity.popularmovies.stageone.model.impl.ConnectionStatus;
import com.udacity.popularmovies.stageone.view.ISplashView;

public class SplashPresenter {

  private IConnectionStatus mConnectionStatus;
  private ISplashView mView;

  public SplashPresenter() {
  //  this(new ConnectionStatus());
  }

  public SplashPresenter(Context context) {
    mConnectionStatus = new ConnectionStatus(context);
  }

  public void setView(ISplashView view) {
    this.mView = view;
  }

  protected ISplashView getView() {
    return mView;
  }

  public void didFinishLoading() {
    ISplashView view = getView();

    if (mConnectionStatus.isOnline()) {
      view.moveToMainView();
    } else {
      view.hideProgress();
      view.showNoInetErrorMsg();
    }
  }
}