package cobrakai.com.miyagi.models.lyft;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Gareoke on 2/26/16.
 */
public class Drivers {
    @SerializedName("drivers") private ArrayList<Locations> drivers;
    @SerializedName("ride_type") private String rideType;

    public Drivers(ArrayList<Locations> drivers, String rideType) {
        this.drivers = drivers;
        this.rideType = rideType;
    }

    public ArrayList<Locations> getDrivers() {
        return drivers;
    }

    public String getRideType() {
        return rideType;
    }
}
