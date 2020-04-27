package www.nabil.keenam.view.fragment;


import android.graphics.Movie;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.nabil.keenam.R;
import www.nabil.keenam.adapter.MovieDiscoverAdapter;
import www.nabil.keenam.model.movie.MovieDiscoverResultsItem;
import www.nabil.keenam.view.viewmodel.MovieViewModel;

public class MovieFragment extends Fragment {

    private MovieDiscoverAdapter movieDiscoverAdapter;
    private RecyclerView rvMovieDiscover;
    private MovieViewModel movieViewModel;


    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieDiscoverAdapter = new MovieDiscoverAdapter(getContext());
        movieDiscoverAdapter.notifyDataSetChanged();

        rvMovieDiscover = view.findViewById(R.id.fragmentmovie_rv);
        rvMovieDiscover.setLayoutManager(new GridLayoutManager(getContext(),2));

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.setMovieDiscover();
        movieViewModel.getMoviesDiscover().observe(this,getMovieDiscover);

        rvMovieDiscover.setAdapter(movieDiscoverAdapter);
    }

    private Observer<ArrayList<MovieDiscoverResultsItem>> getMovieDiscover = new Observer<ArrayList<MovieDiscoverResultsItem>>() {
        @Override
        public void onChanged(ArrayList<MovieDiscoverResultsItem> movieDiscoverResultsItems) {
            if (movieDiscoverResultsItems != null){
                movieDiscoverAdapter.setData(movieDiscoverResultsItems);
            }
        }
    };
}
