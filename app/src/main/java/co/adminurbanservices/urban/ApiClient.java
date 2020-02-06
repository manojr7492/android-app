package co.adminurbanservices.urban;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haerul on 17/03/18.
 */

class ApiClient {

   // private static final String BASE_URL = "http://192.168.0.4/GET/";
    private static Retrofit retrofit;

    static Retrofit getApiClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
