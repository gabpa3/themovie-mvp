package com.gabcode.themovie_mvp.movie;

import com.gabcode.themovie_mvp.BasePresenter;
import com.gabcode.themovie_mvp.BaseView;
import com.gabcode.themovie_mvp.data.model.Movie;

import java.util.List;

public interface MovieContract {

    interface View extends BaseView {
        void showMoviesData(List<Movie> movies);
        void onItemClick(Movie movie);
        void navigateToDetail(Integer movieId);
    }

    interface Presenter extends BasePresenter {
        void getMoviesDataByRangeDate(boolean refreshed);
    }

}
