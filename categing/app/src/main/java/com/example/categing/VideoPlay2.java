package com.example.categing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoPlay2 extends Activity {

	VideoView vv;
	SharedPreferences sh;

	ProgressDialog progressDialog;
	MediaController mediaController;
	public static String path;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
//		getSupportActionBar().hide(); // hide the title bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full sc
		setContentView(R.layout.activity_video_play);

		sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



		vv = (VideoView) findViewById(R.id.vv_child_vdo);
		try {

			String path = "http://" + IPSettings.text + "/" + MYProfile.video;
			path = path.replace(" ", "%20");
			Toast.makeText(getApplicationContext(), path, Toast.LENGTH_LONG).show();
//			Uri path_uri = Uri.parse(path);
//			vv.setVideoURI(path_uri);
//			vv.start();
			playvideo(path);
			
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Exc : " + e, Toast.LENGTH_LONG).show();
		}
	}

	
	public void playvideo(String videopath) {
	    Log.e("entered", "playvide");
	    Log.e("path is", "" + videopath);
	    try {
	        progressDialog = ProgressDialog.show(VideoPlay2.this, "",
	                "Buffering video...", false);
	        progressDialog.setCancelable(true);
	        getWindow().setFormat(PixelFormat.TRANSLUCENT);

	        mediaController = new MediaController(VideoPlay2.this);

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

	public void onBackPressed()
	{
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent b=new Intent(getApplicationContext(),MYProfile.class);
		startActivity(b);
	}


}
