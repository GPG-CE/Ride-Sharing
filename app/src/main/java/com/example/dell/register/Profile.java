package com.example.dell.register;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    EditText suredit , destedit;
    Button sbm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*textview findviewbyid*/

        /*editview findviewbyid*/
        suredit = (EditText)findViewById(R.id.sourceid);
        destedit = (EditText)findViewById(R.id.destinationid);

        /*button code*/
        sbm = findViewById(R.id.submit);
        sbm.setOnClickListener(this);

        /*floating action button coding*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ie = new Intent(Profile.this , share_car.class);
                startActivity(ie);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
// Handle the camera action
        if (id == R.id.nav_profile) {
            Intent ie;
            ie = new Intent(this , YourProfile.class);
            startActivity(ie);

        } else if (id == R.id.nav_car) {
            /*car data activity*/
            Intent ie = new Intent(this,car_register.class);
            startActivity(ie);
        } else if (id == R.id.nav_upcome) {
            Intent ie;
            ie = new Intent(this , upcomingride.class);
            startActivity(ie);

        } else if (id == R.id.nav_feed) {

            /*feedback activity */
            Intent ie = new Intent(Profile.this,feedback.class);
            startActivity(ie);

        } else if (id == R.id.nav_rep) {

        }
        else if(id == R.id.nav_yurcar)
        {
            Intent ie;
            ie = new Intent(this , sharecardata.class);
            startActivity(ie);
        }
        else if (id == R.id.nav_Driver_Location)
        {
           Intent ie;
           ie = new Intent(this,DriverLocation.class);
           startActivity(ie);
        }
        /*share application*/
        else if (id == R.id.nav_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "AndroidSolved");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Now Learn Android with AndroidSolved clicke here to visit https://url of our application/ ");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
        /*click submit button then open car data activity*/
    @Override
    public void onClick(View v) {

        String sr = suredit.getText().toString();
        String des = destedit.getText().toString();
        Log.e("se",sr);
        Log.e("des",des);
        Intent ie = new Intent(getApplicationContext(),car_data.class);
        ie.putExtra("src" , sr);
        ie.putExtra("dest" , des);

        startActivity(ie);

    }
}
