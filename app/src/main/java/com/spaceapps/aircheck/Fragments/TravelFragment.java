package com.spaceapps.aircheck.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.spaceapps.aircheck.ApiManager;
import com.spaceapps.aircheck.JSONObjects.carbonMonoxide.CO;
import com.spaceapps.aircheck.JSONObjects.no2.NitrousOxide;
import com.spaceapps.aircheck.JSONObjects.ozone.O3;
import com.spaceapps.aircheck.JSONObjects.so2.SO2;
import com.spaceapps.aircheck.JSONObjects.station.Hub;
import com.spaceapps.aircheck.JSONObjects.weather.Forecast;
import com.spaceapps.aircheck.R;

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
    private Button btCPos;
    private AutoCompleteTextView acCity;
    private TextView tvTest;
    private Geocoder gc;
    private ImageView imImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_travel, container,
                false);

        final Context ctx = this.getContext();

        gc = new Geocoder(ctx, Locale.getDefault());

        // btCity = (Button) findViewById(R.id.btCity);
        btSumbitCity = ButterKnife.findById(v, R.id.btSumbitCity);
        btCPos = ButterKnife.findById(v, R.id.btCPos);
        acCity = ButterKnife.findById(v, R.id.acCity);
        tvTest = ButterKnife.findById(v, R.id.tvTest);
        imImg = ButterKnife.findById(v, R.id.imImg);

        /*
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
*/

        btSumbitCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (acCity.getText().length() != 0) {
                    tvTest.setText(acCity.getText().toString());
                    try {
                        List<Address> addresses = gc.getFromLocationName(acCity.getText().toString(), 10); // get the found Address Objects

                        acCity.setText(addresses.get(0).getLocality() + ", " + addresses.get(0).getCountryName());

                    /*ArrayList<String> sAdapter = new ArrayList<String>();
                    for (Address a:addresses) {
                        tvTest.setText(tvTest.getText() + "\n" + a.toString());
                        sAdapter.add(a.getLocality() + ", " + a.getCountryName());
                    }*/

                    /*String[] cities = (String[]) sAdapter.toArray(new String[sAdapter.size()]);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ctx,
                            android.R.layout.simple_dropdown_item_1line, cities);

                    acCity.setAdapter(adapter);*/

                    calculeData(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());

                    //int goodness = getGoodness(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                    //setImage(goodness);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btCPos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) getActivity().getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String provider = locationManager.getBestProvider(criteria, true);
                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Location location = locationManager.getLastKnownLocation(provider);
                tvTest.setText(location.getLatitude() + " " + location.getLongitude());

                try {
                    List<Address> addresses = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    acCity.setText(addresses.get(0).getLocality() + ", " + addresses.get(0).getCountryName());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //calculeData(location.getLatitude(), location.getLongitude());
                int goodness = getGoodness(location.getLatitude(), location.getLongitude());
                setImage(goodness);
            }
        });


        return v;
    }

    private void setImage(int index) {
        tvTest.setText("Air quality: " + index + "/10\n");
        switch (index) {
            case 1:
                imImg.setImageResource(R.drawable.emo1);
                break;
            case 2:
                imImg.setImageResource(R.drawable.emo2);
                break;
            case 3:
                imImg.setImageResource(R.drawable.emo3);
                break;
            case 4:
                imImg.setImageResource(R.drawable.emo4);
                break;
            case 5:
                imImg.setImageResource(R.drawable.emo5);
                break;
            case 6:
                imImg.setImageResource(R.drawable.emo6);
                break;
            case 7:
                imImg.setImageResource(R.drawable.emo7);
                break;
            case 8:
                imImg.setImageResource(R.drawable.emo8);
                break;
            case 9:
                imImg.setImageResource(R.drawable.emo9);
                break;
            case 10:
                imImg.setImageResource(R.drawable.emo10);
                break;
        }
    }
    private int getGoodness(double latitude, double longitude) {
        final double lat = new BigDecimal(latitude).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        final double lon = new BigDecimal(longitude).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();

        /*ApiManager.getApiService().getCO(lat, lon, new Callback<CO>() {

        });*/
            return 1;
    }

    private void calculeData(final double latitude, final double longitude) {
        final double[] lat = new double[2];
        final double[] lon = new double[2];
        lon[0] = new BigDecimal(longitude).setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
        lat[0] = new BigDecimal(latitude).setScale(1, BigDecimal.ROUND_DOWN).doubleValue();

        final float[] coValue = new float[1];
        final float[] ozoneValue = new float[1];
        final float weatherValue;
        final double[] no2Value = new double[1];
        final double[] so2Value = new double[1];
        tvTest.setText("");
        
        ApiManager.getApiService().getStation(lat[0], lon[0], new Callback<ArrayList<Hub>>() {
            @Override
            public void success(ArrayList<Hub> hubs, Response response) {
                lon[1] = hubs.get(0).getStation().getCoord().getLon();
                lat[1] = hubs.get(0).getStation().getCoord().getLat();
                lon[1] = new BigDecimal(lon[1]).setScale(1, BigDecimal.ROUND_DOWN).doubleValue();
                lat[1] = new BigDecimal(lat[1]).setScale(1, BigDecimal.ROUND_DOWN).doubleValue();

                ApiManager.getApiService().getCO(lat[1], lon[1], new Callback<CO>() {
                    @Override
                    public void success(CO co, Response response) {
                        coValue[0] = co.getData().get(0).getValue();
                        ApiManager.getApiService().getOzone(lat[1], lon[1], new Callback<O3>() {
                            @Override
                            public void success(O3 o3, Response response) {
                                ozoneValue[0] = o3.getData();
                                tvTest.setText("ozoneValue " + ozoneValue[0] + "\n");
                                ApiManager.getApiService().getNO2(lat[1], lon[1], new Callback<NitrousOxide>() {
                                    @Override
                                    public void success(NitrousOxide no2, Response response) {
                                        no2Value[0] = no2.getData().getNo2().getValue();
                                        tvTest.setText(tvTest.getText() + "no2Value " + no2Value[0] + "\n");
                                        ApiManager.getApiService().getSO2(lat[1], lon[1], new Callback<SO2>() {
                                            @Override
                                            public void success(SO2 so2, Response response) {
                                                so2Value[0] = so2.getData().get(0).getValue();
                                                tvTest.setText(tvTest.getText() + "so2Value " + so2Value[0] + "\n");

                                                ApiManager.getApiService().getCurrentWeather(lat[1], lon[1], new Callback<Forecast>() {
                                                    @Override
                                                    public void success(Forecast forecast, Response response) {
                                                        tvTest.setText(tvTest.getText() + "Succes " + forecast.getWeather().get(0).getMain());
                                                    }

                                                    @Override
                                                    public void failure(RetrofitError error) {
                                                        tvTest.setText("Failure ");
                                                    }
                                                });
                                            }

                                            @Override
                                            public void failure(RetrofitError error) {
                                                tvTest.setText("Failure so2" + error.getMessage());
                                            }
                                        });
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                    }
                                });
                            }

                            @Override
                            public void failure(RetrofitError error) {
                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
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