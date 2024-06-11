package com.example.categing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class View_Recipy extends AppCompatActivity  implements JsonResponse{

    ListView l1;
    public static String cid,catg;
    String[] name,inc,stock,rate,img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full sc
        setContentView(R.layout.activity_view_recipy);

        l1=findViewById(R.id.horizontal_gridview);
//        l1.setOnItemClickListener(this);


        JsonReq JR=new JsonReq();
        JR.json_response=(JsonResponse) View_Recipy.this;
        String q = "/view_recipy";
        q=q.replace(" ","%20");
        JR.execute(q);
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
                    stock=new String[ja1.length()];
                    rate=new String[ja1.length()];
                    img=new String[ja1.length()];


                    for(int i = 0;i<ja1.length();i++)
                    {

                        name[i]=ja1.getJSONObject(i).getString("name");
                        inc[i]=ja1.getJSONObject(i).getString("incredints");
                        stock[i]=ja1.getJSONObject(i).getString("stock");
                        rate[i]=ja1.getJSONObject(i).getString("rate");
                        img[i]=ja1.getJSONObject(i).getString("image");




                    }
//                    CustomUser clist=new CustomUser(this,cat);
//                    l1.setAdapter(clist);


//                    CustomUser adapter = new CustomUser(this, name,img,stock,rate,inc);
//                    l1.setAdapter(adapter);
//                    ArrayAdapter<String> ar= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,value);
//                    lv1.setAdapter(ar);


                }

//                else {
////                    Toast.makeText(getApplicationContext(), "No Result found!", Toast.LENGTH_LONG).show();
//
//                }
            }

        }catch (Exception e)
        {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}