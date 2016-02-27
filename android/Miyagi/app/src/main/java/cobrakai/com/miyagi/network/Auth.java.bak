package cobrakai.com.miyagi.network;

import android.app.Activity;
import android.content.Context;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson.JacksonFactory;
import com.wuman.android.auth.AuthorizationFlow;
import com.wuman.android.auth.AuthorizationUIController;
import com.wuman.android.auth.DialogFragmentController;
import com.wuman.android.auth.OAuthManager;
import com.wuman.android.auth.oauth2.store.SharedPreferencesCredentialStore;

import java.io.IOException;

/**
 * Created by m.stanford on 2/26/16.
 */
public class Auth {

    public static Credential auth(Context ctx, String userId, String authUrl, String clientId) throws IOException {

        Activity context = (Activity) ctx;

        SharedPreferencesCredentialStore credentialStore =
                new SharedPreferencesCredentialStore(context,
                        "preferenceFileName", new JacksonFactory());

        AuthorizationFlow.Builder builder = new AuthorizationFlow.Builder(
                BearerToken.authorizationHeaderAccessMethod(),
                AndroidHttp.newCompatibleTransport(),
                new JacksonFactory(),
                new GenericUrl("https://login.uber.com/oauth/v2/authorize"),
                new ClientParametersAuthentication("client_id", "Qdtevhvnjsz9J4MUE0243LJZo8jUUAKU"),
                "CLIENT_ID",
                "https://login.uber.com/oauth/v2/authorize");
        builder.setCredentialStore(credentialStore);
        AuthorizationFlow flow = builder.build();

        AuthorizationUIController controller =
                new DialogFragmentController(context.getFragmentManager()) {

                    @Override
                    public String getRedirectUri() throws IOException {
                        return "http://localhost/Callback";
                    }

                    @Override
                    public boolean isJavascriptEnabledForWebView() {
                        return true;
                    }

                };

        OAuthManager oauth = new OAuthManager(flow, controller);

        return oauth.authorizeImplicitly(userId, null, null).getResult();
    }
}
