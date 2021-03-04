package sd.rittal.qrsdk.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;
import sd.rittal.qrsdk.beans.Purchase;

public interface ApiService {

    @Headers("Accept: text/html")
    @POST()
    Call<String> qrPurchase(@Url String url,@Body String purchase);

    @Headers("Accept: text/html")
    @POST()
    Call<String> getKey( @Url String url);


}
