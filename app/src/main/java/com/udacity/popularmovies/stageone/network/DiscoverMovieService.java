package com.udacity.popularmovies.stageone.network;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by kunaljaggi on 2/17/16.
 */
public interface DiscoverMovieService {

    @GET("/3/discover/movie")
    public Call<MovieInfo> getPopularMovies(@Query("sort_by") String sortBy, @Query("api_key") String apiKey);

}
