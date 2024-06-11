package com.example.categing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

public class DIetMode extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {

    ListView l1;
    public static String video,catg,frompage,vid;
    String[] name,inc,stock,rate,img,details,vpath,videoid,username,userimage;
    String from,to;
    EditText e1,e2;
    ImageButton i1;
    TextView t1;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full sc
        setContentView(R.layout.activity_diet_mode);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



        l1=findViewById(R.id.horizontal_gridview);
        l1.setOnItemClickListener(this);
        e1=findViewById(R.id.from);
        e2=findViewById(R.id.to);
        i1=findViewById(R.id.imageButton2);
        t1=findViewById(R.id.nodata);

        t1.setVisibility(View.GONE);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                from = e1.getText().toString();
                to = e2.getText().toString();
                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse) DIetMode.this;
                String q = "/diet_mode_search?from="+from+"&to="+to;
                q=q.replace(" ","%20");
                JR.execute(q);
            }
        });
//        l1.setOnItemClickListener(this);


        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) DIetMode.this;
        String q = "/diet_mode";
        q=q.replace(" ","%20");
        JR.execute(q);

//        Button b1 = findViewById(R.id.hostel);

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), View_Recipy.class));
//            }
//        });


//        b3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), IPSettings.class));
//            }
//        });

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomview);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.dietmode);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.community:
                        startActivity(new Intent(getApplicationContext(), VIewUsers.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.mihome:
                        startActivity(new Intent(getApplicationContext(), UserHome.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), MYProfile.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.dietmode:
                        startActivity(new Intent(getApplicationContext(), DIetMode.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), Login.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }

        });
    }

    @Override
    public void response(JSONObject jo) {


        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("diet_mode")){
                String status=jo.getString("status");
                Log.d("pearl",status);
                if(status.equalsIgnoreCase("success")){

                    t1.setVisibility(View.GONE);
                    l1.setVisibility(View.VISIBLE);

                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");


                    name=new String[ja1.length()];
                    inc=new String[ja1.length()];
                    details=new String[ja1.length()];
                    vpath=new String[ja1.length()];
                    img=new String[ja1.length()];
                    videoid=new String[ja1.length()];
                    userimage=new String[ja1.length()];
                    username=new String[ja1.length()];



                    for(int i = 0;i<ja1.length();i++)
                    {

                        name[i]=ja1.getJSONObject(i).getString("name");
                        inc[i]=ja1.getJSONObject(i).getString("incredints");
                        details[i]=ja1.getJSONObject(i).getString("additional_dtls");
                        vpath[i]=ja1.getJSONObject(i).getString("video");
                        videoid[i]=ja1.getJSONObject(i).getString("recipy_id");
                        img[i]=ja1.getJSONObject(i).getString("image");
                        userimage[i]=ja1.getJSONObject(i).getString("pic");
                        username[i]=ja1.getJSONObject(i).getString("username");





                    }
//                    CustomUser clist=new CustomUser(this,cat);
//                    l1.setAdapter(clist);


                    CustomUser adapter = new CustomUser(this, name,img,userimage,username);
                    l1.setAdapter(adapter);
//                    ArrayAdapter<String> ar= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,value);
//                    lv1.setAdapter(ar);


                }

                else {

                    t1.setVisibility(View.VISIBLE);
                    l1.setVisibility(View.GONE);

                }
            }
            if(method.equalsIgnoreCase("test_count")){

                String sample = "";

            }

        }catch (Exception e)
        {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        video=vpath[i];
        frompage="diet";
        vid=videoid[i];
        final CharSequence[] items = {"Watch Video","View Ingredients", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(DIetMode.this , R.style.CustomDialogTheme);
        TextView textView = new TextView(getApplicationContext());
        textView.setText("Select an option");
        textView.setPadding(20, 30, 20, 30);
        textView.setTextSize(20F);
        textView.setBackgroundColor(Color.parseColor("#E7EAEA"));
        textView.setTextColor(Color.parseColor("#888888"));
        textView.setGravity(Gravity.CENTER);
        builder.setCustomTitle(textView);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Watch Video")) {

                    JsonReq JR=new JsonReq();
                    JR.json_response=(JsonResponse) DIetMode.this;
                    String q = "/watch_video_withcount?lid="+sh.getString("log_id","")+"&vid="+vid;
                    q=q.replace(" ","%20");
                    JR.execute(q);

                    startActivity(new Intent(getApplicationContext(), VideoPlay3.class));

                }
                else if (items[item].equals("View Ingredients")) {

                    startActivity(new Intent(getApplicationContext(), View_Ingredients3.class));
                }
                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }

            }

        });
        builder.show();
    }
}