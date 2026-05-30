package com.erel.eyalproject.screens;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erel.eyalproject.R;
import com.erel.eyalproject.adapters.TicketAdapter;
import com.erel.eyalproject.model.Ticket;
import com.erel.eyalproject.services.DatabaseService;

import java.util.List;

public class MyTicketsActivity extends AppCompatActivity {

    private static final String TAG = "MyTicketsActivity";
    private TicketAdapter ticketAdapter;
    private RecyclerView rvMyTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_tickets);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rvMyTickets = findViewById(R.id.rv_my_tickets);
        rvMyTickets.setLayoutManager(new LinearLayoutManager(this));

        ticketAdapter = new TicketAdapter(new TicketAdapter.OnTicketClickListener() {
            @Override
            public void onTicketClick(Ticket ticket) {
                Log.d(TAG, "Ticket clicked: " + ticket);
            }

            @Override
            public void onLongTicketClick(Ticket ticket) {
                Log.d(TAG, "Ticket long clicked: " + ticket);
            }
        });

        rvMyTickets.setAdapter(ticketAdapter);

        findViewById(R.id.btnBackFromMyTickets).setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();

        DatabaseService.getInstance().getMyTickets(new DatabaseService.DatabaseCallback<List<Ticket>>() {
            @Override
            public void onCompleted(List<Ticket> tickets) {
                Log.d(TAG, "My tickets loaded: " + tickets.size());
                ticketAdapter.setTicketList(tickets);
                ticketAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "Failed to get my tickets", e);
            }
        });
    }
}