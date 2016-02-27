package cobrakai.com.miyagi.models.lyft;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gareoke on 2/26/16.
 */
public class LatLng {
    @SerializedName("lat") private String displayName;
    @SerializedName("lng") private String rideType;

    public LatLng(String displayName, String rideType) {
        this.displayName = displayName;
        this.rideType = rideType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getRideType() {
        return rideType;
    }
}
