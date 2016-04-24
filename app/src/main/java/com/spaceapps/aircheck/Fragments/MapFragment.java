package com.spaceapps.aircheck.Fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.spaceapps.aircheck.Feedback;
import com.spaceapps.aircheck.GetMarkersObjects.GetRequest;
import com.spaceapps.aircheck.R;
import com.spaceapps.aircheck.ServerManager;


import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Autor: Daniel Salvador Urgel
 * Fecha: 23/11/2015
 */

public class MapFragment extends Fragment implements LocationListener {

    private Button _buttonDist;

    private DiscreteSeekBar _seekBar;

    private MapView mMapView;
    private GoogleMap googleMap;
    private MarkerOptions marker;
    private Location current;
    private final LatLng UPV = new LatLng(39.481106, -0.340987);

    final static String URL ="http://40.68.44.128:8080/close_users";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_map, container,
                false);

        FloatingActionButton fab = ButterKnife.findById(v,R.id.fab);
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#a8896c")));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Feedback.class);
                Log.d("DBG",""+googleMap.getMyLocation().getLatitude());
                Log.d("DBG",""+googleMap.getMyLocation().getLongitude());
                if(current!=null){
                    i.putExtra("Lat",""+current.getLatitude());
                    i.putExtra("Long",""+current.getLongitude());
                }

                startActivity(i);
            }
        });

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity().getBaseContext());

        // Showing status
        if(status!= ConnectionResult.SUCCESS){ // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, getActivity(), requestCode);
            dialog.show();

        }else {
            mMapView = (MapView) v.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);
            mMapView.onResume();// needed to get the map to display immediately

            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }

            googleMap = mMapView.getMap();

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(41.684691, -0.884999)).zoom(12).build();
            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

            // Enabling MyLocation Layer of Google Map
            googleMap.setMyLocationEnabled(true);

            Location locurr= googleMap.getMyLocation();

            if(locurr!=null){
                cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(locurr.getLatitude(),locurr.getLongitude())).zoom(12).build();
                googleMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(cameraPosition));
            }

            googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                public View getInfoWindow(Marker arg0) {
                    View v = getActivity().getLayoutInflater().inflate(R.layout.custom_infowindow, null);
                    return v;
                }

                public View getInfoContents(Marker arg0) {

                    //View v = getLayoutInflater().inflate(R.layout.custom_infowindow, null);

                    return null;

                }
            });

            // Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();

            if(googleMap.getMyLocation()!=null){
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom( new LatLng(googleMap.getMyLocation().getLatitude(),
                        googleMap.getMyLocation().getLongitude()),15));
            }

            // Getting the name of the best provider
            String provider = locationManager.getBestProvider(criteria, true);
            if (locationManager != null) {
                if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    locationManager.removeUpdates(this);
                }
            }
            // Getting Current Location
            Location location = locationManager.getLastKnownLocation(provider);

            if(location!=null){
                onLocationChanged(location);
            }
            locationManager.requestLocationUpdates(provider, 10000, 100, this);

            final LatLngBounds.Builder builder = new LatLngBounds.Builder();

            googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

                @Override
                public void onCameraChange(CameraPosition arg0) {
                    // Move camera.
                    //googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 10));
                    // Remove listener to prevent position reset on camera move.
                    //googleMap.setOnCameraChangeListener(null);

                    Log.d("DBG","MOVE MAP");
                    //new HttpAsyncTask().execute(""+googleMap.getCameraPosition().target.latitude,
                    //        ""+googleMap.getCameraPosition().target.longitude,""+10);
                    addMarkersToMap();
                }

            });
        }

        // Perform any camera updates here
        return v;
    }

    private void addMarkersToMap() {

        ServerManager.getApiService().getUsersFeedback(46.21, -0.9, 1, new Callback<ArrayList<GetRequest>>() {
            @Override
            public void success(ArrayList<GetRequest> getRequests, Response response) {
                Log.d("DBG","adsad");
                for (int i = 0; i < getRequests.size(); i++) {
                    Log.d("DBG","marker: "+getRequests.get(i).getUser()+" "+getRequests.get(i).getLoc().getCoordinates().get(1)
                            +" "+getRequests.get(i).getLoc().getCoordinates().get(0));
                    LatLng ll = new LatLng(getRequests.get(i).getLoc().getCoordinates().get(1),
                            getRequests.get(i).getLoc().getCoordinates().get(0));
                    BitmapDescriptor bitmapMarker;
                    bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);

                    switch (getRequests.get(i).getRisk().getValue()) {
                        case 0:
                            bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                            Log.i("DBG", "RED");
                            break;
                        case 1:
                            bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                            Log.i("DBG", "GREEN");
                            break;
                        case 2:
                            bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                            Log.i("DBG", "ORANGE");
                            break;
                        case 3:
                            bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN);
                            Log.i("DBG", "RED");
                            break;
                        case 4:
                            bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);
                            Log.i("DBG", "GREEN");
                            break;
                        case 5:
                            bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);
                            Log.i("DBG", "ORANGE");
                            break;
                        case 6:
                            bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW);
                            Log.i("DBG", "RED");
                            break;
                        case 7:
                            bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
                            Log.i("DBG", "GREEN");
                            break;
                        case 8:
                            bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
                            Log.i("DBG", "ORANGE");
                            break;
                        case 9:
                            bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED);
                            Log.i("DBG", "RED");
                            break;
                        default:
                            bitmapMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
                            Log.i("DBG", "DEFAULT");
                            break;
                    }
                    googleMap.addMarker(new MarkerOptions().position(ll).title(getRequests.get(i).getUser())
                            .snippet(getRequests.get(i).toString()).icon(bitmapMarker));
                    //Marker mark = new Marker

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("DBG",error.toString());

            }
        });
    }
    public void moveCamera(View view) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(UPV));
    }


    @Override
    public void onLocationChanged(Location location) {
        Log.d("DBG","LOC");
        // Getting latitude of the current location
        double latitude = location.getLatitude();

        // Getting longitude of the current location
        double longitude = location.getLongitude();

        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);

        // Showing the current location in Google Map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        // Zoom in the Google Map
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        current=location;
    }

    public Location getLatestLocation(){
        return current;
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
    @Override
    public void onResume() {
        super.onResume();
       mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0],urls[1],urls[2]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getActivity().getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            Log.d("DBG",result);
        }
    }
    public static String GET(String lat,String longi,String rad){
        InputStream inputStream = null;
        String result = "";
        String u= URL+"?latitude="+lat+"&longitude="+longi+"&radius="+rad;
        URL ur = null;
        String response="";
        try {
            ur = new URL(u);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {

            HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");

            InputStream in = conn.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            response = convertInputStreamToString(in);
            Log.d("DBG","response: "+response);
            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                System.out.print(current);
            }
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }


        return response;
    }
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}
