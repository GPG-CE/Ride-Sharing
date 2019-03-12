package com.example.dell.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static android.widget.Toast.makeText;

public class car_register extends AppCompatActivity implements View.OnClickListener {
    Button car_register;
    EditText c_no, c_mod, t_seat, y_add, d_lin;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_register);

        c_no = findViewById(R.id.car_no);
        c_mod = findViewById(R.id.car_model);
        t_seat = findViewById(R.id.total_seat);
        y_add = findViewById(R.id.address);
        d_lin = findViewById(R.id.driving_license);

        /*button code*/

        car_register = findViewById(R.id.btn_car_register);
        car_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        SharedPreferences sharedPreferences = getSharedPreferences("emaildata", Context.MODE_PRIVATE);
        Email = sharedPreferences.getString("Email",null);

        final String cr_no = (c_no.getText().toString());
        Log.e("car_no",cr_no);
        String cr_mod = (c_mod.getText().toString());
        Log.e("car_mod",cr_mod);
        String to_seat = (t_seat.getText().toString());
        Log.e("to_seat",to_seat);
        String yur_add = (y_add.getText().toString());
        Log.e("yur_add",yur_add);
        String drv_lin = (d_lin.getText().toString());
        Log.e("drv_lin",drv_lin);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://ridesharingproject.000webhostapp.com/carregister.php?text1="+cr_no+"&text2="+cr_mod+"&text3="+to_seat+"&text4="+yur_add+"&text5="+drv_lin+"&text6="+Email;
        Log.e("error",url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("response"  , response);

                        SharedPreferences sharedPreferences =getSharedPreferences("car_reg", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor =sharedPreferences.edit();
                        editor.putString("car_registered","Yes");

                        Intent ie = new Intent(getApplicationContext() , Profile.class);
                        startActivity(ie);

                        editor.commit();

                        //  ie.putExtra("car_no",cr_no);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        car_register.setText("REGISTER");
                    }
                }
        );
        queue.add(stringRequest);




    }
}