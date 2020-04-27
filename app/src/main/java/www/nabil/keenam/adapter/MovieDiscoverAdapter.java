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
import www.nabil.keenam.model.movie.MovieDiscoverResultsItem;
import www.nabil.keenam.view.activity.MovieDetailsActivity;

public class MovieDiscoverAdapter extends RecyclerView.Adapter<MovieDiscoverAdapter.ViewHolder> {

    private ArrayList<MovieDiscoverResultsItem> movieDiscoverItems = new ArrayList<>();
    private Context context;

    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185/";

    public MovieDiscoverAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MovieDiscoverResultsItem> items) {
        movieDiscoverItems.clear();
        movieDiscoverItems.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MovieDiscoverAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //menggunakan item_list yang mana
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieDiscoverAdapter.ViewHolder holder, final int position) {
        final MovieDiscoverResultsItem item = movieDiscoverItems.get(position);
        //menampilkan data yang berbeda-beda
        Glide.with(context).load(BASE_IMAGE_URL+movieDiscoverItems.get(position)
                .getPosterPath())
                .into(holder.ivThumb);

        holder.tvTitle.setText(movieDiscoverItems.get(position).getTitle());
        holder.tvRate.setText(String.valueOf(movieDiscoverItems.get(position).getVoteAverage()));

        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(context, MovieDetailsActivity.class);

                    MovieDiscoverResultsItem items = new MovieDiscoverResultsItem();
                    items.setId(item.getId());
                    items.setTitle(item.getTitle());
                    items.setPosterPath(BASE_IMAGE_URL + item.getPosterPath());
                    items.setOverview(item.getOverview());
                    items.setVoteAverage(item.getVoteAverage());
                    items.setBackdropPath(item.getBackdropPath());;

                    intent.putExtra(MovieDetailsActivity.EXTRA_DETAILS, items);
                    context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        //jumlah datanya berapa // menampilkan data yang telah ditampilkan
        return movieDiscoverItems.size();
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
