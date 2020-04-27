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
import www.nabil.keenam.model.tvshow.TvDiscoverResultsItem;
import www.nabil.keenam.view.activity.TvDetailsActivity;

public class FavoriteTvshowAdapter extends RecyclerView.Adapter<FavoriteTvshowAdapter.ViewHolder> {

    private ArrayList<MovieModelDb> tvshowItems = new ArrayList<>();
    private Context context;

    public FavoriteTvshowAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MovieModelDb> items){
        tvshowItems.clear();
        tvshowItems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteTvshowAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new FavoriteTvshowAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteTvshowAdapter.ViewHolder holder, int i) {
        final MovieModelDb item = tvshowItems.get(i);

        Glide.with(context).load(item.getPosterPath()).into(holder.ivThumb);
        holder.tvTitle.setText(item.getTitle());
        holder.tvRate.setText(String.valueOf(item.getVoteAverage()));

        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, TvDetailsActivity.class);

                TvDiscoverResultsItem items = new TvDiscoverResultsItem();
                items.setId(item.getId());
                items.setName(item.getTitle());
                items.setPosterPath(item.getPosterPath());
                items.setOverview(item.getOverview());
                items.setVoteAverage(item.getVoteAverage());

                intent.putExtra(TvDetailsActivity.EXTRA_DETAILS, items);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tvshowItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;
        TextView tvTitle, tvRate;
        CardView cvItem;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvItem = itemView.findViewById(R.id.itemlist_cv);
            ivThumb = itemView.findViewById(R.id.itemlist_iv_thumbnail);
            tvTitle = itemView.findViewById(R.id.itemlist_tv_title);
            tvRate = itemView.findViewById(R.id.itemlist_tv_rate);
        }
    }
}
