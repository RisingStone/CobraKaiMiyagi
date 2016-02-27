package cobrakai.com.miyagi.models.lyft;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Gareoke on 2/26/16.
 */
public class EstimatedTimeObject {
    @SerializedName("display_name") private String displayName;
    @SerializedName("ride_type") private String rideType;
    @SerializedName("eta_seconds") private int etaSeconds;

    public EstimatedTimeObject(String displayName, String rideType, int etaSeconds) {
        this.displayName = displayName;
        this.rideType = rideType;
        this.etaSeconds = etaSeconds;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getRideType() {
        return rideType;
    }

    public int getEtaSeconds() {
        return etaSeconds;
    }
}
