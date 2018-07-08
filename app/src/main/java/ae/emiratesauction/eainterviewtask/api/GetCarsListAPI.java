package ae.emiratesauction.eainterviewtask.api;

import ae.emiratesauction.eainterviewtask.data.CarsListData;
import ae.emiratesauction.eainterviewtask.utils.Constants;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GetCarsListAPI {
    String FEED = "carsonline";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET(FEED)
//    @FormUrlEncoded
    Call<CarsListData> getCarsList( );

}
