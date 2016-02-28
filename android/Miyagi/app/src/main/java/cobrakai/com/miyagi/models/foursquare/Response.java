package cobrakai.com.miyagi.models.foursquare;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by g.anderson on 1/26/16.
 */
public class Response {
    ArrayList<Venue> venues;

    public Response(ArrayList<Venue> venues){
        this.venues = venues;
    }

    public ArrayList<Venue> getVenues(){
        Collections.reverse(this.venues);

        return this.venues;
    }
}
