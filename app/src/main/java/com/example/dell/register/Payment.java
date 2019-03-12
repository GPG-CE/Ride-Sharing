package com.example.dell.register;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Payment extends AppCompatActivity implements View.OnClickListener {
String to1 , via1 , via2 , from1 , date1 , time1 , facility , cr_no;
Button payment;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        payment = findViewById(R.id.confirm);
        payment.setOnClickListener(this);


        to1 = getIntent().getExtras().getString("to1");
        via1 = getIntent().getExtras().getString("via1");
        via2 = getIntent().getExtras().getString("via2");
        from1 = getIntent().getExtras().getString("from1");
        date1 = getIntent().getExtras().getString("date1");
        time1 = getIntent().getExtras().getString("time1");
        facility = getIntent().getExtras().getString("facility");
        cr_no = getIntent().getExtras().getString("car_no");



        SharedPreferences sharedPreferences = getSharedPreferences("emaildata", Context.MODE_PRIVATE);
        Email = sharedPreferences.getString("Email",null);

    }

    @Override
    public void onClick(View v) {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://ridesharingproject.000webhostapp.com/booking_detailes.php?text1="+ Uri.encode(to1)+"&text2="+via1+"&text3="+via2+"&text4="+from1+"&text5="+date1+"&text6="+time1+"&text7="+facility+"&text8="+Email+"&text9="+cr_no;
        Log.e("url",url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("respons",response);


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
}
