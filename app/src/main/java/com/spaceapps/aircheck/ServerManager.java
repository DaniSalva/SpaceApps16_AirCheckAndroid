package com.spaceapps.aircheck;

import com.spaceapps.aircheck.GetMarkersObjects.GetRequest;
import com.spaceapps.aircheck.JSONObjects.carbonMonoxide.CO;
import com.spaceapps.aircheck.JSONObjects.ozone.O3;
import com.spaceapps.aircheck.JSONObjects.weather.List;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Manuel on 23/04/2016.
 */
public class ServerManager {

    private static final String API_URL = "http://40.68.44.128:8080";

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(API_URL)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

    private static final APIData API_SERVICE = REST_ADAPTER.create(APIData.class);

    public static APIData getApiService() {
        return API_SERVICE;
    }

    public interface APIData {

        @GET("/close_users")
        public void getUsersFeedback(@Query("latitude") double lat,
                                     @Query("longitude") double lon,
                                     @Query("radius") double rad, Callback<ArrayList<GetRequest>> callback);

        @FormUrlEncoded
        @POST("/insert_syntom")
        public void sendFeedback(@Field("lat") String lat,
                                 @Field("long") String lon,
                                 @Field("user") String user,
                                 @Field("date") String date,
                                 @Field("breath") String breath,
                                 @Field("cough") String cough,
                                 @Field("wheeze") String wheeze,
                                 @Field("eyes") String eyes,
                                 @Field("mouth") String mouth,
                                 @Field("nasal") String nasal,
                                 @Field("sneeze") String sneeze, Callback<String> callback);


    }
}
