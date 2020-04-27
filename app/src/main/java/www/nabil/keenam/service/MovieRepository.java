package www.nabil.keenam.service;

import retrofit2.Call;
import retrofit2.http.GET;
import www.nabil.keenam.model.movie.MovieDiscoverResponse;

public interface MovieRepository {
    @GET("3/discover/movie?api_key=94b005ebdefb50b6da022f628ede2b5b&sort_by=popularity.desc&page=1")
    Call<MovieDiscoverResponse> getMovieDiscover();
}
