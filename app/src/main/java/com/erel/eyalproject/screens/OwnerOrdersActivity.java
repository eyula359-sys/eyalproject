package com.erel.eyalproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erel.eyalproject.R;
import com.erel.eyalproject.adapters.OrderAdapter;
import com.erel.eyalproject.model.Order;
import com.erel.eyalproject.services.DatabaseService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class OwnerOrdersActivity extends AppCompatActivity {
    private static final String TAG = "MyOwnerOrdersActivity";
    private OrderAdapter orderAdapter;
    private RecyclerView rvMyOrders;

    ArrayList<Order>getMyOrders=new ArrayList<>();

    FirebaseAuth auth;
    String userId;

    DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_owner_orders);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        databaseService=DatabaseService.getInstance();
        auth=FirebaseAuth.getInstance();
        userId=auth.getUid();

        rvMyOrders = findViewById(R.id.rv_my_orders);
        rvMyOrders.setLayoutManager(new LinearLayoutManager(this));

        orderAdapter = new OrderAdapter(getMyOrders ,new OrderAdapter.OnOrderClickListener() {
            @Override
            public void onOrderClick(Order order) {
                Log.d(TAG, "Order clicked: " + order);



            }

            @Override
            public void onLongOrderClick(Order order) {
                if (order.getStatus().equals("אושר") || order.getStatus().equals("נדחה")) {
                    android.widget.Toast.makeText(OwnerOrdersActivity.this,
                            "לא ניתן לשנות הזמנה שכבר טופלה",
                            android.widget.Toast.LENGTH_SHORT).show();
                    return;
                }
                new android.app.AlertDialog.Builder(OwnerOrdersActivity.this)
                        .setTitle("טיפול בהזמנה")
                        .setMessage("מה ברצונך לעשות עם ההזמנה?")
                        .setPositiveButton("אשר", (dialog, which) -> {
                            order.setStatus("אושר");
                            order.getTicket().setIs_available(false);
                            databaseService.updateOrder(order, null);
                            databaseService.updateTicket(order.getTicket(), null);
                            orderAdapter.updateOrder(order);
                        })
                        .setNegativeButton("דחה", (dialog, which) -> {
                            order.setStatus("נדחה");
                            order.getTicket().setIs_available(true);
                            databaseService.updateOrder(order, null);
                            databaseService.updateTicket(order.getTicket(), null);
                            orderAdapter.updateOrder(order);
                        })
                        .setNeutralButton("ביטול", null)
                        .show();
            }
        });

        rvMyOrders.setAdapter(orderAdapter);

        findViewById(R.id.btnBackFromMyOrders).setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();

        databaseService.getOwnerOrderList(userId,new DatabaseService.DatabaseCallback<List<Order>>() {
            @Override
            public void onCompleted(List<Order> orders) {
                Log.d(TAG, "My orders loaded: " + orders.size());
                orderAdapter.setOrderList(orders);
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {
                Log.e(TAG, "Failed to get my orders", e);
            }
        });
    }
}
