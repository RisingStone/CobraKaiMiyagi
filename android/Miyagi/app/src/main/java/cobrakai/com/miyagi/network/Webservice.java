package cobrakai.com.miyagi.network;

import android.util.Log;

import cobrakai.com.miyagi.models.lyft.EstimatedTime;
import cobrakai.com.miyagi.models.lyft.EstimatedTimeObject;
import cobrakai.com.miyagi.models.uber.UberModel;
import cobrakai.com.miyagi.utils.Constants;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by m.stanford on 2/26/16.
 */
public class Webservice {

    private static final String TAG = Webservice.class.getSimpleName();

    public interface GithubService {
        String SERVICE_ENDPOINT = "https://api.github.com";

        @GET("/users/{login}")
        Observable<UberModel> getUser(@Path("login") String login);
    }

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .build();
        T service = restAdapter.create(clazz);

        return service;
    }

    public static void fetchGithub(){
        GithubService service = Webservice.createRetrofitService(GithubService.class, GithubService.SERVICE_ENDPOINT);
        service.getUser("mastanford")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UberModel>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("GithubDemo", e.getMessage());
                    }

                    @Override
                    public final void onNext(UberModel response) {
                        Log.d(TAG, "Response " + response.toString());
                    }
                });
    }

    public static void fetchEstimatedTime(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.LYFT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Webservice.Get webservice = retrofit.create(Webservice.Get.class);

        Observable<EstimatedTime> estimatedTimeRxCall = webservice.lyftEstimatedTime(Constants.LYFT_ACCESS_TOKEN_SANDBOX, Constants.MOCK_LAT, Constants.MOCK_LNG);
        estimatedTimeRxCall
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<EstimatedTime>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted -- START");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError -- START -- " + e.toString());
                    }

                    @Override
                    public void onNext(EstimatedTime estimatedTime) {
                        Log.d(TAG, "onNext -- START");

                        for(EstimatedTimeObject estimateTimeObject : estimatedTime.getEtaEstimates()){
                            Log.d(TAG, estimateTimeObject.getDisplayName()); //not supposed to do it here
                            Log.d(TAG, estimateTimeObject.getRideType()); //not supposed to do it here
                            Log.d(TAG, String.valueOf(estimateTimeObject.getEtaSeconds())); //not supposed to do it here
                        }
                    }
                });
    }

    public interface Get {
        @retrofit2.http.GET(Constants.LYFT_ESTIMATED_TIME)
        Observable<EstimatedTime> lyftEstimatedTime(
                @Header("Authorization") String authorization,
                @Query("lat") String lat,
                @Query("lng") String lng
        );
    }
}