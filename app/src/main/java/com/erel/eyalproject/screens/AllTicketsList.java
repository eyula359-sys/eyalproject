package com.erel.eyalproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.erel.eyalproject.adapters.TicketAdapter;
import com.erel.eyalproject.services.DatabaseService;

import com.erel.eyalproject.R;
import com.erel.eyalproject.model.Ticket;

public class AllTicketsList extends AppCompatActivity {

    private static final String TAG = "AllTicketsList";
    private TicketAdapter ticketAdapter;
    private RecyclerView rvTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_tickets_list);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         rvTicket = findViewById(R.id.rv_tickets_list);
        rvTicket.setLayoutManager(new LinearLayoutManager(this));



        ticketAdapter = new TicketAdapter(0,new TicketAdapter.OnTicketClickListener() {
            @Override
            public void onTicketClick(Ticket ticket) {
                Log.d(TAG, "Ticket clicked: " + ticket);
                Intent intent = new Intent(AllTicketsList.this, Admin_activity.class);
                intent.putExtra("TICKET_ID", ticket.getTicketId());
                startActivity(intent);
            }

            @Override
            public void onLongTicketClick(Ticket ticket) {
                Log.d(TAG, "Ticket long clicked: " + ticket);
            }
        });

        rvTicket.setAdapter(ticketAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        DatabaseService.getInstance().getTicketList(new DatabaseService.DatabaseCallback<List<Ticket>>() {
            @Override
            public void onCompleted(List<Ticket> tickets) {
                Log.d(TAG, "Tickets loaded: " + tickets.size());
                ticketAdapter.setTicketList(tickets);
                ticketAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "Failed to get tickets list", e);
            }
        });

        Button btnBackToAdminActivity = findViewById(R.id.btnBackToAdminActivity);
        {

            btnBackToAdminActivity.setOnClickListener(v -> {
                Intent intent = new Intent(AllTicketsList.this, Admin_activity.class);
                startActivity(intent);
            });
    }}}