package cobrakai.com.miyagi.models.foursquare;

import com.google.gson.annotations.SerializedName;

/**
 * Created by g.anderson on 1/25/16.
 */
public class Location {
    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    @SerializedName("address")
    private String address;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    public Location(double lat, double lng, String address, String city, String state) {
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getFormattedLocation(){
        return this.address + ", " + this.city + " " + this.state;
    }
}
