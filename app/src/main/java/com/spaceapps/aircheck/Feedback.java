package com.spaceapps.aircheck;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Feedback extends AppCompatActivity  {

    @InjectView(R.id.submitfeedback) Button _submitButton;
    @InjectView(R.id.result) TextView _res;
    ArrayList<String> selection= new ArrayList<String>();

    final String URL="http://10.10.11.105:8080/insert_syntom";

    HashMap<String, String> postParams= new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.inject(this);

        addListenerOnButton();
        setToolbar();
        postParams.put("cough","no");
        postParams.put("breathe","no");
        postParams.put("eyes","no");
        postParams.put("mouth","no");
        postParams.put("sneeze","no");
        postParams.put("wheeze","no");
        postParams.put("nasal","no");

        postParams.put("lat","17.4");
        postParams.put("long","10.2");
        postParams.put("date","20161123");
        postParams.put("user","Almadapa");

    }

    public void selectItems( View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())

        {
            case R.id.breathcheck:
                if (checked) {
                    postParams.remove("breathe");
                    postParams.put("breathe","yes");
                } else {
                    postParams.remove("breathe");
                    postParams.put("breathe","no");
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
        String final_fruit_selection = "";
        Iterator it = postParams.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            final_fruit_selection+=pair.getKey() + " = " + pair.getValue();
            it.remove(); // avoids a ConcurrentModificationException
        }
        //_res.setText(final_fruit_selection);
        PostWS post= new PostWS(getApplicationContext(),new AsyncTaskListener<String>() {
            @Override
            public void onProgress(Integer progress) {
            }
            @Override
            public void onInit() {

            }

            @Override
            public void onFinish(String result) {
                _res.setText(result);
            }

            @Override
            public void onCancel() {
            }
        });
        post.feedback(postParams);
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
