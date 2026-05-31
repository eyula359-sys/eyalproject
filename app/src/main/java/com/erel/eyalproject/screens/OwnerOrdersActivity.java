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
                Log.d(TAG, "Order long clicked: " + order);
            }
        });

        rvMyOrders.setAdapter(orderAdapter);

        findViewById(R.id.btnBackFromMyOrders).setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();

        DatabaseService.getInstance().getOwnerOrderList(userId,new DatabaseService.DatabaseCallback<List<Order>>() {
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
