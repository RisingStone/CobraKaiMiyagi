package cobrakai.com.miyagi.network;

import android.content.Context;
import android.graphics.Color;
import android.security.keystore.UserNotAuthenticatedException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import cobrakai.com.miyagi.R;
import cobrakai.com.miyagi.animation.LatLngInterpolator;
import cobrakai.com.miyagi.animation.MarkerAnimation;
import cobrakai.com.miyagi.models.google.AddressComponent;
import cobrakai.com.miyagi.models.google.ReverseGeoLookupResponse;
import cobrakai.com.miyagi.models.lyft.Drivers;
import cobrakai.com.miyagi.models.lyft.EstimatedTime;
import cobrakai.com.miyagi.models.lyft.EstimatedTimeObject;
import cobrakai.com.miyagi.models.lyft.Locations;
import cobrakai.com.miyagi.models.lyft.NearbyDrivers;
import cobrakai.com.miyagi.models.lyft.OAuth;
import cobrakai.com.miyagi.models.miyagi.Hub;
import cobrakai.com.miyagi.models.uber.UberPrice;
import cobrakai.com.miyagi.utils.Constants;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by m.stanford on 2/26/16.
 */
public class Webservice {
    private static boolean firstTimeFlag = Boolean.TRUE;
    private static ArrayList<Marker> markers;
    private static final String TAG = Webservice.class.getSimpleName();

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .build();
        T service = restAdapter.create(clazz);

        return service;
    }

    public static void fetchUberPrice(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.LYFT_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Webservice.Get webservice = retrofit.create(Webservice.Get.class);
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

    public static void fetchDriverUpdateLocation(final GoogleMap map, final BitmapDescriptor markerIcon, String accessToken) {
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
                        Log.d(TAG, "onCompleted -- fetchDriverUpdateLocation -- START");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError -- fetchDriverUpdateLocation -- START --" + e.toString() + "--");
                        if(e!=null && e.toString()!=null && e.toString().equals("retrofit2.HttpException: HTTP 401 ")) {  //bad way of detecting 401 but whatever hackathon!!
                            refreshLyftOauth();
                        }
                    }

                    @Override
                    public void onNext(Drivers driver) {
                        int counter = 0;
                        if(driver.getRideType()!=null && driver.getRideType().equals("lyft")){
                            for(Locations locations : driver.getDrivers()){
                                double lat = locations.getLocations().get(0).getLat();
                                double lng = locations.getLocations().get(0).getLng();

                                Log.d(TAG, "lat: " + lat);
                                Log.d(TAG, "lng: " + lng);
                                LatLng latLng = new LatLng(lat,lng);

                                LatLngInterpolator latLngInterpolator = new LatLngInterpolator.Linear();
                                Marker marker = markers.size() > counter ? markers.get(counter) : null;

                                if(marker != null){
                                    MarkerAnimation.animateMarkerToGB(marker, latLng, latLngInterpolator, 1500);
                                } else{
                                    marker = map.addMarker(new MarkerOptions().position(latLng).icon(markerIcon).title("Driver " + counter));

                                    if(marker!=null){
                                        markers.add(marker);
                                    }
                                }

                                counter++;
                            }

                            MarkerAnimation.startAnimate();
                        }
                    }
                });
    }

    public static void fetchMockHubLocation(final GoogleMap map, final BitmapDescriptor markerIcon, String accessToken) {
        markers = new ArrayList<>();
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
                        int counter = 0;
                        if(driver.getRideType()!=null && driver.getRideType().equals("lyft")){
                            for(Locations locations : driver.getDrivers()){
//                                if(counter >= 1){
//                                    counter = 0;
//                                    break;
//                                }
                                double lat = locations.getLocations().get(0).getLat();
                                double lng = locations.getLocations().get(0).getLng();

                                Log.d(TAG, "lat: " + lat);
                                Log.d(TAG, "lng: " + lng);

                                reverseGeoLoookup(map, markerIcon, lat, lng);
                            }
                        }
                    }
                });
    }

    public static void fetchDriverInitialLocation(final GoogleMap map, final BitmapDescriptor markerIcon, String accessToken) {
        markers = new ArrayList<>();
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
                        int counter = 0;
                        if(driver.getRideType()!=null && driver.getRideType().equals("lyft")){
                            for(Locations locations : driver.getDrivers()){
//                                if(counter >= 1){
//                                    counter = 0;
//                                    break;
//                                }
                                double lat = locations.getLocations().get(0).getLat();
                                double lng = locations.getLocations().get(0).getLng();

                                Log.d(TAG, "lat: " + lat);
                                Log.d(TAG, "lng: " + lng);

                                LatLng latLng = new LatLng(lat,lng);
                                Marker marker = map.addMarker(new MarkerOptions().position(latLng).icon(markerIcon).title("Lyft Driver " + counter));

                                LatLngInterpolator latLngInterpolator = new LatLngInterpolator.Linear();
                                if(marker != null){
                                    markers.add(marker);
                                }

                                counter++;
                            }
                        }
                    }
                });
    }

    public static void testMyLocalHost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.TESTPREFIX)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Webservice.Get webservice = retrofit.create(Webservice.Get.class);

        Observable<String> myLocalServer = webservice.testMyNodeServer();
        myLocalServer
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext -- mylocalhost -- START");
                    }
                });
    }

    public static void fetchKreeseLocation(final GoogleMap map, final BitmapDescriptor kreeseIcon, Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.LOCAL_HOST_PREFIX)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Webservice.Get webservice = retrofit.create(Webservice.Get.class);

        Observable<Hub[]> kreeseLocations = webservice.kreeseLocations();
        kreeseLocations
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Hub[]>() {
                               @Override
                               public void onCompleted() {
                                   Log.d(TAG, "onCompleted -- kreeseLocations -- START");
                               }

                               @Override
                               public void onError(Throwable e) {
                                   Log.d(TAG, "onError -- kreeseLocations -- START -- " + e.toString());
                               }

                               @Override
                               public void onNext(Hub[] hubs) {
                                   Log.d(TAG, "kreeseLocations -- START");
                                   ArrayList<LatLng> latLngs = new ArrayList<>();
                                   for(Hub hub : hubs){
                                       List<List<Double>> coordinates = hub.getArea().getCoordinates().get(0);
                                       for(List<Double> doubles : coordinates) {
                                           Log.d(TAG, "kreeseLocations -- " + doubles.get(0).toString());

                                           latLngs.add(new LatLng(doubles.get(1), doubles.get(0)));
                                       }
                                   }

                                   LatLng[] latLngArray = new LatLng[latLngs.size()];
                                   latLngArray = latLngs.toArray(latLngArray);
                                   Iterable<LatLng> iterable = Arrays.asList(latLngArray);

                                   Polygon polygon = map.addPolygon(new PolygonOptions()
                                           .addAll(iterable)
                                           .strokeColor(Color.RED)
                                           .fillColor(Color.BLUE));
                               }
                           });

