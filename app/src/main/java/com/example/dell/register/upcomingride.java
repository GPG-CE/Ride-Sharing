package com.example.dell.register;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class upcomingride extends AppCompatActivity {
    ListView listView;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcomingride);
        listView = findViewById(R.id.listview);

        SharedPreferences sharedPreferences = getSharedPreferences("emaildata", Context.MODE_PRIVATE);
         Email = sharedPreferences.getString("Email",null);
         Log.e("email",Email);

       RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://ridesharingproject.000webhostapp.com/upcomingdata.php?email="+Email;
        Log.e("url" , url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response",response);
                        String rows[]=response.split("##");
                        String to1[]=new String[rows.length];
                        String via1[]=new String[rows.length];
                        String via2[]=new String[rows.length];
                        String from1[]=new String[rows.length];
                        String date1[]=new String[rows.length];
                        String time1[]=new String[rows.length];
                        String fecility[]=new String[rows.length];
                        String carno[] = new String[rows.length];
                       for(int i=0;i<rows.length;i++) {
                            //Log.e("Rows " + i, rows[i]);
                            String col[]=rows[i].split("#");
                            to1[i]=col[0];
                            via1[i]=col[1];
                            via2[i]=col[2];
                            from1[i]=col[3];
                            date1[i]=col[4];
                            time1[i]=col[5];
                            fecility[i]=col[6];
                            carno[i]=col[7];
                     }
                        upcomingadapter customList = new upcomingadapter(upcomingride.this, to1,via1,via2,from1,date1,time1,fecility , carno);

                        listView.setAdapter(customList);

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
