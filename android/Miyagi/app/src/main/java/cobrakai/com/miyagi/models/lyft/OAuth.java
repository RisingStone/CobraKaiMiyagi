package cobrakai.com.miyagi.models.lyft;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gareoke on 2/27/16.
 */
public class OAuth {
    @SerializedName("token_type") private String tokenType;
    @SerializedName("access_token") private String accessToken;
    @SerializedName("expires_in") private int expiresIn;
    @SerializedName("scope") private String scope;

    public OAuth(String tokenType, String accessToken, int expiresIn, String scope) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getScope() {
        return scope;
    }
}
