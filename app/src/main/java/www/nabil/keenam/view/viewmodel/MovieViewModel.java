package www.nabil.keenam.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import www.nabil.keenam.model.movie.MovieDiscoverResponse;
import www.nabil.keenam.model.movie.MovieDiscoverResultsItem;
import www.nabil.keenam.model.tvshow.TvDiscoverResponse;
import www.nabil.keenam.model.tvshow.TvDiscoverResultsItem;
import www.nabil.keenam.service.ApiMain;

public class MovieViewModel extends ViewModel {
    private ApiMain apiMain;

    //library live data dengan tipe ArrayList
    private MutableLiveData<ArrayList<MovieDiscoverResultsItem>> listDiscoverMovie = new MutableLiveData<>();
    private MutableLiveData<ArrayList<TvDiscoverResultsItem>> listDiscoverTv = new MutableLiveData<>();

    public void setMovieDiscover(){
        if (this.apiMain == null){
            apiMain = new ApiMain();
        }

        apiMain.getApiMovie().getMovieDiscover().enqueue(new Callback<MovieDiscoverResponse>() {
            @Override
            public void onResponse(Call<MovieDiscoverResponse> call, Response<MovieDiscoverResponse> response) {
                MovieDiscoverResponse movieresponseDiscover = response.body();
                if (movieresponseDiscover != null && movieresponseDiscover.getResults() != null){
                    ArrayList<MovieDiscoverResultsItem> movieDiscoverItems = movieresponseDiscover.getResults();
                    listDiscoverMovie.postValue(movieDiscoverItems);
                }
            }

            @Override
            public void onFailure(Call<MovieDiscoverResponse> call, Throwable t) {

            }
        });
    }

    public void setTvDiscover(){
        if (this.apiMain == null){
            apiMain = new ApiMain();
        }

        apiMain.getApiMovies().getTvDiscover().enqueue(new Callback<TvDiscoverResponse>() {
            @Override
            public void onResponse(Call<TvDiscoverResponse> call, Response<TvDiscoverResponse> response) {
                TvDiscoverResponse tvresponseDiscover = response.body();
                if (tvresponseDiscover != null && tvresponseDiscover.getResults() != null){
                    ArrayList<TvDiscoverResultsItem> tvDiscoverItems = tvresponseDiscover.getResults();
                    listDiscoverTv.postValue(tvDiscoverItems);
                }
            }

            @Override
            public void onFailure(Call<TvDiscoverResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<MovieDiscoverResultsItem>> getMoviesDiscover(){
        return listDiscoverMovie;
    }

    public LiveData<ArrayList<TvDiscoverResultsItem>> getTvDiscover(){
        return listDiscoverTv;
    }
}
