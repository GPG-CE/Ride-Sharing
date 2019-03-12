package com.example.dell.register;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DriverLocation extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Handler handler;
    String Email , lati , langu;
    LatLng sydney;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_location);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        SharedPreferences sharedPreferences = getSharedPreferences("emaildata", Context.MODE_PRIVATE);
         Email = sharedPreferences.getString("Email",null);
flag=1;

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://ridesharingproject.000webhostapp.com/DriverLocation.php?email="+Email;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("respoonse DriveLocation" , response);
                                for(int i = 0; i<=response.length(); i++) {
                                    String cols[] = response.split("#");
                                    lati = cols[0];
                                    Log.e("latitude",lati);
                                    langu = cols[1];
                                    Log.e("langitude",langu);


                                    sydney = new LatLng(Double.parseDouble(cols[0]) , Double.parseDouble(cols[1]));

                                }

                                if(flag==1) {
                                    mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
                                flag=0;
                                }
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                );
                queue.add(stringRequest);



                handler.postDelayed(this, 5000);


            }
        };

        handler.postDelayed(r, 5000);



    }

    private  void volley()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://ridesharingproject.000webhostapp.com/DriverLocation.php?email="+Email;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("respoonse DriveLocation" , response);
                        for(int i = 0; i<=response.length(); i++) {
                            String cols[] = response.split("#");
                            lati = cols[0];
                            Log.e("latitude",lati);
                            langu = cols[1];
                            Log.e("langitude",langu);



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
}
