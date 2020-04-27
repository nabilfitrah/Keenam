package www.nabil.keenam.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import www.nabil.keenam.R;
import www.nabil.keenam.adapter.TvDiscoverAdapter;
import www.nabil.keenam.model.tvshow.TvDiscoverResultsItem;
import www.nabil.keenam.view.viewmodel.MovieViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvshowFragment extends Fragment {

    private TvDiscoverAdapter tvDiscoverAdapter;
    private RecyclerView rvTvDiscover;
    private MovieViewModel movieViewModel;


    public TvshowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvDiscoverAdapter = new TvDiscoverAdapter(getContext());
        tvDiscoverAdapter.notifyDataSetChanged();

        rvTvDiscover = view.findViewById(R.id.fragmenttv_rv);
        rvTvDiscover.setLayoutManager(new GridLayoutManager(getContext(),2));

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.setTvDiscover();
        movieViewModel.getTvDiscover().observe(this,getTvDiscover);

        rvTvDiscover.setAdapter(tvDiscoverAdapter);
    }

    private Observer<ArrayList<TvDiscoverResultsItem>> getTvDiscover = new Observer<ArrayList<TvDiscoverResultsItem>>() {
        @Override
        public void onChanged(ArrayList<TvDiscoverResultsItem> tvDiscoverResultsItems) {
            if (tvDiscoverResultsItems != null){
                tvDiscoverAdapter.setData(tvDiscoverResultsItems);
            }
        }
    };
}
