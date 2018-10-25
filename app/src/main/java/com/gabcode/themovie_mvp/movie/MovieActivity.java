package com.gabcode.themovie_mvp.movie;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.gabcode.themovie_mvp.R;
import com.gabcode.themovie_mvp.data.model.Movie;
import com.gabcode.themovie_mvp.databinding.ActivityMovieBinding;
import com.gabcode.themovie_mvp.movieDetail.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity implements MovieContract.View {

    private static final String TAG = MovieActivity.class.getSimpleName();

    private MovieAdapter adapter;
    private ActivityMovieBinding binding;
    private MoviePresenter presenter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie);

        Toolbar toolbar = binding.toolbar;
        toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.ic_isotype));
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        presenter = new MoviePresenter(this);
        binding.setPresenter(presenter);
        presenter.subscribe();

        swipeRefresh = binding.swipeRefresh;
        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.orangeLight), getResources().getColor(R.color.colorAccent));
        swipeRefresh.setOnRefreshListener(() ->
                presenter.getMoviesDataByRangeDate(true));

        setUpRecyclerView(binding.recyclerView);
    }

    private void setUpRecyclerView(RecyclerView recyclerView) {
        adapter = new MovieAdapter(this, new ArrayList<>());
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            @Override
            public int getSpanSize(int i) {
                return i == 0 ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMoviesData(List<Movie> movies) {
        adapter.setMoviesData(movies);
    }

    @Override
    public void onItemClick(Movie movie) {
        navigateToDetail(movie.getId());
    }

    @Override
    public void navigateToDetail(Integer movieId) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.MOVIE_ID_KEY, movieId);
        startActivity(intent);
    }

    @Override
    public void disableRefresh() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unsubscribe();
    }
}
