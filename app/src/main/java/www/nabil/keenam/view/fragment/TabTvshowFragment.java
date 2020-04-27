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
import www.nabil.keenam.adapter.FavoriteTvshowAdapter;
import www.nabil.keenam.database.AppDatabase;
import www.nabil.keenam.database.MovieModelDb;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabTvshowFragment extends Fragment {

    private RecyclerView rvTvshowFav;
    private ArrayList<MovieModelDb> listTvshowFav = new ArrayList<>();
    private AppDatabase appDatabase;
    private FavoriteTvshowAdapter favoriteTvshowAdapter;

    private TextView tvStatus;

    public TabTvshowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_tvshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTvshowFav = view.findViewById(R.id.fragment_tabtvshow_rv);
        tvStatus = view.findViewById(R.id.fragment_tabtvshow_tv);
        rvTvshowFav.setLayoutManager(new LinearLayoutManager(getContext()));

        if (this.appDatabase == null){
            appDatabase = AppDatabase.initDatabase(getContext());
        }

        listTvshowFav.addAll(appDatabase.movieDAO().getByCategory("tvshow"));
        favoriteTvshowAdapter = new FavoriteTvshowAdapter(getContext());
        favoriteTvshowAdapter.notifyDataSetChanged();
        favoriteTvshowAdapter.setData(listTvshowFav);

        rvTvshowFav.setAdapter(favoriteTvshowAdapter);

        if (listTvshowFav.isEmpty()){
            tvStatus.setVisibility(View.VISIBLE);
        }else{
            tvStatus.setVisibility(View.GONE);
        }
    }
}
