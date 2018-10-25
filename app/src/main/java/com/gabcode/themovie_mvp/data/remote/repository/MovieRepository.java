package com.gabcode.themovie_mvp.data.remote.repository;

import com.gabcode.themovie_mvp.data.model.Movie;
import com.gabcode.themovie_mvp.data.model.Video;
import com.gabcode.themovie_mvp.data.remote.ApiManager;
import com.gabcode.themovie_mvp.data.remote.ApiService;
import com.gabcode.themovie_mvp.data.remote.ResponseApi;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class MovieRepository implements MovieDataSource {

    private ApiService apiService;

    public MovieRepository() {
        apiService = ApiManager.getInstance().getService();
    }

    @Override
    public void getMovieDetail(Integer id, LoadDataCallback callback) {
        apiService.getMovieDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscribe<Movie>().generateObserver(callback));
    }

    @Override
    public void getMovieVideos(Integer id, LoadDataListCallback<Video> callback) {
        apiService.getMovieVideos(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscribe<Video>().generateListObserver(callback));
    }

    @Override
    public void getSimilarMovies(Integer id, LoadDataResponseCallback<Movie> callback) {
        apiService.getMovieSimilar(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscribe<Movie>().generateResponseObserver(callback));
    }

    @Override
    public void discoverMoviesByDateRange(String dateFrom, String dateTo, LoadDataResponseCallback<Movie> callback) {
        apiService.getMoviesByDateRange(dateFrom, dateTo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscribe<Movie>().generateResponseFlowable(callback));

    }

    private class BaseSubscribe<T> {

        private DisposableObserver<List<T>> generateListObserver(LoadDataListCallback<T> callback) {
            return new DisposableObserver<List<T>>(){
                @Override
                public void onNext(List<T> ts) { callback.onDataLoaded(ts); }

                @Override
                public void onError(Throwable e) { callback.onDataNotAvailable(e.getMessage()); }

                @Override
                public void onComplete() {}
            };
        }

        private DisposableObserver<ResponseApi<T>> generateResponseObserver(LoadDataResponseCallback<T> callback) {
            return new DisposableObserver<ResponseApi<T>>() {
                @Override
                public void onNext(ResponseApi<T> response) {
                    callback.onDataLoaded(response);
                }

                @Override
                public void onError(Throwable t) { callback.onDataNotAvailable(t.getMessage()); }

                @Override
                public void onComplete() {}
            };
        }

        private ResourceSubscriber<ResponseApi<T>> generateResponseFlowable(LoadDataResponseCallback<T> callback) {
            return new ResourceSubscriber<ResponseApi<T>>() {
                @Override
                public void onNext(ResponseApi<T> response) {
                    callback.onDataLoaded(response);
                }

                @Override
                public void onError(Throwable t) { callback.onDataNotAvailable(t.getMessage()); }

                @Override
                public void onComplete() {}
            };
        }

       private DisposableObserver<T> generateObserver(LoadDataCallback<T> callback){
            return new DisposableObserver<T>() {
                @Override
                public void onNext(T t) { callback.onDataLoaded(t); }

                @Override
                public void onError(Throwable e) { callback.onDataNotAvailable(e.getMessage()); }

                @Override
                public void onComplete() { }
            };
       }

    }

}
