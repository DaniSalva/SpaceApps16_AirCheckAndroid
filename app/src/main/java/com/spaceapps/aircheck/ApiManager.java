package com.spaceapps.aircheck;

import com.spaceapps.aircheck.JSONObjects.carbonMonoxide.CO;
import com.spaceapps.aircheck.JSONObjects.no2.NitrousOxide;
import com.spaceapps.aircheck.JSONObjects.ozone.O3;
import com.spaceapps.aircheck.JSONObjects.so2.SO2;
import com.spaceapps.aircheck.JSONObjects.weather.List;
import com.spaceapps.aircheck.JSONObjects.weather.Weather;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Manuel on 23/04/2016.
 */
public class ApiManager {

        private static final String API_URL = "http://api.openweathermap.org";
        private static final String API_CODE = "570b33d8c0275b0e221296d7b6190032";

        private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
                .setEndpoint(API_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        private static final APIData API_SERVICE = REST_ADAPTER.create(APIData.class);

        public static APIData getApiService() {
            return API_SERVICE;
        }

        public interface APIData {

            /* -- EXAMPLE CODE --
            ApiManager.getApiService().getCO(41.6, -0.9, new Callback<CO>() {
                @Override
                public void success(CO co, Response response) {
                    Log.d("CO", co.getData().get(0).getPrecision()
                            + " "  + co.getData().get(0). getValue()
                            + " "  + co.getData().get(0). getPressure());
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d("CO", "ERROR" + error.getMessage());
                }
            });*/

            @GET("/data/2.5/weather?&appid="+API_CODE)
            public void getCurrentWeather(@Query("lat") double lat, @Query("lon") double lon, Callback<Weather> callback);

            // WARNING: Only one decimal value in LAT and LON
            @GET("/pollution/v1/o3/{lat},{lon}/current.json?appid="+API_CODE)
            public void getOzone(@Path("lat") double lat, @Path("lon") double lon, Callback<O3> callback);

            // WARNING: Up to five decimal (it approximates the distance > number of decimals better accurazy)
            @GET("/pollution/v1/co/{lat},{lon}/current.json?appid="+API_CODE)
            public void getCO(@Path("lat") double lat, @Path("lon") double lon, Callback<CO> callback);

            // http://api.openweathermap.org/pollution/v1/no2/0.0,10.0/current.json?appid=570b33d8c0275b0e221296d7b6190032
            @GET("/pollution/v1/no2/{lat},{lon}/current.json?appid="+API_CODE)
            public void getNO2(@Path("lat") double lat, @Path("lon") double lon, Callback<NitrousOxide> callback);

            // http://api.openweathermap.org/pollution/v1/no2/0.0,10.0/current.json?appid=570b33d8c0275b0e221296d7b6190032
            @GET("/pollution/v1/so2/{lat},{lon}/current.json?appid="+API_CODE)
            public void getSO2(@Path("lat") double lat, @Path("lon") double lon, Callback<SO2> callback);

    }
}
