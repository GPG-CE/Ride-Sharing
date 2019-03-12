package com.example.dell.register;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

public class customadapter extends ArrayAdapter<String> {

    private String[] to1;
    private String[] via1;
    private String[] via2;
    private String[] from1;
    private String[] date1;
    private String[] time1;
    private String[] facility;
    private String[] ID;
    private String[] c_no;
    private String[] prc;
    private Activity context;
    TextView textViewto1;


    public customadapter(Activity context, String[] to1, String[] via1, String[] via2 , String[] from1 , String[] date1 , String[] time1 , String[] facility , String[] ID ,String[] c_no , String[] prc)
    {

            super(context, R.layout.list, to1);

            this.to1 = to1;
            this.via1 = via1;
            this.via2 = via2;
            this.from1 = from1;
            this.date1 = date1;
            this.time1 = time1;
            this.facility = facility;
            this.ID = ID;
            this.c_no = c_no;
            this.prc=prc;
            this.context = context;

        }

        public View getView(final int position, View convertView, ViewGroup parent)
        {
                LayoutInflater inflater=context.getLayoutInflater();
                View listViewItem= inflater.inflate(R.layout.list,null,true);
                 textViewto1=(TextView)listViewItem.findViewById(R.id.to1);
                TextView textViewvia1=(TextView)listViewItem.findViewById(R.id.via1);
                TextView textViewvia2=(TextView)listViewItem.findViewById(R.id.via2);
                TextView textViewfrom1=(TextView)listViewItem.findViewById(R.id.from1);
                TextView textViewdate1=(TextView)listViewItem.findViewById(R.id.date1);
                TextView textViewtime1=(TextView)listViewItem.findViewById(R.id.time1);
                TextView textViewfacility1=(TextView)listViewItem.findViewById(R.id.facility);
                TextView textViewdeletecar = (TextView)listViewItem.findViewById(R.id.deletecar);
                TextView textViewcar_no = (TextView)listViewItem.findViewById(R.id.car_number);
            TextView textViewprice = (TextView)listViewItem.findViewById(R.id.price);

            textViewto1.setText(to1[position]+"");
            textViewvia1.setText(via1[position]);
            textViewvia2.setText(via2[position]);
            textViewfrom1.setText(from1[position]+"");
            textViewdate1.setText(date1[position]);
            textViewtime1.setText(time1[position]);
            textViewfacility1.setText(facility[position]);
            textViewcar_no.setText(c_no[position]);
            textViewprice.setText(prc[position]);


            textViewdeletecar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RequestQueue queue = Volley.newRequestQueue(context);
                    String url = "http://ridesharingproject.000webhostapp.com/deletecar.php?id="+ID[position];
                    Log.e("url",url);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("1"))
                                    {
                                        Intent ie =new Intent( context, Profile.class);
                                        context.startActivity(ie);

                                    }
                                    if (response.equals("2"))
                                    {

                                    }

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                    queue.add(stringRequest);
                }
            });

            return  listViewItem;



        }


}

