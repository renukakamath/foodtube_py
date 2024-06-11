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
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class MYProfile extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {

    ListView l1;
    public static String video,catg,frompage,vid;
    String[] name,inc,stock,rate,img,details,vpath,videoid,username,userimage;
    TextView t1;
    ImageView i77;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full sc
        setContentView(R.layout.activity_myprofile);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        l1=findViewById(R.id.horizontal_gridview);
        i77 = findViewById(R.id.pro_pic);
        t1=findViewById(R.id.details);
        ImageButton i1=findViewById(R.id.imageButton);
        l1.setOnItemClickListener(this);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddRecipy.class));
            }
        });


        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) MYProfile.this;
        String q = "/my_profile?lid="+sh.getString("log_id","");
        q=q.replace(" ","%20");
        JR.execute(q);




        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomview);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.profile);

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
            if(method.equalsIgnoreCase("my_profile")){
                String status=jo.getString("status");
                Log.d("pearl",status);
                if(status.equalsIgnoreCase("success")){

                    JSONArray ja2=(JSONArray)jo.getJSONArray("data2");


                    String fname = ja2.getJSONObject(0).getString("fname");
                    String lname = ja2.getJSONObject(0).getString("lname");
                    String phone = ja2.getJSONObject(0).getString("phone");
                    String email = ja2.getJSONObject(0).getString("email");
                    String image = ja2.getJSONObject(0).getString("pic");

                    t1.setText(fname+" "+lname+"\n"+phone+"\n"+email);

                    String pth = "http://"+sh.getString("ip", "")+"/"+image;
                    pth = pth.replace("~", "");

                    Log.d("-------------", pth);
                    Picasso.with(getApplicationContext())
                            .load(pth)
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background).into(i77);




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
                        img[i]=ja1.getJSONObject(i).getString("image");
                        videoid[i]=ja1.getJSONObject(i).getString("recipy_id");
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
                    Toast.makeText(getApplicationContext(), "No Result found!", Toast.LENGTH_SHORT).show();

                }
            }
            if(method.equalsIgnoreCase("delete_receipy")){
                Toast.makeText(getApplicationContext(), "Receipy deleted!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MYProfile.class));

            }

        }catch (Exception e)
        {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        video=vpath[i];
        frompage="profile";
        vid=videoid[i];
        final CharSequence[] items = {"Watch Video","View Ingredients","Statistics","Edit","Delete", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(MYProfile.this , R.style.CustomDialogTheme);
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

                    startActivity(new Intent(getApplicationContext(), VideoPlay2.class));

                }
                else if (items[item].equals("View Ingredients")) {

                    startActivity(new Intent(getApplicationContext(), View_Ingredients2.class));
                } else if (items[item].equals("Statistics")) {

                    startActivity(new Intent(getApplicationContext(), View_MY_Statistics.class));
                }
                else if (items[item].equals("Edit")) {

                    startActivity(new Intent(getApplicationContext(), Edit_recipe.class));
                }
                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
                else if (items[item].equals("Delete")) {
                    JsonReq JR=new JsonReq();
                    JR.json_response=(JsonResponse) MYProfile.this;
                    String q = "/delete_receipy?rid="+vid;
                    q=q.replace(" ","%20");
                    JR.execute(q);
                }

            }

        });
        builder.show();
    }
}