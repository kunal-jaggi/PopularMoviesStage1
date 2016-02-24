package com.udacity.popularmovies.stageone.network.model;

/**
 * A POJO that represents Movie object.
 * Created by kunaljaggi on 2/17/16.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Movie implements Parcelable {
    @SerializedName("id") private int mId;
    @SerializedName("adult") private boolean mAdult;
    @SerializedName("overview") private String mOverview;
    @SerializedName("title") private String mTitle;
    @SerializedName("popularity") private float mPopularity;
    @SerializedName("video") private boolean mVideo;
    @SerializedName("poster_path") private String mPosterPath;
    @SerializedName("release_date") private String mReleaseDate;
    @SerializedName("genre_ids") private List<Integer> mGenreIds = new ArrayList<Integer>();
    @SerializedName("original_title") private String mOriginalTitle;
    @SerializedName("original_language") private String mOriginalLanguage;
    @SerializedName("backdrop_path") private String mBackdropPath;
    @SerializedName("vote_count") private int mVoteCount;
    @SerializedName("vote_average") private float mVoteAverage;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public boolean ismAdult() {
        return mAdult;
    }

    public void setmAdult(boolean mAdult) {
        this.mAdult = mAdult;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public float getmPopularity() {
        return mPopularity;
    }

    public void setmPopularity(float mPopularity) {
        this.mPopularity = mPopularity;
    }

    public boolean ismVideo() {
        return mVideo;
    }

    public void setmVideo(boolean mVideo) {
        this.mVideo = mVideo;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public List<Integer> getmGenreIds() {
        return mGenreIds;
    }

    public void setmGenreIds(List<Integer> mGenreIds) {
        this.mGenreIds = mGenreIds;
    }

    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public String getmOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setmOriginalLanguage(String mOriginalLanguage) {
        this.mOriginalLanguage = mOriginalLanguage;
    }

    public String getmBackdropPath() {
        return mBackdropPath;
    }

    public void setmBackdropPath(String mBackdropPath) {
        this.mBackdropPath = mBackdropPath;
    }

    public int getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(int mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    public float getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(float mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeByte((byte) (mAdult ? 1 : 0));
        dest.writeString(mOverview);
        dest.writeString(mTitle);
        dest.writeFloat(mPopularity);
        dest.writeByte((byte) (mVideo ? 1 : 0));
        dest.writeString(mPosterPath);
        dest.writeString(mReleaseDate);
        dest.writeList(mGenreIds);
        dest.writeString(mOriginalTitle);
        dest.writeString(mOriginalLanguage);
        dest.writeString(mBackdropPath);
        dest.writeInt(mVoteCount);
        dest.writeFloat(mVoteAverage);
    }

    public Movie() {
    }

    /**
     * A private constructor that can only be used by the CREATOR field.
     * You have to read the data from the Parcel in the exact same order you wrote it.
     * @param in used to retrieve the values that we originally wrote into the `Parcel`
     */
    private Movie(Parcel in) {
        mId = in.readInt();
        mAdult = in.readByte() != 0;
        mOverview = in.readString();
        mTitle= in.readString();
        mPopularity= in.readFloat();
        mVideo = in.readByte() != 0;
        mPosterPath= in.readString();
        mReleaseDate= in.readString();
        in.readList(mGenreIds,null);
        mOriginalTitle= in.readString();
        mOriginalLanguage= in.readString();
        mBackdropPath= in.readString();
        mVoteCount= in.readInt();
        mVoteAverage= in.readFloat();

    }

    // After implementing the `Parcelable` interface, we need to create the
    // `Parcelable.Creator<MyParcelable> CREATOR` constant for our class;
    // Notice how it has our class specified as its type.
    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {

        // This simply calls our new constructor (typically private) and
        // passes along the unmarshalled `Parcel`, and then returns the new object!
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        // We just need to copy this and change the type to match our class.
        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

}