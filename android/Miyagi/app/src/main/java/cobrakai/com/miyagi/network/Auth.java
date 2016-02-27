package cobrakai.com.miyagi.network;

import android.app.Activity;
import android.util.Log;

import io.oauth.OAuth;
import io.oauth.OAuthCallback;
import io.oauth.OAuthData;


/**
 * Created by m.stanford on 2/26/16.
 */
public class Auth {

    private static final String TAG = Auth.class.getSimpleName();

    public static String getAuthToken(final Activity activity){
        OAuth oauth = new OAuth(activity);

        // Initialize the SDK
        oauth.initialize("Qdtevhvnjsz9J4MUE0243LJZo8jUUAKU");

        oauth.popup("uber", new OAuthCallback() {
            @Override
            public void onFinished(OAuthData data) {
                Log.d(TAG, data.toString());
            }
        });

        return "";
    }
}
