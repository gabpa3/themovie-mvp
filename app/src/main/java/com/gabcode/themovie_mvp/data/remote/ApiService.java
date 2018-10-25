package com.gabcode.themovie_mvp.data.remote;

import com.gabcode.themovie_mvp.data.model.Movie;
import com.gabcode.themovie_mvp.data.model.Video;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String BASE_URL = "https://api.themoviedb.org/3/";
    String IMAGE_URL = "https://image.tmdb.org/t/p/";

    @GET(Endpoint.MOVIE)
    Observable<Movie> getMovieDetail(@Path("movie_id") int movieId);

    @GET(Endpoint.MOVIE_VIDEOS)
    Observable<List<Video>> getMovieVideos(@Path("movie_id") int movieId);

    @GET(Endpoint.MOVIE_SIMILAR)
    Observable<ResponseApi<Movie>> getMovieSimilar(@Path("movie_id") int movieId);

    @GET(Endpoint.DISCOVER_MOVIE)
    Flowable<ResponseApi<Movie>> getMoviesByDateRange(
            @Query("primary_release_date.gte") String dateFrom,
            @Query("primary_release_date.lte") String dateTo);

}
