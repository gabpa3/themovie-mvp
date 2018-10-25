package com.gabcode.themovie_mvp.data.remote.repository;

import com.gabcode.themovie_mvp.data.remote.ResponseApi;

import java.util.List;

public interface BaseDataSource {

    interface LoadDataListCallback<T> {
        void onDataLoaded(List<T> data);
        void onDataNotAvailable(String message);
    }

    interface LoadDataResponseCallback<T> {
        void onDataLoaded(ResponseApi<T> data);
        void onDataNotAvailable(String message);
    }

    interface LoadDataCallback<T> {
        void onDataLoaded(T data);
        void onDataNotAvailable(String message);
    }

}
