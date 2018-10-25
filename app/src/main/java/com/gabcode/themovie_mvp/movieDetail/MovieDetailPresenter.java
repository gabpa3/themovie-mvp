package com.gabcode.themovie_mvp.movieDetail;

import android.databinding.ObservableBoolean;

import com.gabcode.themovie_mvp.data.model.Movie;
import com.gabcode.themovie_mvp.data.remote.ResponseApi;
import com.gabcode.themovie_mvp.data.remote.repository.MovieDataSource;
import com.gabcode.themovie_mvp.data.remote.repository.MovieRepository;

import java.util.List;

public class MovieDetailPresenter implements MovieDetailContract.Presenter{

    private MovieDetailContract.View view;
    private MovieRepository repository;
    private Integer movieId;

    public ObservableBoolean loading;

    public MovieDetailPresenter(MovieDetailContract.View view, Integer movieId) {
        this.view = view;
        this.movieId = movieId;
        repository = new MovieRepository();
        loading = new ObservableBoolean(false);
    }

    @Override
    public void subscribe() {
        getMovieDetailData(false);
        getMovieSimilarData();
    }

    @Override
    public void unsubscribe() {
        view = null;
        repository = null;
    }


    @Override
    public void getMovieDetailData(boolean refreshed) {
        if(!refreshed) setLoading(true);
        repository.getMovieDetail(movieId, new MovieDataSource.LoadDataCallback<Movie>(){
            @Override
            public void onDataLoaded(Movie data) {
                disableLoadingOrRefreshing(refreshed);
                view.showMovieDetailData(data);
            }

            @Override
            public void onDataNotAvailable(String message) {
                disableLoadingOrRefreshing(refreshed);
                view.showErrorMessage(message);
            }
        });
    }

    @Override
    public void getMovieSimilarData() {
        repository.getSimilarMovies(movieId, new MovieDataSource.LoadDataResponseCallback<Movie>() {
            @Override
            public void onDataLoaded(ResponseApi<Movie> data) {
                view.showMovieSimilarData(data.getResults());
            }

            @Override
            public void onDataNotAvailable(String message) {

            }
        });
    }

    private void disableLoadingOrRefreshing(boolean refreshed) {
        if(!refreshed) setLoading(false);
        else view.disableRefresh();
    }

    public void setLoading(boolean value) {
        this.loading.set(value);
    }


}
