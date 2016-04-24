package com.spaceapps.aircheck;

import com.spaceapps.aircheck.GetMarkersObjects.GetRequest;
import com.spaceapps.aircheck.JSONObjects.Risk;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
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

        @POST("/insert_syntom")
        public void sendFeedback(@Query("lat") String lat,
                                 @Query("long") String lon,
                                 @Query("user") String user,
                                 @Query("date") String date,
                                 @Query("breath") String breath,
                                 @Query("cough") String cough,
                                 @Query("wheeze") String wheeze,
                                 @Query("eyes") String eyes,
                                 @Query("mouth") String mouth,
                                 @Query("nasal") String nasal,
                                 @Query("sneeze") String sneeze,
                                 Callback<String> callback);

        @GET("/risk_value")
        public void getRisk(@Query("latitude") double lat,
                            @Query("longitude") double lon
                            , Callback<Risk> callback);

    }
}
