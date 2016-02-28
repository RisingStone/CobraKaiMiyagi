package cobrakai.com.miyagi.views;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cobrakai.com.miyagi.R;
import cobrakai.com.miyagi.models.foursquare.ResponseHolder;
import cobrakai.com.miyagi.network.Webservice;
import cobrakai.com.miyagi.utils.Constants;
import cobrakai.com.miyagi.utils.OnLocationClickListener;
import cobrakai.com.miyagi.views.adapters.LocationAdapter;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

public class LocationActivity extends AppCompatActivity {

    private final static String TAG = LocationActivity.class.getSimpleName();

    @InjectView(R.id.location_list) protected RecyclerView locationRecyclerView;
    @InjectView(R.id.search_for_places) protected EditText searchForPlacesView;

    LocationAdapter locationAdapter;

    OnLocationClickListener onLocationClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        ButterKnife.inject(this);

        setupActionBar();

        this.onLocationClickListener = new OnLocationClickListener(LocationActivity.this);

        this.locationRecyclerView = (RecyclerView) findViewById(R.id.location_list);
        this.searchForPlacesView = (EditText) findViewById(R.id.search_for_places);

        this.locationRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.locationAdapter = new LocationAdapter(getApplicationContext(), LocationActivity.this);
        this.locationRecyclerView.setAdapter(this.locationAdapter);

        this.searchForPlacesView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged -- START" + s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged -- START" + s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged -- START" + s.toString());
                searchPlaces(s.toString());
            }
        });
    }

    //@param query - what the user wants to search for
    public void searchPlaces(String query){
        Log.d(TAG, "searchPlaces -- START");
        try {
            // Create an instance of the Foursquare Search API interface
            Webservice.fetchLocation(locationAdapter, query, Double.valueOf(Constants.MOCK_LAT), Double.valueOf(Constants.MOCK_LNG));
        } catch(Exception e){
            Log.d(TAG, "IOException: " + e.toString());
        }
    }

    private void setupActionBar(){
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowCustomEnabled(true);

        setTitle("");

        ab.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_background)));
        ab.setDisplayHomeAsUpEnabled(Boolean.TRUE);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ab.setElevation(0);
        }

        View customActionBarView = getLayoutInflater().inflate(R.layout.custom_action_bar, null);
        ((TextView)customActionBarView.findViewById(R.id.custom_action_bar_text)).setText("Please choose a destination");
        ab.setCustomView(customActionBarView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
