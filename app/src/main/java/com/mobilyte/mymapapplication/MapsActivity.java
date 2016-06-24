package com.mobilyte.mymapapplication;

import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.VisibleRegion;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnCameraChangeListener, LocationListener {

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1; // 1 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 1 * 1; //
    List<LatLng> latLngs = new ArrayList<>();
    List<LatLng> reverselatLngs = new ArrayList<>();
    LatLng lastKnowLatLng;
    HideOverlayView hideView;
    Location location;
    double latitude, longitude;
    private GoogleMap mMap;
    private List<Marker> visibleMarkers = new ArrayList<>();
    private LocationManager locationManager;
    private boolean isGPSEnabled = false, isNetworkEnabled = false, canGetLocation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
  /*      ((Button) findViewById(R.id.btm)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });*/
        hideView = (HideOverlayView) findViewById(R.id.hideoverlayview);

    }

    void check() {
    /*    LatLngBounds curScreen = mMap.getProjection()
                .getVisibleRegion().latLngBounds;
        System.out.println(curScreen.northeast.longitude);
        System.out.println(curScreen.northeast.latitude);
        System.out.println(curScreen.southwest.latitude);
        System.out.println(curScreen.southwest.longitude);*/
        VisibleRegion vr = mMap.getProjection().getVisibleRegion();
        double left = vr.latLngBounds.southwest.longitude;
        double top = vr.latLngBounds.northeast.latitude;
        double right = vr.latLngBounds.northeast.longitude;
        double bottom = vr.latLngBounds.southwest.latitude;
        PolygonOptions rectOptions = new PolygonOptions();

        rectOptions.add(new LatLng(top, left),
                new LatLng(top, right),
                new LatLng(bottom, right),
                new LatLng(bottom, left)
        );


      /*  latLngs.add(new LatLng(top - 0.00114, left + 0.00006));
        latLngs.add(new LatLng(top - 0.00004, left + 0.00324324));
        latLngs.add(new LatLng(bottom + 0.00114, right - 0.00006));
        latLngs.add(new LatLng(bottom + 0.00004, right - 0.00324324));*/
//        latLngs.add(new LatLng(40.707638, -74.049354));

        //40.703800, -74.059181
//        rectOptions.add(new LatLng(top, left), new LatLng());
        for (int i = reverselatLngs.size() - 1; i >= 0; i--) {
            latLngs.add(reverselatLngs.get(i));
        }
        rectOptions.addHole(latLngs);
        rectOptions.fillColor(Color.RED);
        rectOptions.strokeWidth(1);
        rectOptions.strokeColor(Color.WHITE);


        mMap.addPolygon(rectOptions);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(40.705589, -74.050941);
        //      mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //   mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17.0f));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
 /*       BitmapDescriptor image = BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher);

        GroundOverlayOptions groundOverlay = new GroundOverlayOptions()
                .image(image)
                .position(sydney, 500f)
                .transparency(0.5f);
        mMap.addGroundOverlay(groundOverlay);

       *//* Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(51.5, -0.1), new LatLng(40.7, -74.0))
                .width(5)
                .color(Color.RED));*//*

// Get back the mutable Polygon
        Polygon polygon = mMap.addPolygon(rectOptions);*/

      /*  PolygonOptions rectOptions = new PolygonOptions();

        rectOptions.add(new LatLng(40.703800, -74.059181),
                new LatLng(40.705426, -74.055190),
                new LatLng(40.707638, -74.049354)
             *//*   new LatLng(40.702889, -74.051800),
                new LatLng(40.702010, -74.056993),
                new LatLng(40.703084, -74.059739)*//*);
        rectOptions.fillColor(Color.RED);
        rectOptions.strokeWidth(1);
        rectOptions.strokeColor(Color.WHITE);
        mMap.addPolygon(rectOptions);*/

       /* final List<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng(1, 1));
        latLngs.add(new LatLng(1, 2));
        latLngs.add(new LatLng(2, 2));
        latLngs.add(new LatLng(2, 1));
        latLngs.add(new LatLng(1, 1));*/

/*        mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(0, 0), new LatLng(0, 5), new LatLng(3, 5), new LatLng(3, 0), new LatLng(0, 0))
                .addHole(latLngs)
                .fillColor(Color.BLUE));*/
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
      /*  mMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                CameraPosition.fromLatLngZoom(new LatLng(40.22861, -3.95567), 15)));*/
        mMap.setOnCameraChangeListener(this);
        /*visibleMarkers.add(mMap.addMarker(new MarkerOptions().position(
                new LatLng(40.22861, -3.95567))));
        visibleMarkers.add(mMap.addMarker(new MarkerOptions().position(
                new LatLng(40.22977, -3.95338))));*/
        getLocation();

     /*   mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                *//*latLngs.add(new LatLng(latLng.latitude + 0.0002, latLng.longitude));
                if (lastKnowLatLng == null) {
                    lastKnowLatLng = latLng;
                } else {
                    latLngs.add(new LatLng(latLng.latitude - 0.0002, latLng.longitude));
                    latLngs.add(new LatLng(lastKnowLatLng.latitude - 0.0002, lastKnowLatLng.longitude));
                    lastKnowLatLng = null;
                }*//*
*//*                if (latLngs.size() > 0) {
                    if (latLngs.get(latLngs.size() - 1).latitude > latLng.latitude) {
                        latLngs.add(new LatLng(latLng.latitude - 0.0002, latLng.longitude + 0.0002));
                    } else {
                        latLngs.add(new LatLng(latLng.latitude + 0.0002, latLng.longitude - 0.0002));
                    }
                }else{*//*
                latLngs.add(new LatLng(latLng.latitude + 0.00002, latLng.longitude - 0.00002));
//                }

                reverselatLngs.add(new LatLng(latLng.latitude, latLng.longitude));
            }
        });*/

    }

    @Override
    public void onCameraChange(CameraPosition cameraPosition) {
        /*List<Point> visiblePoints = new ArrayList<>();
        Projection projection = mMap.getProjection();

        for (Marker visibleMarker : visibleMarkers) {
            visiblePoints.add(projection.toScreenLocation(visibleMarker.getPosition()));
        }

        float radius = 150f; // meters
        Point centerPoint = projection.toScreenLocation(cameraPosition.target);
        Point radiusPoint = projection.toScreenLocation(
                SphericalUtil.computeOffset(cameraPosition.target, radius, 90));

        float radiusPx = (float) Math.sqrt(Math.pow(centerPoint.x - radiusPoint.x, 2));

        hideView.reDraw(visiblePoints, radiusPx);*/
    }

    @Override
    public void onLocationChanged(Location location) {
        System.out.println("LOca>>" + location.getLatitude() + "<><>" + location.getLongitude());
        visibleMarkers.add(mMap.addMarker(new MarkerOptions().position(
                new LatLng(location.getLatitude(), location.getLongitude()))));
        List<Point> visiblePoints = new ArrayList<>();
        Projection projection = mMap.getProjection();

        for (Marker visibleMarker : visibleMarkers) {
            visiblePoints.add(projection.toScreenLocation(visibleMarker.getPosition()));
        }
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        float radius = 20f; // meters
        Point centerPoint = projection.toScreenLocation(latLng);
        Point radiusPoint = projection.toScreenLocation(
                SphericalUtil.computeOffset(latLng, radius, 90));

        float radiusPx = (float) Math.sqrt(Math.pow(centerPoint.x - radiusPoint.x, 2));
        System.out.println("RM>>>" + radiusPx);
        hideView.reDraw(visiblePoints, radiusPx);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public Location getLocation() {
        try {
            locationManager = (LocationManager)
                    getSystemService(LOCATION_SERVICE);

// getting GPS status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

// getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
// no network provider is enabled
            } else {
                this.canGetLocation = true;
// First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            //txt1.setText(latitude + ">>>>" + longitude);
                            System.out.println(" network latitude>>>>" + latitude + "network longitude>>>" + longitude);
                        }
                    }
                }
// if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                                //txt2.setText(latitude + ">>>>" + longitude);
                                System.out.println("gps latitude>>>>" + latitude + "gps longitude>>>" + longitude);
                                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(
                                        CameraPosition.fromLatLngZoom(new LatLng(latitude, longitude), 18)));
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }
}
