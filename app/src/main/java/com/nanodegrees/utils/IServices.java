package com.nanodegrees.utils;

import com.nanodegrees.models.MovieDetailsRequest;
import com.nanodegrees.models.MovieRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IServices {

    @GET(Constants.URI_BASE_MOVIE + Constants.URI_POPULAR)
    Call<MovieRequest> getPopular(@Query("api_key") String key, @Query("language") String lenguaje, @Query("page") Integer page);

    @GET(Constants.URI_BASE_MOVIE + Constants.URI_TOP_RATED)
    Call<MovieRequest> getTopRated(@Query("api_key") String key, @Query("language") String lenguaje, @Query("page") Integer page);

    @GET(Constants.URI_BASE_MOVIE + Constants.URI_UPCOMING)
    Call<MovieRequest> getUpComing(@Query("api_key") String key, @Query("language") String lenguaje, @Query("page") Integer page);

    @GET(Constants.URI_BASE_MOVIE + Constants.URI_MOVIE_DETAILS)
    Call<MovieDetailsRequest> getDetails(@Path("ID") Integer id, @Query("api_key") String key, @Query("language") String lenguaje, @Query("page") Integer page);

}
