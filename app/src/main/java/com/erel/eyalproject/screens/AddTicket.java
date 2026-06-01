package com.erel.eyalproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.erel.eyalproject.R;
import com.erel.eyalproject.model.Game;
import com.erel.eyalproject.model.Ticket;
import com.erel.eyalproject.model.User;
import com.erel.eyalproject.services.DatabaseService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class AddTicket extends AppCompatActivity implements View.OnClickListener {

    EditText text_Price, text_Section, text_Row, text_Seat, text_Quantity;
    Button btnAddTick;
    private DatabaseService databaseService;
    Spinner spinnerGames;

    ArrayList<Game> games = new ArrayList<>();
    ArrayAdapter<Game> gamesAdapter;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_ticket);

        spinnerGames = findViewById(R.id.spinnergames);
        databaseService = DatabaseService.getInstance();

        String userId = mAuth.getUid();

        databaseService.getUser(userId, new DatabaseService.DatabaseCallback<User>() {
            @Override
            public void onCompleted(User user) {
                currentUser = new User(user);
            }

            @Override
            public void onFailed(Exception e) {
            }
        });

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
        text_Quantity = findViewById(R.id.text_Quantity);

        btnAddTick.setOnClickListener(this);

        Button backToUser = findViewById(R.id.backToUser);
        backToUser.setOnClickListener(v -> {
            Intent intent = new Intent(AddTicket.this, UserActivity.class);
            startActivity(intent);
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
            }
        });
    }

    @Override
    public void onClick(View v) {
        Game selectedGame = (Game) spinnerGames.getSelectedItem();
        String stprice = text_Price.getText().toString().trim();
        String section = text_Section.getText().toString().trim();
        String strow = text_Row.getText().toString().trim();
        String stseat = text_Seat.getText().toString().trim();
        String stquantity = text_Quantity.getText().toString().trim();

        // ולידציה — שדות ריקים
        if (stprice.isEmpty() || section.isEmpty() || strow.isEmpty()
                || stseat.isEmpty() || stquantity.isEmpty() || selectedGame == null) {
            Toast.makeText(this, "יש למלא את כל השדות", Toast.LENGTH_SHORT).show();
            return;
        }

        // ולידציה — תאריך המשחק לא עבר
        if (selectedGame.isNotExpired()) {
            Toast.makeText(this, "לא ניתן להוסיף כרטיס למשחק שכבר עבר", Toast.LENGTH_LONG).show();
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(stquantity);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "כמות כרטיסים חייבת להיות מספר", Toast.LENGTH_SHORT).show();
            return;
        }

        // ולידציה — מספר מושבים תואם לכמות
        String[] seats = stseat.split(",");
        if (seats.length != quantity) {
            Toast.makeText(this,
                    "רשמת " + quantity + " כרטיסים אך הזנת " + seats.length + " מושבים. יש להתאים",
                    Toast.LENGTH_LONG).show();
            return;
        }

        double price = Double.parseDouble(stprice);
        String ticketId = databaseService.generateTicketId();

        Ticket newTicket = new Ticket(ticketId, selectedGame, price, section, strow, stseat, quantity, true, currentUser);

        databaseService.createNewTicket(newTicket, new DatabaseService.DatabaseCallback<Void>() {
            @Override
            public void onCompleted(Void object) {
                Log.d("TAG", "Ticket created successfully");
                Toast.makeText(AddTicket.this, "הכרטיס נוסף בהצלחה!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddTicket.this, UserActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailed(Exception e) {
                Log.e("TAG", "Ticket creation failed");
                Toast.makeText(AddTicket.this, "שגיאה בהוספת הכרטיס", Toast.LENGTH_SHORT).show();
            }
        });
    }
}