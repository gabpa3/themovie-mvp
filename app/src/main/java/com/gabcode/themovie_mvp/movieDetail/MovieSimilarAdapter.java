package com.gabcode.themovie_mvp.movieDetail;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabcode.themovie_mvp.R;
import com.gabcode.themovie_mvp.data.model.Movie;
import com.gabcode.themovie_mvp.databinding.ViewholderMovieSimilarBinding;

import java.util.List;

public class MovieSimilarAdapter extends RecyclerView.Adapter<MovieSimilarAdapter.MovieHolder> {

    private List<Movie> movies;

    public MovieSimilarAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    public void setMoviesData(List<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        ViewholderMovieSimilarBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.viewholder_movie_similar, parent,false);
        return new MovieHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder  {
        private ViewholderMovieSimilarBinding binding;

        public MovieHolder(ViewholderMovieSimilarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Movie movie) {
            binding.setData(movie);
        }
    }
}

