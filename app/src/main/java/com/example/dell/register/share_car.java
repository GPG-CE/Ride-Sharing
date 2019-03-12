package com.example.dell.register;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class share_car extends AppCompatActivity implements View.OnClickListener {
    String[] to = {"gandhinagar","ahmedabad","rajkot","himatnagar","sector-1" , "sector-26" , "government polytechnic sector-26"};

    AutoCompleteTextView actvt ,actvf ;
    final Calendar mycalender = Calendar.getInstance();
    EditText editdate , editvia1 , editvia2 , price;
    EditText EditTime;
    Button adcar;
    Spinner sp;
    String car_register="";

    /*fatch dat to other activity*/
//String car_number;
    public share_car() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_car);

        actvt = findViewById(R.id.autoTo);
        actvf = findViewById(R.id.autofrom);
        editvia1 = findViewById(R.id.editvia);
        editvia2 = findViewById(R.id.editvia2);
        editdate=(EditText) findViewById(R.id.editdate);
        EditTime = (EditText) findViewById(R.id.edittime);
        price = findViewById(R.id.price);
        sp = (Spinner)findViewById(R.id.spiner);

        adcar = (Button) findViewById(R.id.btnaddcar);
        adcar.setOnClickListener(this);


        SharedPreferences sharedPreferences1 = getSharedPreferences("car_reg", Context.MODE_PRIVATE);
        car_register = sharedPreferences1.getString("car_registered","");

        if(car_register.equals(""))
        {
            Intent i = new Intent(getApplicationContext(),car_register.class);
            startActivity(i);

        }


       // car_number = getIntent().getExtras().getString("car_no");
        //Log.e("carnumber",car_number);

        /*Creating the instance of ArrayAdapter containing autocomplete textview data*/
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,to);
            AutoCompleteTextView actvt = (AutoCompleteTextView)findViewById(R.id.autoTo);
            actvt.setThreshold(2);
            actvt.setAdapter(adapter);

            /*destination*/
            ArrayAdapter<String> adapterf = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,to);
            AutoCompleteTextView actvf = (AutoCompleteTextView)findViewById(R.id.autofrom);
            actvf.setThreshold(1);
            actvf.setAdapter(adapterf);

        /*for date*/

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
               /*date*/
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {

                    mycalender.set(Calendar.YEAR, year);
                    mycalender.set(Calendar.MONTH, monthOfYear);
                    mycalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();
                }
            };
            /*on click to open date format*/
            editdate.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    new DatePickerDialog(share_car.this, date, mycalender
                            .get(Calendar.YEAR), mycalender.get(Calendar.MONTH),
                            mycalender.get(Calendar.DAY_OF_MONTH)).show();
                }
            });
            /*for time*/
            EditTime.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);

                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(share_car.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            EditTime.setText( selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, true);
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();

                }
            });
    }
    /*for date format */
    private void updateLabel() {
        String myFormat = "yyyy/dd/MM"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editdate.setText(sdf.format(mycalender.getTime()));
    }



    @Override
    public void onClick(View v) {

        SharedPreferences sharedPreferences = getSharedPreferences("emaildata", Context.MODE_PRIVATE);
        String Email = sharedPreferences.getString("Email",null);

        Log.e("email",Email);
        String ema = Email;
        Log.e("hhh","hh");
        String to = (actvt.getText().toString());
        Log.e("to" , to);
        String frm = (actvf.getText().toString());
        Log.e("frm" , frm);
        String via1 = (editvia1.getText().toString());
        Log.e("via1",via1);
        String via2 = (editvia2.getText().toString());
        Log.e("via2",via2);
        String date = (editdate.getText().toString());
        Log.e("date",date);
        String time = (EditTime.getText().toString());
        Log.e("time",time);
        String spi = (sp.getSelectedItem().toString());
        Log.e("spi",spi);
        String prc = (price.getText().toString());
        Log.e("price",prc);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://ridesharingproject.000webhostapp.com/sharecar.php?text1="+ Uri.encode(to) +"&text2="+via1+"&text3="+via2+"&text4="+Uri.encode(frm)+"&text5="+date+"&text6="+time+"&text7="+spi+"&text8="+ema+"&text10="+prc;
        Log.e("error",url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("error",response);
                        Intent ie  = new Intent(getApplicationContext() , sharecardata.class);
                        startActivity(ie);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adcar.setText("error");
                    }
                }

        );
        queue.add(stringRequest);

    }

}



