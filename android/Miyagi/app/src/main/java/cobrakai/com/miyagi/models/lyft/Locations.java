package cobrakai.com.miyagi.models.lyft;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import cobrakai.com.miyagi.models.general.LatLng;

/**
 * Created by Gareoke on 2/26/16.
 */
public class Locations {
    @SerializedName("locations") private ArrayList<LatLng> locations;

    public Locations(ArrayList<LatLng> locations) {
        this.locations = locations;
    }

    public ArrayList<LatLng> getLocations() {
        return locations;
    }
}
