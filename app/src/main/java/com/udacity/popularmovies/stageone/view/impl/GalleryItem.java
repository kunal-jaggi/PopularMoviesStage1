package com.udacity.popularmovies.stageone.view.impl;

/**
 * Created by kunaljaggi on 2/17/16.
 */
public class GalleryItem {
    private String mCaption;
    private String mId;
    private String mUrl;

    public String getCaption() {
        return mCaption;
    }
    public void setCaption(String caption) {
        mCaption = caption;
    }
    public String getId() {
        return mId;
    }
    public void setId(String id) {
        mId = id;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String toString() {
        return mCaption;
    }
}
