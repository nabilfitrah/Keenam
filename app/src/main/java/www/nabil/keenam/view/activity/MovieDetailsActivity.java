package www.nabil.keenam.view.activity;;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import www.nabil.keenam.R;
import www.nabil.keenam.database.AppDatabase;
import www.nabil.keenam.database.MovieModelDb;
import www.nabil.keenam.model.movie.MovieDiscoverResultsItem;

public class MovieDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_DETAILS = "extra_details";

    ImageView activitydetails_iv;
    TextView activitydetails_tv_nama, activitydetails_tv_desc;
    private AppDatabase appDatabase;
    boolean isFavorite = false;

    private MovieDiscoverResultsItem getMovie;

    private Menu menuItem = null;

    public MovieDetailsActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetails);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        activitydetails_iv = findViewById(R.id.activityimplisit_iv);
        activitydetails_tv_nama = findViewById(R.id.activityimplisit_tv_nama);
        activitydetails_tv_desc = findViewById(R.id.activityimplisit_tv_desc);

        getMovie = getIntent().getParcelableExtra(EXTRA_DETAILS);

        Glide.with(getApplicationContext()).load(getMovie.getPosterPath()).into(activitydetails_iv);

        activitydetails_tv_nama.setText(getMovie.getTitle());
        activitydetails_tv_desc.setText(getMovie.getOverview());

        appDatabase = AppDatabase.initDatabase(getApplicationContext());
        favoriteState();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_favorite:
                if (isFavorite){
                    deleteFavorite();
                    isFavorite = false;
                    setFavorite();
                } else {
                    saveToFavorite();
                    isFavorite = true;
                    setFavorite();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteFavorite(){

        MovieModelDb movieModelDb = new MovieModelDb();

        movieModelDb.setId(getMovie.getId());
        movieModelDb.setTitle(getMovie.getTitle());
        movieModelDb.setPosterPath(getMovie.getPosterPath());
        movieModelDb.setOverview(getMovie.getOverview());
        movieModelDb.setVoteAverage(getMovie.getVoteAverage());
        movieModelDb.setCategory("movie");

        appDatabase.movieDAO().deleteMovie(movieModelDb);
        Toast.makeText(getApplicationContext(), "DELETE FROM FAVORITE", Toast.LENGTH_SHORT).show();
    }

    private void saveToFavorite(){

        MovieModelDb movieModelDb = new MovieModelDb();

        movieModelDb.setId(getMovie.getId());
        movieModelDb.setTitle(getMovie.getTitle());
        movieModelDb.setPosterPath(getMovie.getPosterPath());
        movieModelDb.setOverview(getMovie.getOverview());
        movieModelDb.setVoteAverage(getMovie.getVoteAverage());
        movieModelDb.setCategory("movie");

        appDatabase.movieDAO().insertMovie(movieModelDb);

        Toast.makeText(getApplicationContext(), "SAVE TO FAVORITE", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorite, menu);
        menuItem = menu;
        favoriteState();
        if (isFavorite) {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.star));
        } else {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this,R.drawable.star_uncheck));
        }
        return super.onCreateOptionsMenu(menu);
    }

    private void setFavorite(){
        if (isFavorite) {
            menuItem.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.star));
        } else {
            menuItem.getItem(0).setIcon(ContextCompat.getDrawable(this,R.drawable.star_uncheck));
        }
    }

    public void favoriteState(){

        List<MovieModelDb> getById = appDatabase.movieDAO().getById(getMovie.getId());
        if (!getById.isEmpty()){
            isFavorite = true;
        }
    }
}
