package com.example.categing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class View_MY_Statistics extends AppCompatActivity implements JsonResponse {

    TextView e1,e2,e3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full sc
        setContentView(R.layout.activity_view_my_statistics);

        e1 = findViewById(R.id.moni);
        e2 = findViewById(R.id.count);
        e3 = findViewById(R.id.amount);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) View_MY_Statistics.this;
        String q = "/view_my_stat?rid=" + MYProfile.vid;
        q = q.replace(" ", "%20");
        JR.execute(q);

    }

    @Override
    public void response(JSONObject jo) {
        try {
            String method = jo.getString("method");
            Log.d("pearl", method);

            if (method.equalsIgnoreCase("view_my_stat")) {

                String status = jo.getString("status");
                if (status.equalsIgnoreCase("success")) {

                   e1.setText("Monetization "+jo.getString("Monetization"));
                   e2.setText("Views Count: "+jo.getString("count"));
                   e3.setText("Received Amount: "+jo.getString("amount"));

                }
                else {
                    e1.setText("Monetization "+jo.getString("Monetization"));
                   e2.setText("Views Count: "+jo.getString("count"));
                   e3.setText("Received Amount: "+jo.getString("amount"));
//
//
                }

            }


        }
        catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}