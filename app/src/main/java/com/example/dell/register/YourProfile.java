package com.example.dell.register;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.actions.ItemListIntents;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Hashtable;
import java.util.Map;

public class YourProfile extends AppCompatActivity implements View.OnClickListener {
TextView name , email , phoneno;
//TextView fatchname , fatchemail , fatchmobile;
    String p_name;
    String p_email;
    String p_mobile_no , p_image;
    Button photo;
    private String KEY_IMAGE = "image";
    private static final int SELECT_IMAGE = 1;
    String realPath;
    RequestQueue queue;
    Bitmap bmp;
    ImageView imag;
String image;
    String Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_profile);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phoneno = findViewById(R.id.phone_no);
        photo = findViewById(R.id.photo);
        imag = findViewById(R.id.image);
        imag.setOnClickListener(this);

        //      fatchname = findViewById(R.id.fachname);
        /*SharedPreferences*/
        SharedPreferences sharedPreferences = getSharedPreferences("emaildata", Context.MODE_PRIVATE);
         Email = sharedPreferences.getString("Email",null);


        /*volley request your profile */
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://ridesharingproject.000webhostapp.com/YourProfile.php?email="+Email;
        Log.e("url",url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response",response);

                        for(int i = 0; i<=response.length(); i++)
                        {
                            String cols[] =response.split("#");
                            p_name = cols[0];
                            p_email = cols[1];
                            p_mobile_no = cols[2];
                            p_image = cols[3];

                        }

                        //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

                        name.setText(p_name);
                        email.setText(p_email);
                        phoneno.setText(p_mobile_no);

                        if(!p_image.equals(" "))
                        {
                            Log.e("p_image" , p_image);

                            Picasso.with(getApplicationContext()).load(p_image).into(imag);

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(stringRequest);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void imageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SELECT_IMAGE:
                    if (Build.VERSION.SDK_INT < 11) {
                        realPath = RealPathUtil.getRealPathFromURI_BelowAPI11(this, data.getData());

                    }
                        // SDK >= 11 && SDK < 19
                    else if (Build.VERSION.SDK_INT < 19) {
                        realPath = RealPathUtil.getRealPathFromURI_API11to18(this, data.getData());
                    }
                        // SDK > 19 (Android 4.4)
                    else {
                        realPath = RealPathUtil.getRealPathFromURI_API19(this, data.getData());

                    }
                    break;
            }
            bmp = BitmapFactory.decodeFile(realPath);

            String image = getStringImage(bmp);
            Log.e("image" ,image);
            uploadImage();
        }
    }

    private void uploadImage() {
        //Showing the progress dialog
        image = getStringImage(bmp);

        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }

        String url1 ="http://ridesharingproject.000webhostapp.com/upload1.php";
        Log.e("UpdateDetails" , url1);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e   ("respons uplaod `1e" , response);
                      if(response.trim().equals("1"))
                      {
                          Log.e("saasdfs","sdfsf");
                          Intent i = new Intent( getApplicationContext() , YourProfile.class);
                          startActivity(i);
                      }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("errror" , String.valueOf(error));
            }


        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //Creating parameters

                Map<String,String> params = new Hashtable<String, String>();
                //Adding parameters
                params.put("image" , image);
                params.put("email" , Email);
                //returning parameters
                return params;
            }
        };;
        queue.add(stringRequest);
        Log.e("URL", "CALL");
    }

    @Override
    public void onClick(View v) {
        imageFromGallery();
    }


}
