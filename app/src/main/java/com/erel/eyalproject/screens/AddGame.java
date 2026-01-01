package com.erel.eyalproject.screens;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.erel.eyalproject.R;
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
    }

    @Override
    public void onClick(View v) {

        String HomeTeam = text_hometeam.getText().toString();
        String AweyTeam = text_awayteam.getText().toString();
        String Date = text_date.getText().toString();
        String Hour = text_hour.getText().toString();



    }
}