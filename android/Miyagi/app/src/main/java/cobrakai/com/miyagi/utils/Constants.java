package cobrakai.com.miyagi.utils;

/**
 * Created by Gareoke on 2/26/16.
 */
public class Constants {
    //Lyft Constants
    public static final String LYFT_BASE_URL = "https://api.lyft.com/";
    public static final String LYFT_ESTIMATED_TIME = "v1/eta";
    public static final String LYFT_DRIVERS_LOCATION = "v1/drivers";
    public static final String LYFT_DRIVERS_COST = "v1/cost";
    public static final String LYFT_CLIENT_ID = "BBLCVdMuy1UF";
    public static final String LYFT_CLIENT_SECRET = "SANDBOX-Q5GpCDF8_YRGA47ajRhjZIYuInNNjyFU";

    public static final String LYFT_BASE_OAUTH_URL = "https://" + LYFT_CLIENT_ID + ":SANDBOX-" + LYFT_CLIENT_SECRET + "@api.lyft.com/";
    public static final String LYFT_OAUTH_TOKEN = "oauth/token";

    public static final String LYFT_ACCESS_TOKEN = "Bearer gAAAAABW010ZFpy63ZKepvW_s1Wpd-MeRFL8-NxmzPkddhJKcSNFo09CTdUAyVFbbnrz-TqdOZpEeI-uslgGMK1plhi96Y_dRz7t_8kDSz1WtfcwudlB7-jnUdoHZyLCUKPWnM44okwFoMq2-kvCYeDPtI16P7wlMOa8ZwvLfeHvJuQmenDch2toUkDgTwlPVEYx4MuJ4q-QmwhJj6X2kNmAdSFHq1-Rvg==";
    public static final String LYFT_ACCESS_TOKEN_SANDBOX = "Bearer SANDBOX-gAAAAABW0rjMtPWq5ALvnfFPAYHyxTD1XYXjRcf0gThfuuPuw-DfUehHBNsw6oYRWqQyvEdOqLwY5i3Nj8yxtHimU3v1DhpmgirGggZ7GZBtw2MuAS4CJBmFty4n6SURByD9WnrZZLkqGZWjr0XgsrPdhq7PBpQxcoxx1kRJHXoNYGQ7MfA2CyhahmdECKyZcErnKmllDjYixpR23-UOog-wnK-INYmDAitKaemoUaIWopKph8t33UZ00c98kt11gW8tkGn2r6u6";

    //Miyagi API
    public static final String LOCAL_HOST_PREFIX = "http://192.168.11.133:3000/";
    public static final String MIYAGI_API_HUBS = "hubs/";
    public static final String MIYAGI_API_HUBS_QUE_PERSON = "hubs/{hubid}/{ridertype}/{id}";
    public static final String TESTPREFIX = "http://192.168.11.129:8080/";
    public static final String TESTSUFFX = "api/miyagi/hubs?";

    //Mock Data
//    public static final String MOCK_LAT = "37.6213129";        //SFO Airport
//    public static final String MOCK_LNG = "-122.3811494";

    public static final String MOCK_LAT = "37.805985";        //Fort Mason
    public static final String MOCK_LNG = "-122.431896";

    //Directions
    public static final String GOOGLE_MAPS_BASEURL = "http://maps.googleapis.com/maps/api/";
    public static final String GOOGLE_MAPS_REVERSE_LOOKUP = "geocode/json";

    //Foursquare
    public static final String FOURSQUARE_CLIENT_ID = "OCT5PKK5L1UVBA3CAQKC5HO0WURE2LPLS13GUB3YMU3VWCHO";
    public static final String FOURSQUARE_CLIENT_SECRET = "HU1QZHFADFCDYILP2VL5AJQDGBJB231RKGNFDPARF3JJIBHL";
    public static final String FOURSQUARE_VERSION = "20130815";

    public static final String FOURSQURE_API_PREFIX = "https://api.foursquare.com";
}