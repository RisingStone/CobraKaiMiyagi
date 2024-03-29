package cobrakai.com.miyagi.utils;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import cobrakai.com.miyagi.models.foursquare.Location;
import cobrakai.com.miyagi.models.foursquare.Venue;

/**
 * Created by g.anderson on 1/28/16.
 */
public class OnLocationClickListener implements View.OnClickListener {
    private static final String TAG = OnLocationClickListener.class.getSimpleName();
    private Location location;
    private Venue venue;

    private static Activity activity;

    public OnLocationClickListener(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, location.getFormattedLocation());

        Intent returnIntent = new Intent();
        returnIntent.putExtra("name", this.venue.getName());
        returnIntent.putExtra("lat", this.venue.getLocation().getLat());
        returnIntent.putExtra("lng", this.venue.getLocation().getLng());

        returnIntent.putExtra("location", ((new StringBuilder()).append(location.getCity()).append(" ").append(location.getState())).toString());
        this.activity.setResult(Activity.RESULT_OK, returnIntent);
        this.activity.finish();
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public void setVenue(Venue venue){
        this.venue = venue;
    }

    public Location getLcoation(){
        return this.location;
    }

    public Venue getVenue(){
        return this.venue;
    }
}
