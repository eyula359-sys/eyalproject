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

public class Admin_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnAllUsers = findViewById(R.id.btnAllUsers);

        btnAllUsers.setOnClickListener(v -> {
            Intent intent = new Intent(Admin_activity.this, UsersListActivity.class);
            startActivity(intent);
        });

        Button btnAddNewGame = findViewById(R.id.btnAddNewGame);

        btnAddNewGame.setOnClickListener(v -> {
            Intent intent = new Intent(Admin_activity.this, AddGame.class);
            startActivity(intent);
        });

        Button btnAllTickets = findViewById(R.id.btnAllTickets);

        btnAllTickets.setOnClickListener(v -> {
            Intent intent = new Intent(Admin_activity.this, AllTicketsList.class);
            startActivity(intent);
        });
    }
}