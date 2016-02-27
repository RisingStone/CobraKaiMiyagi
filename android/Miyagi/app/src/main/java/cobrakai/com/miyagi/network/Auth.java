package cobrakai.com.miyagi.network;

import android.app.Activity;

import io.oauth.OAuth;
import io.oauth.OAuthCallback;


/**
 * Created by m.stanford on 2/26/16.
 */
public class Auth {

    private static final String TAG = Auth.class.getSimpleName();

    public static void getUberAuthToken(final Activity activity, OAuthCallback callback){
        OAuth oauth = new OAuth(activity);

        // Initialize the SDK
        oauth.initialize("mZYzsjep6YhUgove5xONfat34DQ");

        oauth.popup("uber", callback);
    }
}
