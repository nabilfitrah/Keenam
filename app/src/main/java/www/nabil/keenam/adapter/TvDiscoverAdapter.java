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
import www.nabil.keenam.model.tvshow.TvDiscoverResultsItem;
import www.nabil.keenam.view.activity.TvDetailsActivity;

public class TvDiscoverAdapter extends RecyclerView.Adapter<TvDiscoverAdapter.ViewHolder> {

    private ArrayList<TvDiscoverResultsItem> tvDiscoverItems = new ArrayList<>();
    private Context context;

    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";

    public TvDiscoverAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<TvDiscoverResultsItem> items){
        tvDiscoverItems.clear();
        tvDiscoverItems.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvDiscoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvDiscoverAdapter.ViewHolder holder, int position) {
        final TvDiscoverResultsItem item = tvDiscoverItems.get(position);
        Glide.with(context).load(BASE_IMAGE_URL+tvDiscoverItems.get(position)
                .getPosterPath())
                .into(holder.ivThumb);

        holder.tvTitle.setText(tvDiscoverItems.get(position).getName());
        holder.tvRate.setText(String.valueOf(tvDiscoverItems.get(position).getVoteAverage()));

        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, TvDetailsActivity.class);

                TvDiscoverResultsItem item1 = new TvDiscoverResultsItem();
                item1.setId(item.getId());
                item1.setName(item.getName());
                item1.setPosterPath(BASE_IMAGE_URL + item.getPosterPath());
                item1.setOverview(item.getOverview());
                item1.setVoteAverage(item.getVoteAverage());
                item.setBackdropPath(item.getBackdropPath());

                intent.putExtra(TvDetailsActivity.EXTRA_DETAILS, item1);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tvDiscoverItems.size();
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
