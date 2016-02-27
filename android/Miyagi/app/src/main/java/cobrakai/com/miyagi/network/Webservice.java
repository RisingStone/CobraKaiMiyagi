package cobrakai.com.miyagi.network;

import android.util.Log;

import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
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
}