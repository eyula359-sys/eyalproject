package com.erel.eyalproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.erel.eyalproject.R;
import com.erel.eyalproject.model.Game;
import com.erel.eyalproject.model.Ticket;
import com.erel.eyalproject.services.DatabaseService;

import java.util.ArrayList;
import java.util.List;
import android.widget.Spinner;
import android.widget.ArrayAdapter;


public class AddTicket extends AppCompatActivity implements View.OnClickListener {

    EditText text_Price, text_Section, text_Row, text_Seat, text_Currency;
    Button btnAddTick;
    private DatabaseService databaseService;
    Spinner spinnerGames;

    ArrayList<Game> games= new ArrayList<>();
    ArrayAdapter<Game> gamesAdapter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_ticket);
        spinnerGames = findViewById(R.id.spinnergames);


        databaseService = DatabaseService.getInstance();


        gamesAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                games
        );

        gamesAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerGames.setAdapter(gamesAdapter);


        databaseService.getGamesList(new DatabaseService.DatabaseCallback<List<Game>>() {
            @Override
            public void onCompleted(List<Game> gameList) {
                games.clear();
                games.addAll(gameList);
                gamesAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailed(Exception e) {

            }
        });


        btnAddTick = findViewById(R.id.btnAddTick);
        text_Price = findViewById(R.id.text_Price);
        text_Section = findViewById(R.id.text_Section);
        text_Row = findViewById(R.id.text_Row);
        text_Seat = findViewById(R.id.text_Seat);
        text_Currency = findViewById(R.id.text_Currency);


        btnAddTick.setOnClickListener(this);
        Button backToUser = findViewById(R.id.backToUser);
        {

            backToUser.setOnClickListener(v -> {
                Intent intent = new Intent(AddTicket.this, UserActivity.class);
                startActivity(intent);
            });


        }
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
            }
        });
    }


    @Override
    public void onClick(View v) {

        String ticketId=databaseService.generateTicketId();
        Game selectedGame = (Game) spinnerGames.getSelectedItem();
        String price = text_Price.getText().toString();
        String section = text_Section.getText().toString();
        String row = text_Row.getText().toString();
        String seat = text_Seat.getText().toString();
        String currency = text_Currency.getText().toString();
        Ticket newTicket= new Ticket();

        databaseService.createNewTicket(newTicket, new DatabaseService.DatabaseCallback<Void>() {
            @Override
            public void onCompleted(Void object) {
                Log.d("TAG", "createTicketInDatabase: Ticket created successfully");
                Intent intent = new Intent(AddTicket.this, UserActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailed(Exception e) {
                Log.e("TAG", "createTicketInDatabase: Ticket created not successfully");

            }
        });


    }




}