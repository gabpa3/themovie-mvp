package com.gabcode.themovie_mvp.data.remote;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static ApiManager instance;
    private String apiKey;

    private ApiManager(String apiKey) {
        this.apiKey = apiKey;
    }

    public static void init(String apiKey) {
        instance = new ApiManager(apiKey);
    }

    public static ApiManager getInstance() {
        return instance;
    }

    public ApiService getService() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new NetworkInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }

    private class NetworkInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter("api_key", apiKey)
                    .build();

            Request newRequest = request.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .url(url)
                    .build();

            return chain.proceed(newRequest);
        }
    }


}
