package com.spaceapps.aircheck;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class PostWS {

    private AccessREST accessWS;
    private Context appContext;
    private AsyncTaskListener authListener;

    public PostWS(Context appContext, AsyncTaskListener authListener) {
        this.appContext=appContext;
        this.authListener=authListener;
    }

    public String feedback (HashMap<String,String> parameters){
        accessWS= new AccessREST(appContext, new AsyncTaskListener<String>() {
            @Override
            public void onInit() {

            }

            @Override
            public void onProgress(Integer progress) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFinish(String result) {
                authListener.onFinish("OK");

            }
        });

        accessWS.execute(parameters);


        return null;
    }
}
