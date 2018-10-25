package com.gabcode.themovie_mvp;

import android.app.Application;

import com.gabcode.themovie_mvp.data.remote.ApiManager;

public class ThemovieApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ApiManager.init(getString(R.string.api_key));
    }
}
