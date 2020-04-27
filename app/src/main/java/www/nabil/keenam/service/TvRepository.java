package www.nabil.keenam.service;

import retrofit2.Call;
import retrofit2.http.GET;
import www.nabil.keenam.model.tvshow.TvDiscoverResponse;

public interface TvRepository {
    @GET("3/discover/tv?api_key=94b005ebdefb50b6da022f628ede2b5b")
    Call<TvDiscoverResponse> getTvDiscover();
}
