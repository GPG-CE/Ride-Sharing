package com.example.dell.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class car_data extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView1;
    String src, destin;
    String to1[] , via1[] ,via2[] , from1[] , date1[] , time1[] , facility[] , car_no[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_data);

        //listView1 = findViewById(R.id.listview);
        listView1 = (ListView) findViewById(R.id.listview);

        /*put data from another activity*/
        src = getIntent().getExtras().getString("src");
        Log.e("sssssssssss" , src);
        destin = getIntent().getExtras().getString("dest");
        Log.e("dededeee" , destin);
        String sr = src;
        String de = destin;

        /*list view find view id*/
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://ridesharingproject.000webhostapp.com/searchcar.php?text1=" + sr + "&text2=" + de;
        Log.e("url",url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                        public void onResponse(String response){
                            String rows[] = response.split("##");
                            Log.e("reponse" , response);
                             to1 = new String[rows.length];
                             via1 = new String[rows.length];
                             via2 = new String[rows.length];
                             from1 = new String[rows.length];
                             date1 = new String[rows.length];
                             time1 = new String[rows.length];
                             facility = new String[rows.length];
                             car_no = new String[rows.length];
                            for (int i = 0; i < rows.length; i++) {

                                String col[] = rows[i].split("#");
                                to1[i] = col[0];
                                via1[i] = col[1];
                                via2[i] = col[2];
                                from1[i] = col[3];
                                date1[i] = col[4];
                                time1[i] = col[5];
                                facility[i] = col[6];
                                car_no[i] = col[7];
                            }
                            adaptercardata customList = new adaptercardata(car_data.this, to1, via1, via2, from1, date1, time1, facility , car_no);

                            listView1.setAdapter(customList);


                        }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
                );
        queue.add(stringRequest);
        listView1.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent ie;
        ie = new Intent(this,Payment.class);
        ie.putExtra("to1",to1[position]);
        ie.putExtra("via1",via1[position]);
        ie.putExtra("via2",via2[position]);
        ie.putExtra("from1",from1[position]);
        ie.putExtra("date1",date1[position]);
        ie.putExtra("time1",time1[position]);
        ie.putExtra("facility",facility[position]);
        ie.putExtra("car_no",car_no[position]);
        startActivity(ie);




    }
}
