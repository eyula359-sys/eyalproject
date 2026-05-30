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
import com.erel.eyalproject.services.FavoriteTickets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FavoriteTicketsActivity extends AppCompatActivity {

    private static final String TAG = "FavoriteTicketsActivity";
    private TicketAdapter ticketAdapter;
    private RecyclerView rvFavoriteTickets;
    private FavoriteTickets favoritesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_favorite_tickets);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        favoritesManager = new FavoriteTickets(this);

        rvFavoriteTickets = findViewById(R.id.rv_favorite_tickets);
        rvFavoriteTickets.setLayoutManager(new LinearLayoutManager(this));


        ticketAdapter = new TicketAdapter(new TicketAdapter.OnTicketClickListener() {
            @Override
            public void onTicketClick(Ticket ticket) {
                Log.d(TAG, "Ticket clicked: " + ticket);
            }

            @Override
            public void onLongTicketClick(Ticket ticket) {
                Log.d(TAG, "Ticket long clicked: " + ticket);
            }
        }, true);

        rvFavoriteTickets.setAdapter(ticketAdapter);

        findViewById(R.id.btnBackFromFavorites).setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavoriteTickets();
    }

    private void loadFavoriteTickets() {
        Set<String> favoriteIds = favoritesManager.getFavoriteIds();

        DatabaseService.getInstance().getTicketList(new DatabaseService.DatabaseCallback<List<Ticket>>() {
            @Override
            public void onCompleted(List<Ticket> allTickets) {
                List<Ticket> favorites = new ArrayList<>();
                for (Ticket ticket : allTickets) {
                    if (favoriteIds.contains(ticket.getTicketId())) {
                        favorites.add(ticket);
                    }
                }
                Log.d(TAG, "Favorite tickets loaded: " + favorites.size());
                ticketAdapter.setTicketList(favorites);
            }

            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "Failed to load tickets", e);
            }
        });
    }
}