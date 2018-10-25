package com.gabcode.themovie_mvp.data.remote.repository;

import com.gabcode.themovie_mvp.data.model.Movie;
import com.gabcode.themovie_mvp.data.model.Video;

public interface MovieDataSource extends BaseDataSource {

    void getMovieDetail(Integer id, LoadDataCallback<Movie> callback);
    void getMovieVideos(Integer id, LoadDataListCallback<Video> callback);
    void getSimilarMovies(Integer id, LoadDataResponseCallback<Movie> callback);
    void discoverMoviesByDateRange(String dateFrom, String dateTo, LoadDataResponseCallback<Movie> callback);

}
