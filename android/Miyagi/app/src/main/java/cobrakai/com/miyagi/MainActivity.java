package cobrakai.com.miyagi;

import android.content.Intent;
import android.graphics.Typeface;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import butterknife.InjectView;
import cobrakai.com.miyagi.utils.Constants;
import cobrakai.com.miyagi.utils.Helper;
import cobrakai.com.miyagi.views.ColorStrobeActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    @InjectView(R.id.map_view) MapView miyagiMap;
    @InjectView(R.id.request_ride_btn) Button miyagiButton;

    private static BitmapDescriptor markerIcon;

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

        Webservice.fetchGithub();
        Auth.getAuthToken(this);
        setupMiyagiMap(savedInstanceState);

        markerIcon = BitmapDescriptorFactory.fromResource(R.drawable.car);
        Helper.setupActionBar(this, getResources().getString(R.string.app_name));
        setupUI();
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
                    map.getUiSettings().setMyLocationButtonEnabled(true);
                    map.setMyLocationEnabled(Boolean.TRUE);
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

                    Webservice.fetchDriverLocation(map, markerIcon, Constants.LYFT_ACCESS_TOKEN_SANDBOX);
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
}
