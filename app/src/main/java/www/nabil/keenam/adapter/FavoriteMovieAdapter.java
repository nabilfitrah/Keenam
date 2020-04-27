package www.nabil.keenam.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import www.nabil.keenam.R;
import www.nabil.keenam.database.MovieModelDb;
import www.nabil.keenam.model.movie.MovieDiscoverResultsItem;
import www.nabil.keenam.view.activity.MovieDetailsActivity;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder> {

    private ArrayList<MovieModelDb> movieItems = new ArrayList<>();
    private Context context;

    public FavoriteMovieAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MovieModelDb> items) {
        movieItems.clear();
        movieItems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteMovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new FavoriteMovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteMovieAdapter.ViewHolder holder, int i) {
        final MovieModelDb item = movieItems.get(i);

        Glide.with(context).load(item.getPosterPath()).into(holder.ivThumb);
        holder.tvTitle.setText(item.getTitle());
        holder.tvRate.setText(String.valueOf(item.getVoteAverage()));

        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);

                MovieDiscoverResultsItem items = new MovieDiscoverResultsItem();
                items.setId(item.getId());
                items.setTitle(item.getTitle());
                items.setPosterPath(item.getPosterPath());
                items.setOverview(item.getOverview());
                items.setVoteAverage(item.getVoteAverage());

                intent.putExtra(MovieDetailsActivity.EXTRA_DETAILS, items);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView tvTitle, tvRate;
        CardView cvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvItem = itemView.findViewById(R.id.itemlist_cv);
            ivThumb = itemView.findViewById(R.id.itemlist_iv_thumbnail);
            tvTitle = itemView.findViewById(R.id.itemlist_tv_title);
            tvRate = itemView.findViewById(R.id.itemlist_tv_rate);
        }
    }
}