package com.erel.eyalproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.erel.eyalproject.R;

public class UserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);

        Button btnAddTicket = findViewById(R.id.btnAddTicket);

        btnAddTicket.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, AddTicket.class);
            startActivity(intent);

        });

    }
}