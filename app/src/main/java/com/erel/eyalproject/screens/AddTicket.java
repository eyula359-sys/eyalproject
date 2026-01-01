package com.erel.eyalproject.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.erel.eyalproject.R;
import com.erel.eyalproject.model.Game;
import com.erel.eyalproject.services.DatabaseService;

import java.util.ArrayList;
import java.util.List;
import android.widget.Spinner;
import android.widget.ArrayAdapter;


public class AddTicket extends AppCompatActivity implements View.OnClickListener {

    EditText text_Game, text_Price, text_Section, text_Row, text_Seat, text_Currency;
    Button btnAddTick;
    private DatabaseService databaseService;
    Spinner spinnerGames;
    ArrayAdapter<Game> gamesAdapter;


    ArrayList<Game> games = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_game);
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



        btnAddTick = findViewById(R.id.btnAddTick);
        text_Game = findViewById(R.id.text_Game);
        text_Price = findViewById(R.id.text_Price);
        text_Section = findViewById(R.id.text_Section);
        text_Row = findViewById(R.id.text_Row);
        text_Seat = findViewById(R.id.text_Seat);
        text_Currency = findViewById(R.id.text_Currency);


        btnAddTick.setOnClickListener(this);
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

        String gameId = selectedGame.getId();


        String game = text_Game.getText().toString();
        String price = text_Price.getText().toString();
        String section = text_Section.getText().toString();
        String row = text_Row.getText().toString();
        String seat = text_Seat.getText().toString();
        String currency = text_Currency.getText().toString();
    }

}