package com.erel.eyalproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.erel.eyalproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

    }

    public void goRegister(View view) {

        Intent go=new Intent(this, RegisterActivity.class);
        startActivity(go);
    }

    public void goLogin(View view) {

        Intent go=new Intent(this, LoginActivity.class);
        startActivity(go);
    }
}
