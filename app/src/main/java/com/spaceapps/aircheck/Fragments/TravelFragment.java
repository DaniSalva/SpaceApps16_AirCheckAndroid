package com.spaceapps.aircheck.Fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.spaceapps.aircheck.ApiManager;
import com.spaceapps.aircheck.Feedback;
import com.spaceapps.aircheck.JSONObjects.carbonMonoxide.CO;
import com.spaceapps.aircheck.JSONObjects.ozone.O3;
import com.spaceapps.aircheck.JSONObjects.weather.Weather;
import com.spaceapps.aircheck.R;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Autor: Daniel Salvador Urgel
 * Fecha: 23/11/2015
 */

public class TravelFragment extends Fragment {

    private Button btSumbitCity;
    private AutoCompleteTextView acCity;
    private TextView tvTest;
    private Geocoder gc;

    private List<Address> addresses;
    private Location loc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_travel, container,
                false);

        final Context ctx = this.getContext();

        gc = new Geocoder(ctx, Locale.getDefault());

        // btCity = (Button) findViewById(R.id.btCity);
        btSumbitCity = ButterKnife.findById(v,R.id.btSumbitCity);
        acCity = ButterKnife.findById(v,R.id.acCity);
        tvTest = ButterKnife.findById(v,R.id.tvTest);

        acCity.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvTest.setText(acCity.getText().toString());
                if (s.length() > 4) {
                    try {
                        addresses = gc.getFromLocationName(acCity.getText().toString(), 10); // get the found Address Objects

                        ArrayList<String> sAdapter = new ArrayList<String>();
                        for (Address a : addresses) {
                            tvTest.setText(tvTest.getText() + "\n" + a.toString());
                            sAdapter.add(a.getLocality() + ", " + a.getCountryName());
                        }

                        String[] cities = (String[]) sAdapter.toArray(new String[sAdapter.size()]);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx,
                                android.R.layout.simple_dropdown_item_1line, cities);

                        acCity.setAdapter(adapter);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        acCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                //tvTest.setText(String.valueOf(new BigDecimal(String.valueOf(addresses.get(position).getLatitude())).setScale(1, BigDecimal.ROUND_HALF_UP)));

                double lat = new BigDecimal(addresses.get(position).getLatitude()).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                double lon = new BigDecimal(addresses.get(position).getLongitude()).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

                calculeData(lat, lon);
            }
        });


        btSumbitCity.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            tvTest.setText(acCity.getText().toString());
            try {
                List<Address> addresses= gc.getFromLocationName(acCity.getText().toString(), 10); // get the found Address Objects

                ArrayList<String> sAdapter = new ArrayList<String>();
                for (Address a:addresses) {
                    tvTest.setText(tvTest.getText() + "\n" + a.toString());
                    sAdapter.add(a.getLocality() + ", " + a.getCountryName());
                }

                String[] cities = (String[]) sAdapter.toArray(new String[sAdapter.size()]);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx,
                        android.R.layout.simple_dropdown_item_1line, cities);

                acCity.setAdapter(adapter);

            } catch (IOException e) {
                e.printStackTrace();
            }

            }
        });


        return v;
    }


    private void calculeData(final double lat, final double lon) {
        final float[] coValue = new float[1];
        final float[] ozoneValue = new float[1];
        final float weatherValue;
        final float[] no2 = new float[1];
        final float[] so2 = new float[1];
        tvTest.setText("");
        ApiManager.getApiService().getCO(lat, lon, new Callback<CO>() {
            @Override
            public void success(CO co, Response response) {
                coValue[0] = co.getData().get(0).getValue();

                ApiManager.getApiService().getOzone(lat, lon, new Callback<O3>() {
                    @Override
                    public void success(O3 o3, Response response) {
                        ozoneValue[0] = o3.getData();
                        ApiManager.getApiService().getNO2(lat, lon, new Callback<NO2>() {
                            @Override
                            public void success(NO2 no2, Response response) {
                                no2Value[0] = no2.getData();
                                ApiManager.getApiService().getSO2(lat, lon, new Callback<SO2>() {
                                    @Override
                                    public void success(SO2 no2, Response response) {
                                        so2Value[0] = so2.getData();

                                    }

                                    @Override
                                    public void failure(RetrofitError error) { }
                                });
                            }

                            @Override
                            public void failure(RetrofitError error) { }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {}
                });
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });


    }

    /*
    ApiManager.getApiService().getCurrentWeather(lat, lon, new Callback<Weather>() {
                            @Override
                            public void success(Weather weather, Response response) {
                                tvTest.setText("Succes " + weather.getMain());
                            }

                            @Override
                            public void failure(RetrofitError error) {
                                tvTest.setText("Failure ");
                            }
                        });
     */

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
