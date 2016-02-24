package com.udacity.popularmovies.stageone.network.model;

/**
 * Created by kunaljaggi on 2/17/16.
 */
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Movie implements Parcelable {
    public int id;
    public boolean adult;
    public String overview;
    public String title;
    public float popularity;
    public boolean video;

    @SerializedName("poster_path")
    public String posterPath;

    @SerializedName("release_date")
    public String releaseDate;

    @SerializedName("genre_ids")
    public List<Integer> genreIds = new ArrayList<Integer>();

    @SerializedName("original_title")
    public String originalTitle;

    @SerializedName("original_language")
    public String originalLanguage;

    @SerializedName("backdrop_path")
    public String backdropPath;

    @SerializedName("vote_count")
    public int voteCount;

    @SerializedName("vote_average")
    public float voteAverage;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(overview);
        dest.writeString(title);
        dest.writeFloat(popularity);
        dest.writeByte((byte) (video ? 1 : 0));
        dest.writeString(posterPath);
        dest.writeString(releaseDate);
        dest.writeList(genreIds);
        dest.writeString(originalTitle);
        dest.writeString(originalLanguage);
        dest.writeString(backdropPath);
        dest.writeInt(voteCount);
        dest.writeFloat(voteAverage);
    }

    public Movie() {
    }

    /**
     * A private constructor that can only be used by the CREATOR field.
     * You have to read the data from the Parcel in the exact same order you wrote it.
     * @param in used to retrieve the values that we originally wrote into the `Parcel`
     */
    private Movie(Parcel in) {
        id = in.readInt();
        adult = in.readByte() != 0;
        overview = in.readString();
        title= in.readString();
        popularity= in.readFloat();
        video = in.readByte() != 0;
        posterPath= in.readString();
        releaseDate= in.readString();
        in.readList(genreIds,null);
        originalTitle= in.readString();
        originalLanguage= in.readString();
        backdropPath= in.readString();
        voteCount= in.readInt();
        voteAverage= in.readFloat();

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