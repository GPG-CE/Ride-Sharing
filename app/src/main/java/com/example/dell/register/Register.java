package com.example.dell.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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

import java.util.regex.Pattern;


public class Register extends AppCompatActivity implements View.OnClickListener {
    EditText user, phone, ema, pass;
    Button regi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /*edittext code*/
        user = findViewById(R.id.eduser);
        phone = findViewById(R.id.edphone);
        ema = findViewById(R.id.edemail);
        pass = findViewById(R.id.edpassword);
        /*button code*/
        //
        regi = findViewById(R.id.btnregister);
        regi.setOnClickListener(this);
    }
    /*register validation*/
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    public void onClick(View v) {

/*        SharedPreferences sharedPreferences =getSharedPreferences("emaildata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        editor.putString("Email",ema.getText().toString());
        editor.commit();
*/
        /*volly request code*/
        String usr = (user.getText().toString());
        String email = (ema.getText().toString());
        String phn = (phone.getText().toString());
        String psw = (pass.getText().toString());


        RequestQueue queue = Volley.newRequestQueue(this);
        String url="http://ridesharingproject.000webhostapp.com/androidregister.php?text1="+usr+"&text2="+email+"&text3="+phn+"&text4="+psw;
        StringRequest stringrequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      /*  Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();*/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(stringrequest);
        /*empty username validation*/
        if(TextUtils.isEmpty(user.getText().toString().trim())){
            user.setError("please enter username");
            user.findFocus();
        }
        /*empty email validation*/
        else if (TextUtils.isEmpty(ema.getText().toString().trim())){
            ema.setError("please enter Email");
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(ema.getText().toString().trim()).matches())
        {
            ema.setError("Enter Valid email");
        }
        /*empty phone_no validation*/
        else if (TextUtils.isEmpty(phone.getText().toString().trim())){
            phone.setError("please enter phone no");
        }
        else if(phone.getText().toString().length()!=10)
        {
            phone.setError("mobile number must be 10 digit");
        }
        /*empty password validation*/

        else if (TextUtils.isEmpty(pass.getText().toString().trim())){
            pass.setError("Enter password");
        }
        else if (pass.getText().toString().length()<8)
        {
            pass.setError("password is lessthan 8 charcter");
        }
        else
            /*all clear then enter new activity*/
        {
            Intent ie = new Intent(Register.this,Profile.class);
            startActivity(ie);
        }

    }


}