/*
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.raw_hub_response);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            Gson gson = new Gson();
            Hub[] hubs = gson.fromJson(new String(b), Hub[].class);
            Log.d(TAG, "kreeseLocations -- " + hubs[0].getId());

            ArrayList<LatLng> latLngs = new ArrayList<>();
            for(Hub hub : hubs){
                List<List<Double>> coordinates = hub.getArea().getCoordinates().get(0);
                for(List<Double> doubles : coordinates) {
                    Log.d(TAG, "kreeseLocations -- " + doubles.get(0).toString());

                    latLngs.add(new LatLng(doubles.get(1), doubles.get(0)));
                }
            }

            LatLng[] latLngArray = new LatLng[latLngs.size()];
            latLngArray = latLngs.toArray(latLngArray);
            Iterable<LatLng> iterable = Arrays.asList(latLngArray);

            Polygon polygon = map.addPolygon(new PolygonOptions()
                    .addAll(iterable)
                    .strokeColor(Color.RED)
                    .fillColor(Color.BLUE));
        } catch (IOException e){
            Log.e(TAG, "kreeseLocations -- " + e.toString());
        }*/
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
        oAuthObservable
                .subscribeOn(Schedulers.newThread())
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

    public static void reverseGeoLoookup(final GoogleMap map, final BitmapDescriptor markerIcon, final double lat, final double lng) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.GOOGLE_MAPS_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        Webservice.Get webservice = retrofit.create(Webservice.Get.class);

        String latlng = String.valueOf(lat) + "," + String.valueOf(lng); //Constants.MOCK_LAT + "," + Constants.MOCK_LNG;

        Observable<ReverseGeoLookupResponse> reverseObservable = webservice.reverseGeoLookup("true", latlng);
        reverseObservable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReverseGeoLookupResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted -- ReverseGeoLookupResponse -- START");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError -- ReverseGeoLookupResponse -- " + e.toString());
                    }

                    @Override
                    public void onNext(ReverseGeoLookupResponse reverseGeoLookupResponse) {
                        Log.d(TAG, "onNext -- ReverseGeoLookupResponse -- ");

                        List<AddressComponent> addressComponents = reverseGeoLookupResponse.getResults().get(0).getAddressComponents();

                        if(addressComponents != null) {
                            String name = addressComponents.get(3) != null ? addressComponents.get(3).getLongName() + "," + addressComponents.get(5).getShortName() : "";
                            String street = addressComponents.get(0) != null ? addressComponents.get(0).getLongName() + "," + addressComponents.get(1).getLongName() : "";
                            String neighborhood = addressComponents.get(2).getLongName()!=null ? addressComponents.get(2).getLongName() : "";
                            String number = addressComponents.get(0).getLongName() != null ? addressComponents.get(0).getLongName() : "";
                            String city = addressComponents.get(3).getLongName() != null ? addressComponents.get(3).getLongName() : "";
                            String state = addressComponents.get(5).getShortName() != null ? addressComponents.get(5).getShortName() : "";
                            String zip = addressComponents.get(6).getShortName() != null ? addressComponents.get(6).getShortName() : "";
                            String address = reverseGeoLookupResponse.getResults().get(0).getFormattedAddress();

                            LatLng latLng = new LatLng(lat,lng);
                            Marker marker = map.addMarker(new MarkerOptions().position(latLng).icon(markerIcon).title(address));
                        }
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

        @retrofit2.http.GET(Constants.MIYAGI_API_HUBS)
        Observable<Hub[]> kreeseLocations();

        @retrofit2.http.GET(Constants.TESTSUFFX)
        Observable<String> testMyNodeServer();

        @retrofit2.http.GET(Constants.GOOGLE_MAPS_REVERSE_LOOKUP)
        Observable<ReverseGeoLookupResponse> reverseGeoLookup(
                @Query("sensor") String sensor,
                @Query("latlng") String latlng
        );
    }
}