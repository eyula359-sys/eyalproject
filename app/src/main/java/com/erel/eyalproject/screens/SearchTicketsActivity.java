package com.erel.eyalproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erel.eyalproject.R;
import com.erel.eyalproject.adapters.TicketAdapter;
import com.erel.eyalproject.model.Game;
import com.erel.eyalproject.model.Ticket;
import com.erel.eyalproject.services.DatabaseService;

import java.util.ArrayList;
import java.util.List;

public class SearchTicketsActivity extends AppCompatActivity {

    private static final String TAG = "SearchTicketsActivity";

    Spinner spinnerGames;
    RecyclerView rvTickets;
    Button btnBackToUser;

    TicketAdapter ticketAdapter;

    ArrayList<Game> games = new ArrayList<>();
    ArrayAdapter<Game> gamesAdapter;

    ArrayList<Ticket> allTickets = new ArrayList<>();

    DatabaseService databaseService;
    private Button backToUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tickets);

        spinnerGames = findViewById(R.id.spinnergames2);
        rvTickets = findViewById(R.id.rv_specificticket_list);
        backToUser = findViewById(R.id.backToUser);

        backToUser.setOnClickListener(v -> {
            Intent intent = new Intent(SearchTicketsActivity.this, UserActivity.class);
            startActivity(intent);
        });

        databaseService = DatabaseService.getInstance();

        rvTickets.setLayoutManager(new LinearLayoutManager(this));

        ticketAdapter = new TicketAdapter(0,new TicketAdapter.OnTicketClickListener() {
            @Override
            public void onTicketClick(Ticket ticket) {
                Log.d(TAG, "Ticket clicked: " + ticket);



            }

            @Override
            public void onLongTicketClick(Ticket ticket) {
                Log.d(TAG, "Ticket long clicked: " + ticket);
            }
        }, true);

        rvTickets.setAdapter(ticketAdapter);

        gamesAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                games
        );
        gamesAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spinnerGames.setAdapter(gamesAdapter);

        spinnerGames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Game selectedGame = (Game) spinnerGames.getSelectedItem();
                filterTicketsByGame(selectedGame.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ticketAdapter.setTicketList(allTickets);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        databaseService.getGamesList(new DatabaseService.DatabaseCallback<List<Game>>() {
            @Override
            public void onCompleted(List<Game> gamesFromDb) {
                games.clear();
                games.addAll(gamesFromDb);
                gamesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "Failed to load games", e);
            }
        });

        databaseService.getTicketList(new DatabaseService.DatabaseCallback<List<Ticket>>() {
            @Override
            public void onCompleted(List<Ticket> tickets) {
                Log.d(TAG, "Tickets loaded: " + tickets.size());
                allTickets.clear();
                allTickets.addAll(tickets);
                ticketAdapter.setTicketList(allTickets);
            }

            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "Failed to load tickets", e);
            }
        });
    }

    private void filterTicketsByGame(String gameId) {
        ArrayList<Ticket> filteredTickets = new ArrayList<>();
        for (Ticket ticket : allTickets) {
            if (ticket.getGame() != null &&
                    ticket.getGame().getId().equals(gameId)) {
                filteredTickets.add(ticket);
            }
        }
        ticketAdapter.setTicketList(filteredTickets);
    }
}