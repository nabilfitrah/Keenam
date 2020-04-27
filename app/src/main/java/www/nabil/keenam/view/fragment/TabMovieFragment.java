package www.nabil.keenam.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import www.nabil.keenam.R;
import www.nabil.keenam.adapter.FavoriteMovieAdapter;
import www.nabil.keenam.database.AppDatabase;
import www.nabil.keenam.database.MovieModelDb;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabMovieFragment extends Fragment {

    private RecyclerView rvMovieFav;
    private ArrayList<MovieModelDb> listMovieFav = new ArrayList<>();
    private AppDatabase appDatabase;
    private FavoriteMovieAdapter favoriteMovieAdapter;

    private TextView tvStatus;

    public TabMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMovieFav = view.findViewById(R.id.fragment_tabmovies_rv);
        tvStatus = view.findViewById(R.id.fragment_tabmoviews_tv);
        rvMovieFav.setLayoutManager(new LinearLayoutManager(getContext()));

        if (this.appDatabase == null){
            appDatabase = AppDatabase.initDatabase(getContext());
        }

        listMovieFav.addAll(appDatabase.movieDAO().getByCategory("movie"));
        favoriteMovieAdapter = new FavoriteMovieAdapter(getContext());
        favoriteMovieAdapter.notifyDataSetChanged();
        favoriteMovieAdapter.setData(listMovieFav);

        rvMovieFav.setAdapter(favoriteMovieAdapter);

        //jika listnya kosong maka akan keluar TextView DATA KOSONG
        if (listMovieFav.isEmpty()){
            tvStatus.setVisibility(View.VISIBLE);
        } else {
            tvStatus.setVisibility(View.GONE);
        }
    }
}
