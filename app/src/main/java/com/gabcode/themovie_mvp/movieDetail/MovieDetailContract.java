package com.gabcode.themovie_mvp.movieDetail;

import com.gabcode.themovie_mvp.BasePresenter;
import com.gabcode.themovie_mvp.BaseView;
import com.gabcode.themovie_mvp.data.model.Movie;

import java.util.List;

public interface MovieDetailContract {

    interface View extends BaseView {
        void showMovieDetailData(Movie movie);
        void showMovieSimilarData(List<Movie> movies);
    }


    interface Presenter extends BasePresenter {
        void getMovieDetailData(boolean refreshed);
        void getMovieSimilarData();
    }
}
