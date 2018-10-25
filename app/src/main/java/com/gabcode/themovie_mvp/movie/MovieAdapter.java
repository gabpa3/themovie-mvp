package com.gabcode.themovie_mvp.movie;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabcode.themovie_mvp.R;
import com.gabcode.themovie_mvp.data.model.Movie;
import com.gabcode.themovie_mvp.databinding.ViewholderMovieBinding;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
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
        ViewholderMovieBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.viewholder_movie, parent,false);
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

    public class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ViewholderMovieBinding binding;

        public MovieHolder(ViewholderMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.cardMovie.setOnClickListener(this);
        }

        void bind(Movie movie) {
            binding.setData(movie);
            binding.cardMovie.setTag(movie);
        }

        @Override
        public void onClick(View v) {
            Movie movie = (Movie) v.getTag();
            ((MovieActivity) context).onItemClick(movie);
        }
    }
}
