package com.example.wannago;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class locattion extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locattion);
        findViewById(R.id.Addedusers).setOnClickListener(this);
        findViewById(R.id.locationn).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.locationn:
                startActivity(new Intent(v.getContext(),MapsActivity2.class));


                break;
            case R.id.Addedusers:
                startActivity(new Intent(v.getContext(),MapsActivity.class));
                break;

        }
    }

}
