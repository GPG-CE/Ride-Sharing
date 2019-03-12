package com.example.dell.register;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class feedback extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener {

    RatingBar ratingBar;
    EditText t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ratingBar = findViewById(R.id.rating);
        ratingBar.setOnRatingBarChangeListener(this);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if(rating==1)
        {

        }
        Toast.makeText(this, rating+"", Toast.LENGTH_SHORT).show();
    }
}
