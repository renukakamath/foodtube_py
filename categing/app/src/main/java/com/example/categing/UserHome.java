package com.example.categing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserHome extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {

    ListView l1;
    public static String video,catg,frompage,vid,videoease;
    String[] name,inc,stock,rate,img,details,vpath,videoid,username,userimage;
    String[] videopath;
    String search;
    EditText e1;
    SharedPreferences sh;


    VideoView vv;
    ProgressDialog progressDialog;
    MediaController mediaController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full sc
        setContentView(R.layout.activity_user_home);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());




        l1=findViewById(R.id.horizontal_gridview);
        l1.setOnItemClickListener(this);
        e1=findViewById(R.id.search);

        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                search = e1.getText().toString();
                JsonReq JR=new JsonReq();
                JR.json_response=(JsonResponse) UserHome.this;
                String q = "/view_recipy_search?search="+search;
                q=q.replace(" ","%20");
                JR.execute(q);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//        l1.setOnItemClickListener(this);


        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) UserHome.this;
        String q = "/view_recipy";
        q=q.replace(" ","%20");
        JR.execute(q);


        JsonReq JS=new JsonReq();
        JS.json_response=(JsonResponse) UserHome.this;
        String q1= "/view_videoplay";
        q1=q1.replace(" ","%20");
        JS.execute(q1);




        vv = (VideoView) findViewById(R.id.vv_child_vdo);


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
        bottomNavigationView.setSelectedItemId(R.id.mihome);

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


    public void playvideo(String videopath) {
        Log.e("entered", "playvide");
        Log.e("path is", "" + videopath);
        try {
            progressDialog = ProgressDialog.show(UserHome.this, "",
                    "Buffering video...", false);
            progressDialog.setCancelable(true);
            getWindow().setFormat(PixelFormat.TRANSLUCENT);

            mediaController = new MediaController(UserHome.this);

            Uri video = Uri.parse(videopath);
            vv.setMediaController(mediaController);
            vv.setVideoURI(video);

            vv.start();

//	        vv.setOnPreparedListener(new OnPreparedListener() {
//
//	            public void onPrepared(MediaPlayer mp) {
//	                progressDialog.dismiss();
//	                vv.start();
//	            }
//	        });

        } catch (Exception e) {
            progressDialog.dismiss();
            System.out.println("Video Play Error :" + e.getMessage());
        }
    }





    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("view_recipy")){
                String status=jo.getString("status");
                Log.d("pearl",status);
                if(status.equalsIgnoreCase("success")){

                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");


                    name=new String[ja1.length()];
                    inc=new String[ja1.length()];
                    details=new String[ja1.length()];
                    vpath=new String[ja1.length()];
                    videoid=new String[ja1.length()];
                    img=new String[ja1.length()];
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

//                else {
////                    Toast.makeText(getApplicationContext(), "No Result found!", Toast.LENGTH_LONG).show();
//
//                }
            }

            if(method.equalsIgnoreCase("view_videoplay")){
                String status=jo.getString("status");
                Log.d("pearl",status);
                if(status.equalsIgnoreCase("success")){

                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
                    videopath=new String[ja1.length()];
                    for(int i = 0;i<ja1.length();i++)
                    {

                        videopath[i]=ja1.getJSONObject(i).getString("addpath");
                        videoease=videopath[i];

                        try {

                            String path = "http://" + IPSettings.text + "/" + UserHome.videoease;
                            path = path.replace(" ", "%20");
//			Toast.makeText(getApplicationContext(), path, Toast.LENGTH_LONG).show();
//			Uri path_uri = Uri.parse(path);
//			vv.setVideoURI(path_uri);
//			vv.start();
                            playvideo(path);

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Exc : " + e, Toast.LENGTH_LONG).show();
                        }
                    }
//                    CustomUser clist=new CustomUser(this,cat);
//                    l1.setAdapter(clist);

//
//                    CustomUser adapter = new CustomUser(this, name,img,userimage,username);
//                    l1.setAdapter(adapter);
//                    ArrayAdapter<String> ar= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,value);
//                    lv1.setAdapter(ar);


                }

//                else {
////                    Toast.makeText(getApplicationContext(), "No Result found!", Toast.LENGTH_LONG).show();
//
//                }
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
        vid=videoid[i];
        frompage="home";
        final CharSequence[] items = {"Watch Video","View Ingredients", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(UserHome.this , R.style.CustomDialogTheme);
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
                    JR.json_response=(JsonResponse) UserHome.this;
                    String q = "/watch_video_withcount?lid="+sh.getString("log_id","")+"&vid="+vid;
                    q=q.replace(" ","%20");
                    JR.execute(q);

                    startActivity(new Intent(getApplicationContext(), VideoPlay.class));

                }
                else if (items[item].equals("View Ingredients")) {

                    startActivity(new Intent(getApplicationContext(), View_Ingredients.class));
                }
                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }

            }

        });
        builder.show();

    }

    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(), UserHome.class);
        startActivity(b);
    }

}