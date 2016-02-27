package cobrakai.com.miyagi.network;

import android.security.keystore.UserNotAuthenticatedException;
import android.util.Log;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cobrakai.com.miyagi.models.lyft.Drivers;
import cobrakai.com.miyagi.models.lyft.EstimatedTime;
import cobrakai.com.miyagi.models.lyft.EstimatedTimeObject;
import cobrakai.com.miyagi.models.lyft.Locations;
import cobrakai.com.miyagi.models.lyft.NearbyDrivers;
import cobrakai.com.miyagi.models.lyft.OAuth;
import cobrakai.com.miyagi.models.uber.UberModel;
import cobrakai.com.miyagi.utils.Constants;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import com.google.android.gms.maps.GoogleMap;

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

    public static void fetchDriverLocation(final GoogleMap map, final BitmapDescriptor markerIcon, String accessToken) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.LYFT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Webservice.Get webservice = retrofit.create(Webservice.Get.class);

        Observable<NearbyDrivers> estimatedTimeRxCall = webservice.lyftDriversLocation(accessToken, Constants.MOCK_LAT, Constants.MOCK_LNG);
        estimatedTimeRxCall
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<NearbyDrivers, Observable<Drivers>>() {
                    @Override
                    public Observable<Drivers> call(NearbyDrivers nearbyDrivers) {
                        return Observable.from(nearbyDrivers.getNearbyDrivers());
                    }
                })
                .subscribe(new Subscriber<Drivers>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted -- START");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError -- START --" + e.toString() + "--");
                        if(e!=null && e.toString()!=null && e.toString().equals("retrofit2.HttpException: HTTP 401 ")) {  //bad way of detecting 401 but whatever hackathon!!
                            refreshLyftOauth();
                        }
                    }

                    @Override
                    public void onNext(Drivers driver) {
                        if(driver.getRideType()!=null && driver.getRideType().equals("lyft")){
                            for(Locations locations : driver.getDrivers()){
                                double lat = locations.getLocations().get(0).getLat();
                                double lng = locations.getLocations().get(0).getLng();

                                Log.d(TAG, "lat: " + lat);
                                Log.d(TAG, "lng: " + lng);
                                LatLng latLng = new LatLng(lat,lng);
                                map.addMarker(new MarkerOptions().position(latLng).icon(markerIcon).title("Lyft Driver"));
                            }
                        }
                    }
                });
    }

    public static void refreshLyftOauth() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.LYFT_BASE_OAUTH_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Log.d(TAG, "gta::"+Constants.LYFT_BASE_OAUTH_URL);

        Webservice.Post webservice = retrofit.create(Webservice.Post.class);

        Observable<OAuth> oAuthObservable = webservice.lyftToken(Constants.LYFT_CLIENT_ID, Constants.LYFT_CLIENT_SECRET, "public");
        oAuthObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OAuth>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted -- oAuthObservable -- START");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError -- oAuthObservable -- " + e.toString());
                    }

                    @Override
                    public void onNext(OAuth oAuth) {
                        Log.d(TAG, "onNext -- oAuthObservable -- " + oAuth.getAccessToken());
                    }
                });
    }


    public interface Post {
        @FormUrlEncoded
        @retrofit2.http.POST(Constants.LYFT_OAUTH_TOKEN)
        Observable<OAuth> lyftToken(
                @Query("scope") String scope,
                @Field("client_id") String clientId,
                @Field("client_secret") String clientSecret
        );
    }

    public interface Get{
        @retrofit2.http.GET(Constants.LYFT_ESTIMATED_TIME)
        Observable<EstimatedTime> lyftEstimatedTime(
                @Header("Authorization") String authorization,
                @Query("lat") String lat,
                @Query("lng") String lng
        );

        @retrofit2.http.GET(Constants.LYFT_DRIVERS_LOCATION)
        Observable<NearbyDrivers> lyftDriversLocation(
                @Header("Authorization") String authorization,
                @Query("lat") String lat,
                @Query("lng") String lng
        );
    }
}