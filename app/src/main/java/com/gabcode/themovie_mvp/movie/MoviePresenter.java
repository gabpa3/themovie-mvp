package com.gabcode.themovie_mvp.movie;

import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.gabcode.themovie_mvp.data.model.Movie;
import com.gabcode.themovie_mvp.data.remote.ResponseApi;
import com.gabcode.themovie_mvp.data.remote.repository.MovieDataSource;
import com.gabcode.themovie_mvp.data.remote.repository.MovieRepository;
import com.gabcode.themovie_mvp.util.CommonUtils;


public class MoviePresenter implements MovieContract.Presenter {

    private MovieContract.View view;
    private MovieRepository repository;

    public ObservableBoolean loading;

    public MoviePresenter(@NonNull MovieContract.View view) {
        this.view = view;
        loading = new ObservableBoolean(false);
        repository = new MovieRepository();
    }

    @Override
    public void subscribe() {
        getMoviesDataByRangeDate(false);
    }

    @Override
    public void unsubscribe() {
        view = null;
        repository = null;
    }

    @Override
    public void getMoviesDataByRangeDate(boolean refreshed) {
        if(!refreshed) setLoading(true);
        repository.discoverMoviesByDateRange(CommonUtils.getFirstDateOfYear(), CommonUtils.getCurrentDate(),
                new MovieDataSource.LoadDataResponseCallback<Movie>() {
                    @Override
                    public void onDataLoaded(ResponseApi<Movie> data) {
                        disableLoadingOrRefreshing(refreshed);
                        view.showMoviesData(data.getResults());
                    }

                    @Override
                    public void onDataNotAvailable(String message) {
                        disableLoadingOrRefreshing(refreshed);
                        view.showErrorMessage(message);
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
