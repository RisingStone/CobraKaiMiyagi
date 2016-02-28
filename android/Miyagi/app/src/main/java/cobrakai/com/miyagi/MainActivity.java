package cobrakai.com.miyagi;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.ButterKnife;
import cobrakai.com.miyagi.network.Auth;
import cobrakai.com.miyagi.network.Webservice;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import butterknife.InjectView;
import cobrakai.com.miyagi.service.EnteringGeoFenceService;
import io.oauth.OAuthCallback;
import io.oauth.OAuthData;
import cobrakai.com.miyagi.utils.Constants;
import cobrakai.com.miyagi.utils.Helper;
import cobrakai.com.miyagi.views.ColorStrobeActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    @InjectView(R.id.map_view) MapView miyagiMap;
    @InjectView(R.id.request_ride_btn) Button miyagiButton;
    @InjectView(R.id.miyagi_fab) FloatingActionButton miyagiFAB;

    ArrayList<String> miyagiQuotes;

    private static BitmapDescriptor markerIcon;
    private static BitmapDescriptor hubIcon;
    private static GoogleMap map;

    private int miyagiCycleCouter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        miyagiQuotes = new ArrayList<>();
        setupMiyagiQuotes();

//        Auth.getUberAuthToken(this, new OAuthCallback() {
//            @Override
//            public void onFinished(OAuthData data) {
//                Log.d(TAG, "Toke: " + data.token);
//            }
//        });

        setupMiyagiMap(savedInstanceState);

        markerIcon = BitmapDescriptorFactory.fromResource(R.drawable.car);
        hubIcon = BitmapDescriptorFactory.fromResource(R.drawable.geofence);
        Helper.setupActionBar(this, getResources().getString(R.string.app_name));
        setupUI();
        setupListeners();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Log.d(TAG, "onNavigationItemSelected -- START");
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_flag_down) {
            // Handle the camera action
        } else if (id == R.id.nav_visual_indicate) {
            Log.d(TAG, "nav_visual_indicate -- START");
            Intent intent = new Intent(this, ColorStrobeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupMiyagiMap(Bundle savedInstanceState) {
        miyagiMap
                .getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                try {
                    MainActivity.map = map;
                    map.getUiSettings().setMyLocationButtonEnabled(true);
                    map.setMyLocationEnabled(Boolean.TRUE);
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

//                    Webservice.fetchDriverInitialLocation(map, hubIcon, Constants.LYFT_ACCESS_TOKEN);
                    Webservice.fetchMockHubLocation(map, hubIcon, Constants.LYFT_ACCESS_TOKEN);
                    Webservice.fetchKreeseLocation(map, markerIcon, MainActivity.this);
                    Webservice.testMyLocalHost();

                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                            new LatLng(Double.valueOf(Constants.MOCK_LAT), Double.valueOf(Constants.MOCK_LNG)), 14);
                    map.animateCamera(cameraUpdate);

                    map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {

                            Intent geofenceServiceIntent = new Intent(MainActivity.this, EnteringGeoFenceService.class);
                            startService(geofenceServiceIntent);

                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                    Uri.parse("google.navigation:q=" + marker.getTitle()));
                            startActivity(intent);
                        }
                    });
                } catch (SecurityException e){
                    Log.e(TAG, "SecurityException -- START -- " + e.toString());
                }
            }
        });

        miyagiMap.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        miyagiMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        miyagiMap.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        miyagiMap.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        miyagiMap.onLowMemory();
    }

    private void setupUI(){
        Typeface face=Typeface.createFromAsset(getAssets(),
                "fonts/Roboto-Bold.ttf");

        miyagiButton.setTypeface(face);
    }

    private void setupListeners(){
        miyagiFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, miyagiQuotes.get(miyagiCycleCouter), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                if(miyagiCycleCouter >= miyagiQuotes.size()-1){
                    miyagiCycleCouter = 0;
                } else {
                    miyagiCycleCouter++;
                }
                Webservice.fetchDriverUpdateLocation(MainActivity.map, markerIcon, Constants.LYFT_ACCESS_TOKEN);
            }
        });
    }

    private void setupMiyagiQuotes(){
        miyagiQuotes.add("Man who catch fly with chopstick, accomplish anything");
        miyagiQuotes.add("First learn stand, then learn fly. Nature rule Daniel son, not mine");
        miyagiQuotes.add("Never put passion in front of principle, even if you win, you’ll lose");
        miyagiQuotes.add("It’s ok to lose to opponent. It’s never okay to lose to fear");
    }
}