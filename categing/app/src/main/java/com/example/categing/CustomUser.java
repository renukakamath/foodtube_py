package com.example.categing;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CustomUser extends ArrayAdapter<String>  {

	 private Activity context;       //for to get current activity context
	    SharedPreferences sh;

	private String[] name;
	private String[] image;
	private String[] userimage;
	private String[] username;





	 public CustomUser(Activity context, String[] n, String[] img, String[] userimage, String[] username) {


	        //constructor of this class to get the values from main_activity_class

	        super(context, R.layout.cust_user,n);
	        this.context = context;

		    this.name = n;
		    this.image = img;
		    this.userimage = userimage;
		    this.username = username;




	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	                 //override getView() method

	        LayoutInflater inflater = context.getLayoutInflater();
	        View listViewItem = inflater.inflate(R.layout.cust_user, null, true);
			//cust_list_view is xml file of layout created in step no.2

	        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView1);
	        ImageView im2 = (ImageView) listViewItem.findViewById(R.id.imageView2);
	        TextView t1=(TextView)listViewItem.findViewById(R.id.names);
	        TextView t2=(TextView)listViewItem.findViewById(R.id.det);


//			TextView t3=(TextView)listViewItem.findViewById(R.id.textView6);
//			TextView t4=(TextView)listViewItem.findViewById(R.id.textView7);
//			Log.d("hello","\nName: "+name[position]+"Incredints: "+inc[position]+"Rate: "+rate[position]+"Stock: "+stock[position]);
//			t1.setText("Name: "+name[position]+"\nIncredints: "+inc[position]+"\nRate: "+rate[position]+"\nStock: "+stock[position]);
			t1.setText(name[position]);
			t2.setText(username[position]);

//			t3.setText(qtys[position]);
//			t4.setText(rate[position]);

//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

			String pth = "http://"+IPSettings.text+"/"+image[position];
			pth = pth.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

			Log.d("-------------", pth);
			Picasso.with(context)
					.load(pth)
					.placeholder(R.drawable.ic_launcher_background)
					.error(R.drawable.ic_launcher_background).into(im);

			String pth2 = "http://"+IPSettings.text+"/"+userimage[position];
			pth2 = pth2.replace("~", "");
//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

			Log.d("-------------", pth2);
			Picasso.with(context)
					.load(pth2)
					.placeholder(R.drawable.ic_launcher_background)
					.error(R.drawable.ic_launcher_background).into(im2);


			return  listViewItem;
	    }

		private TextView setText(String string) {
			// TODO Auto-generated method stub
			return null;
		}
}