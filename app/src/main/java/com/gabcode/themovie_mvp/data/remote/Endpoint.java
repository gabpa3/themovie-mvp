package com.gabcode.themovie_mvp.data.remote;

public class Endpoint {

    public static final String MOVIE = "movie/{movie_id}";
    public static final String MOVIE_VIDEOS = "movie/{movie_id}/videos";
    public static final String MOVIE_SIMILAR = "movie/{movie_id}/similar";
    public static final String DISCOVER_MOVIE = "discover/movie?sort_by=popularity.desc";
//    public static final String DISCOVER_MOVIE = "discover/movie?sort_by=release_date.desc";
}
