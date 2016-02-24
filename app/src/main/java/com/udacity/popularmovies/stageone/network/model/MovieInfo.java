package com.udacity.popularmovies.stageone.network.model;

/**
 * Created by kunaljaggi on 2/17/16.
 */

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;



public class MovieInfo {

    public int page;

    @SerializedName("results")
    public List<Movie> movieList = new ArrayList<Movie>();

    @SerializedName("total_results")
    public int totalResults;

    @SerializedName("total_pages")
    public int totalPages;

    public MovieInfo() {
    }

    public MovieInfo(int page, List<Movie> movieList, int totalResults, int totalPages) {
        this.page = page;
        this.movieList = movieList;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}