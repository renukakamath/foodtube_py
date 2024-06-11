package com.example.categing;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements JsonResponse{

    String fname,lname,place,phone,email,username,passwrd,rel,dob,hname;
    EditText e6;
    private int mYear, mMonth, mDay, mHour, mMinute;
    Spinner s1;
    String[] dropplace,value,place_id;
    public  static String plid;

    ImageButton i1,i2;
    Button b1;
    SharedPreferences sh;
    final int CAMERA_PIC_REQUEST = 0, GALLERY_CODE = 201;
    public static String encodedImage = "", path = "",newval="",newimg="";
    private Uri mImageCaptureUri;
    byte[] byteArray = null;
    byte[] byteArray2 = null;
    EditText e1,e2,e3,e4;
    RadioButton r1,r2;
    private final int REQUEST_TAKE_GALLERY_VIDEO = 305;
    String name,dtls,inc,dietmode,Calorie;
    private static final int CAMERA_CODE = 101,  CROPING_CODE = 301,READ_EXTERNAL_STORAGE_PERMISSION=1,CAMERA=2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full sc
        setContentView(R.layout.activity_register);



        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Button b1 = findViewById(R.id.button2);

        EditText e1 = findViewById(R.id.editTextTextPersonName7);
        EditText e2 = findViewById(R.id.editTextTextPersonName6);
        EditText e3 = findViewById(R.id.phone);


        EditText e7 = findViewById(R.id.email);
        EditText e5 = findViewById(R.id.place);

        EditText e9 = findViewById(R.id.username);
        EditText e10 = findViewById(R.id.passw);

        i1= findViewById(R.id.imagebtn);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageOption();

            }

        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname = e1.getText().toString();
                lname = e2.getText().toString();
                phone = e3.getText().toString();
                place = e5.getText().toString();

                email= e7.getText().toString();


                username = e9.getText().toString();
                passwrd= e10.getText().toString();

                if (fname.equalsIgnoreCase("")) {
                    e1.setError("Enter Your FirstName");
                    e1.setFocusable(true);
                } else if (phone.equalsIgnoreCase("") || phone.length()!=10) {
                    e3.setError("Enter Your Phonenumber");
                    e3.setFocusable(true);
                }
                else if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")) {
                    e7.setError("Enter Your Valid EmailId");
                    e7.setFocusable(true);
                }
                else if (username.equalsIgnoreCase("")) {
                    e9.setError("Enter Your Username");
                    e9.setFocusable(true);
                }
                else if (lname.equalsIgnoreCase("")) {
                    e2.setError("Enter Your Lastname");
                    e2.setFocusable(true);
                }
                else if (place.equalsIgnoreCase("")) {
                    e5.setError("Enter Your Place");
                    e5.setFocusable(true);
                }
                else if (passwrd.equalsIgnoreCase("")) {
                    e9.setError("Enter Your Password");
                    e9.setFocusable(true);
                }else {
                    sendAttach();
//                    JsonReq JR = new JsonReq();
//                    JR.json_response = (JsonResponse) Register.this;
//                    String q = "/reg?fname=" + fname +"&lname="+lname+ "&phone=" + phone+ "&email=" + email+ "&uname=" + username+ "&pass=" + passwrd+"&place="+place;
//                    q = q.replace(" ", "%20");
//                    JR.execute(q);
                }
            }
        });

    }

    private void sendAttach() {
        try {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//	            String uid = sh.getString("uid", "");


//        	latitude=LocationService.lati;
//        	longitude=LocationService.logi;


            String q = "http://" + IPSettings.text + "/api/reg";
//            String q = "http://" +IpSetting.ip+"/api/user_upload_file";
//	            String q = "/user_upload_file/?image="+imagename+"&desc="+des+"&yts="+yt;
//	        	String q = "http://" +IPSetting.ip+"/TeachersHelper/api.php?action=teacher_upload_notes";
//	        	String q= "api.php?action=teacher_upload_notes&sh_id="+Teacher_view_timetable.s_id+"&desc="+des;

            Toast.makeText(getApplicationContext(), "Byte Array:" + byteArray.length, Toast.LENGTH_LONG).show();


            Map<String, byte[]> aa = new HashMap<>();

            aa.put("image", byteArray);
            aa.put("fname",fname.getBytes());
            aa.put("lname",lname.getBytes());
            aa.put("phone",phone.getBytes());
            aa.put("email",email.getBytes());
            aa.put("uname",username.getBytes());
            aa.put("pass",passwrd.getBytes());
            aa.put("place",place.getBytes());
//            aa.put("sid",Parentmanagestudent.sids.getBytes());
//            aa.put("latitude",LocationService.lati.getBytes());
//            aa.put("longitude",LocationService.logi.getBytes());

            //   aa.put("logid", sh.getString("log_id", "").getBytes());
//            aa.put("link",link.getBytes());
//            aa.put("description",description.getBytes());
//            aa.put("house",house.getBytes());
//            aa.put("phone",phone.getBytes());
//            aa.put("email",email.getBytes());
//            aa.put("gender",gender.getBytes());
//            aa.put("dob",dob.getBytes());
//            aa.put("username",user.getBytes());
//            aa.put("password",pass.getBytes());

            FileUploadAsync fua = new FileUploadAsync(q);
            fua.json_response = (JsonResponse) Register.this;
            fua.execute(aa);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Exception upload : " + e, Toast.LENGTH_SHORT).show();
        }
    }

    private void selectImageOption() {

        /*Android 10+ gallery code
        android:requestLegacyExternalStorage="true"*/

        final CharSequence[] items = {"Capture Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
        builder.setTitle("Take Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Capture Photo")) {
                    newval="image";
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CAMERA_PIC_REQUEST);

                } else if (items[item].equals("Choose from Gallery")) {
                    newval="image";
                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, GALLERY_CODE);

                }
                else if (items[item].equals("Choose From Video")) {
                    //  ftype = "video";
                    newval="video";
                    Intent intent = new Intent();
                    intent.setType("video/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select Video"),REQUEST_TAKE_GALLERY_VIDEO);


                }
                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && null != data) {

            mImageCaptureUri = data.getData();
            System.out.println("Gallery Image URI : " + mImageCaptureUri);
            //   CropingIMG();

            Uri uri = data.getData();
            Log.d("File Uri", "File Uri: " + uri.toString());
            // Get the path
            //String path = null;
            try {
                path = FileUtils.getPath(this, uri);
//                Toast.makeText(getApplicationContext(), "path : " + path, Toast.LENGTH_LONG).show();

                File fl = new File(path);
                int ln = (int) fl.length();

                InputStream inputStream = new FileInputStream(fl);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[ln];
                int bytesRead = 0;

                while ((bytesRead = inputStream.read(b)) != -1) {
                    bos.write(b, 0, bytesRead);
                }
                inputStream.close();
                byteArray = bos.toByteArray();

                Bitmap bit = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//                Toast.makeText(getApplicationContext(), "test case: "+bit, Toast.LENGTH_LONG).show();
                i1.setImageBitmap(bit);

                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encodedImage = str;
//                sendAttach1();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {

            try {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                i1.setImageBitmap(thumbnail);
                byteArray = baos.toByteArray();

                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encodedImage = str;
//                sendAttach1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_GALLERY_VIDEO) {
                Uri selectedImageUri = data.getData();

                // OI FILE Manager
                String filemanagerstring = selectedImageUri.getPath();

                // MEDIA GALLERY
//	                selectedImagePath = getPaths(selectedImageUri);
//	                if (selectedImagePath != null) {
//
//	                	Toast.makeText(getApplicationContext(), "Helloo Files", Toast.LENGTH_LONG).show();
//	                    Intent intent = new Intent(User_upload_files.this,
//	                            User_upload_files.class);
//	                    intent.putExtra("path", selectedImagePath);
//	                    startActivity(intent);
//	                }
//	                image = decodeFile(selectedImagePath);
                try {
                    InputStream iStream =   getContentResolver().openInputStream(selectedImageUri);
                    byte[] inputData = getBytes(iStream);
                    Toast.makeText(getApplicationContext(), "Len : " + inputData.length, Toast.LENGTH_LONG).show();
                    byteArray2 = inputData;
                } catch(Exception e) {}
            }


        }
    }
    public byte[] getBytes(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }



    @Override
    public void response(JSONObject jo) {
        try {
            String method = jo.getString("method");
            Log.d("pearl", method);

            if (method.equalsIgnoreCase("reg")) {

                String status = jo.getString("status");
                if (status.equalsIgnoreCase("success")) {

                    Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));

                }  else if (status.equalsIgnoreCase("already")) {

                    Toast.makeText(getApplicationContext(), "Username Already Exist!. Try new one", Toast.LENGTH_SHORT).show();


                }

            }

            if (method.equalsIgnoreCase("viewplace")) {

                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                dropplace = new String[ja1.length()];
                value= new String[ja1.length()];
                place_id= new String[ja1.length()];



                for (int i = 0; i < ja1.length(); i++) {
                    dropplace[i] = ja1.getJSONObject(i).getString("place");
                    place_id[i] = ja1.getJSONObject(i).getString("place_id");


                    value[i] = dropplace[i];
                }
                ArrayAdapter<String> ar = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, value);
                s1.setAdapter(ar);

            }

        }
        catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Login.class);
        startActivity(b);
    }


}