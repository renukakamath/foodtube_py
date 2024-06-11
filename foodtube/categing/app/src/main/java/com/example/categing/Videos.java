package com.example.categing;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;


public class Videos extends Activity implements OnItemClickListener,JsonResponse {

	ListView lv1;
	String[] photo;
	public static String hot_id;
	String[] addpath, title,add_id,value;

	SharedPreferences sh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_videos);
		sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


		lv1 = (ListView) findViewById(R.id.ltvideo);
		lv1.setOnItemClickListener(this);


		JsonReq JR = new JsonReq();
		JR.json_response = (JsonResponse) Videos.this;
		String q = "/public_view_videos";
		q = q.replace(" ", "%20");
		JR.execute(q);
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent in = new Intent(getApplicationContext(), VideoPlay.class);
		in.putExtra("vdo", addpath[arg2]);
		startActivity(in);
	}


	@Override
	public void response(JSONObject jo) {
		// TODO Auto-generated method stub
		try {

			String method = jo.getString("method");
			if (method.equalsIgnoreCase("public_view_videos")) {
				String status = jo.getString("status");
				Log.d("pearl", status);
				Toast.makeText(getApplicationContext(), status, Toast.LENGTH_SHORT).show();
				if (status.equalsIgnoreCase("success")) {

					JSONArray ja1 = (JSONArray) jo.getJSONArray("data");


					addpath = new String[ja1.length()];
					title= new String[ja1.length()];
					add_id= new String[ja1.length()];
					value= new String[ja1.length()];

					for (int i = 0; i < ja1.length(); i++) {


						addpath[i] = ja1.getJSONObject(i).getString("addpath");
						title[i] = ja1.getJSONObject(i).getString("title");
						add_id[i] = ja1.getJSONObject(i).getString("add_id");


						value[i] =  "addpath:" + addpath[i] + "\ntitle:" + title[i]    ;



					}
//				Custimage clist=new Custimage(this,photo);
//				 lv1.setAdapter(clist);

					ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, addpath);
					lv1.setAdapter(ar);


				} else {
					Toast.makeText(getApplicationContext(), "no data", Toast.LENGTH_LONG).show();

				}
			}
//			if(method.equalsIgnoreCase("buyprod"))
//			{
//				String status=jo.getString("status");
//				Toast.makeText(getApplicationContext(),status, Toast.LENGTH_LONG).show();
//				if(status.equalsIgnoreCase("success"))
//				{
//					Toast.makeText(getApplicationContext(),"Your order is submitted!", Toast.LENGTH_LONG).show();
//				}
//				else{
//					Toast.makeText(getApplicationContext(),"Your order is not submitted", Toast.LENGTH_LONG).show();
//				}
//			}
		} catch (Exception e) {
			// TODO: handle exception

			Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
		}


	}

	@Override



		public void onBackPressed ()
		{
			// TODO Auto-generated method stub
			super.onBackPressed();
			Intent b = new Intent(getApplicationContext(), UserHome.class);
			startActivity(b);
		}
	}
