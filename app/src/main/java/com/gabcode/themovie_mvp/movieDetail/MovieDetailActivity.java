package com.gabcode.themovie_mvp.movieDetail;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.widget.Toast;

import com.gabcode.themovie_mvp.R;
import com.gabcode.themovie_mvp.data.model.Movie;
import com.gabcode.themovie_mvp.databinding.ActivityMovieDetailBinding;
import com.gabcode.themovie_mvp.movie.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View {

    public static final String TAG = MovieDetailActivity.class.getSimpleName();
    public static final String MOVIE_ID_KEY = "movieId";

    private ActivityMovieDetailBinding binding;
    private MovieDetailPresenter presenter;

    private SwipeRefreshLayout swipeRefresh;
    private MovieSimilarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Integer movieId = getIntent().getIntExtra(MOVIE_ID_KEY, -1);
        presenter = new MovieDetailPresenter(this, movieId);
        binding.setPresenter(presenter);
        presenter.subscribe();

        setUpRecyclerView(binding.contentScrolling.recyclerView);

        binding.contentScrolling.txOverview.setMovementMethod(new ScrollingMovementMethod());
    }

    private void setUpRecyclerView(RecyclerView recyclerView) {
        adapter = new MovieSimilarAdapter(new ArrayList<>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showMovieDetailData(Movie movie) {
        binding.setMovie(movie);
        setCollapsingTitle(movie.getTitle());
        binding.contentCollapsing.ratingBar.setRating(movie.getRating());
    }

    @Override
    public void showMovieSimilarData(List<Movie> movies) {
        adapter.setMoviesData(movies);
    }

    @Override
    public void disableRefresh() {

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

    private void setCollapsingTitle(String title) {
        binding.collapsingToolbar.setTitle(title);
        binding.collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT);
        binding.collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
