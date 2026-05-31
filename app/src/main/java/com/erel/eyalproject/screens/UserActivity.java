package com.erel.eyalproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btnAddTicket).setOnClickListener(v ->
                startActivity(new Intent(UserActivity.this, AddTicket.class)));

        findViewById(R.id.btnSearchTicket).setOnClickListener(v ->
                startActivity(new Intent(UserActivity.this, SearchTicketsActivity.class)));

        findViewById(R.id.btnMyTickets).setOnClickListener(v ->
                startActivity(new Intent(UserActivity.this, MyTicketsActivity.class)));

        findViewById(R.id.btnFavoriteTickets).setOnClickListener(v ->
                startActivity(new Intent(UserActivity.this, FavoriteTicketsActivity.class)));

        findViewById(R.id.btnGoToOwner).setOnClickListener(v ->
                startActivity(new Intent(UserActivity.this, OwnerOrdersActivity.class)));
        findViewById(R.id.btnGoToBuyer).setOnClickListener(v ->
                startActivity(new Intent(UserActivity.this, BuyerOrdersActivity.class)));
    }

    public void goUserProfile(View view) {
        startActivity(new Intent(UserActivity.this, UserProfileActivity.class));
    }
}