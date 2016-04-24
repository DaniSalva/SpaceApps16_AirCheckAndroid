package com.spaceapps.aircheck;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Feedback extends AppCompatActivity  {

    @InjectView(R.id.submitfeedback) Button _submitButton;
    @InjectView(R.id.result) TextView _res;
    ArrayList<String> selection= new ArrayList<String>();

    final String URL="http://40.68.44.128:8080/insert_syntom";

    public HashMap<String, String> postParams= new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        intent.getStringExtra("Lat");
        intent.getStringExtra("Long");
        addListenerOnButton();
        setToolbar();

        fillHashMap();

    }

    public void fillHashMap(){
        Random r = new Random();
        double randomlat = 41.65 + (41.6559 - 41.65) * r.nextDouble();
        double randomlong = -0.8800 + (-0.8826 - -0.8806) * r.nextDouble();

        postParams.put("lat",""+randomlat);
        postParams.put("long",""+randomlong);
        postParams.put("date","20161123");
        postParams.put("user","Almadapa");

        postParams.put("cough","no");
        postParams.put("breath","no");
        postParams.put("eyes","no");
        postParams.put("mouth","no");
        postParams.put("sneeze","no");
        postParams.put("wheeze","no");
        postParams.put("nasal","no");

    }
    public void selectItems( View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())

        {
            case R.id.breathcheck:
                if (checked) {
                    postParams.remove("breath");
                    postParams.put("breathe","yes");
                } else {
                    postParams.remove("breath");
                    postParams.put("breath","no");
                }
                break;

            case R.id.coughcheck:
                if (checked) {
                    postParams.remove("cough");
                    postParams.put("cough","yes");
                } else {
                    postParams.remove("cough");
                    postParams.put("cough","no");
                }
                break;

            case R.id.whezcheck:
                if (checked) {
                    postParams.remove("wheeze");
                    postParams.put("wheeze","yes");
                } else {
                    postParams.remove("wheeze");
                    postParams.put("wheeze","no");
                }
                break;
            case R.id.eyescheck:
                if (checked) {
                    postParams.remove("eyes");
                    postParams.put("eyes","yes");
                } else {
                    postParams.remove("eyes");
                    postParams.put("eyes","no");
                }
                break;
            case R.id.mouthcheck:
                if (checked) {
                    postParams.remove("mouth");
                    postParams.put("mouth","yes");
                } else {
                    postParams.remove("mouth");
                    postParams.put("mouth","no");
                }
                break;
            case R.id.nasalcheck:
                if (checked) {
                    postParams.remove("nasal");
                    postParams.put("nasal","yes");
                } else {
                    postParams.remove("nasal");
                    postParams.put("nasal","no");
                }
                break;
            case R.id.sneezecheck:
                if (checked) {
                    postParams.remove("sneeze");
                    postParams.put("sneeze","yes");
                } else {
                    postParams.remove("sneeze");
                    postParams.put("sneeze","no");
                }
                break;
        }
    }
    public void FinalSelection( View view) {
        fillHashMap();
        //HashMap<String,String> map = postParams;

        ServerManager.getApiService().sendFeedback(postParams.get("latitude"),postParams.get("longitude"),postParams.get("user"),postParams.get("date"),
                postParams.get("breathe"),postParams.get("cough"),postParams.get("wheeze"),postParams.get("eyes"),postParams.get("mouth"),
                postParams.get("nasal"),postParams.get("sneeze"));

        /*final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando...");

        SendSyntomWS post= new SendSyntomWS(getApplicationContext(),new AsyncTaskListener<String>() {
            @Override
            public void onProgress(Integer progress) {
            }
            @Override
            public void onInit() {
                progressDialog.show();
            }

            @Override
            public void onFinish(String result) {
                _res.setText(result);
                finish();
            }

            @Override
            public void onCancel() {
            }
        });
        post.feedback(map);*/
    }


    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarC);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setHomeAsUpIndicator(R.drawable.ic_action_arrow_left);
            ab.setDisplayShowHomeEnabled(true);
        }
    }
    // get the selected dropdown list value
    public void addListenerOnButton() {
        _submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FinalSelection(v);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onRestart() {
        super.onRestart();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
