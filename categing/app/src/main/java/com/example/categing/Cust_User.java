package com.example.categing;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Cust_User extends ArrayAdapter<String>  {

	 private Activity context;       //for to get current activity context
	    SharedPreferences sh;
	private String[] name;
	private String[] number;




	 public Cust_User(Activity context, String[] n, String[] num) {
	        //constructor of this class to get the values from main_activity_class

	        super(context, R.layout.cust_design,n);
	        this.context = context;

		    this.name = n;
		 	this.number = num;


	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	                 //override getView() method

	        LayoutInflater inflater = context.getLayoutInflater();
	        View listViewItem = inflater.inflate(R.layout.cust_design, null, true);
			//cust_list_view is xml file of layout created in step no.2

//	        ImageView im = (ImageView) listViewItem.findViewById(R.id.imageView1);
	        TextView t1=(TextView)listViewItem.findViewById(R.id.names);

		TextView t2=(TextView)listViewItem.findViewById(R.id.nums);
//			TextView t3=(TextView)listViewItem.findViewById(R.id.textView6);
//			TextView t4=(TextView)listViewItem.findViewById(R.id.textView7);
			t1.setText(name[position]);
			t2.setText(number[position]);
//			t3.setText(qtys[position]);
//			t4.setText(rate[position]);

//	       Toast.makeText(context, pth, Toast.LENGTH_LONG).show();

	        return  listViewItem;
	    }

		private TextView setText(String string) {
			// TODO Auto-generated method stub
			return null;
		}
}