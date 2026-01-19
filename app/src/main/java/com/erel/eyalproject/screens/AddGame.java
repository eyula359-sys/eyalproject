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
import com.erel.eyalproject.services.DatabaseService;

public class AddGame extends AppCompatActivity implements View.OnClickListener {

    EditText text_hometeam, text_awayteam, text_date, text_hour;
    Button btnAdd;
    private DatabaseService databaseService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_game);

        databaseService = DatabaseService.getInstance();


        btnAdd = findViewById(R.id.btnAdd);
        text_hometeam = findViewById(R.id.text_hometeam);
        text_awayteam = findViewById(R.id.text_awayteam);
        text_date = findViewById(R.id.text_date);
        text_hour = findViewById(R.id.text_hour);

        btnAdd.setOnClickListener(this);


        Button backToAdmin = findViewById(R.id.backToAdmin);

        backToAdmin.setOnClickListener(v -> {
            Intent intent = new Intent(AddGame.this, Admin_activity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onClick(View v) {

        if (v == btnAdd) {

            String homeTeam = text_hometeam.getText().toString();
            String aweyTeam = text_awayteam.getText().toString();
            String Date = text_date.getText().toString();
            String Hour = text_hour.getText().toString();

            String gameId = databaseService.generateGameId();


            Game newGame = new Game(gameId, homeTeam, aweyTeam, Date, Hour);


            databaseService.createNewGame(newGame, new DatabaseService.DatabaseCallback<Void>() {
                @Override
                public void onCompleted(Void object) {
                    Log.d("TAG", "createGameInDatabase: Game created successfully");
                    Intent intent = new Intent(AddGame.this, Admin_activity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailed(Exception e) {
                    Log.e("TAG", "createGameInDatabase: User created not successfully");

                }
            });


        }
    }
}


