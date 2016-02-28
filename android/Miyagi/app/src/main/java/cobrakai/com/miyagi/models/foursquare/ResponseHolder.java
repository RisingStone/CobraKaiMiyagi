package cobrakai.com.miyagi.models.foursquare;

import com.google.gson.annotations.SerializedName;

/**
 * Created by g.anderson on 1/25/16.
 */
public class ResponseHolder {
    @SerializedName("meta")
    Meta meta;

    @SerializedName("response")
    Response response;

    public ResponseHolder(Meta meta, Response response){
        this.response = response;
    }

    public Meta getMeta(){
        return this.meta;
    }

    public Response getResponse(){
        return this.response;
    }
}
