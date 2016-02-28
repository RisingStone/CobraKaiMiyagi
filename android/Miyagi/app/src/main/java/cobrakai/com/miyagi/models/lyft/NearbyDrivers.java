package cobrakai.com.miyagi.models.lyft;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Gareoke on 2/26/16.
 */
public class NearbyDrivers {
    @SerializedName("nearby_drivers") private ArrayList<Drivers> nearbyDrivers;
    @SerializedName("ride_type") private String rideType;

    public NearbyDrivers(ArrayList<Drivers> nearbyDrivers, String rideType) {
        this.nearbyDrivers = nearbyDrivers;
        this.rideType = rideType;
    }

    public ArrayList<Drivers> getNearbyDrivers() {
        return nearbyDrivers;
    }

    public String getRideType() {
        return rideType;
    }
}
