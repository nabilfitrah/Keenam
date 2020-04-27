package www.nabil.keenam.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import www.nabil.keenam.R;
import www.nabil.keenam.adapter.TabFavoriteAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    private TabFavoriteAdapter tabFavoriteAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.fragment_favorite_tablayout);
        viewPager = view.findViewById(R.id.fragment_favorite_viewpager);

        tabFavoriteAdapter = new TabFavoriteAdapter(getChildFragmentManager());
        tabFavoriteAdapter.addFragment(new TabMovieFragment(), "Movie Favorite");
        tabFavoriteAdapter.addFragment(new TabTvshowFragment(), "Tvshow Favorite");

        viewPager.setAdapter(tabFavoriteAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
