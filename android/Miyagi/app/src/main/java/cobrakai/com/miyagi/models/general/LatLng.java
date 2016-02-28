package cobrakai.com.miyagi.models.general;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gareoke on 2/26/16.
 */
public class LatLng {
    @SerializedName("lat") private double lat;
    @SerializedName("lng") private double lng;

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
