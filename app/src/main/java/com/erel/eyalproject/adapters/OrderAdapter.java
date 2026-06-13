package com.erel.eyalproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erel.eyalproject.R;
import com.erel.eyalproject.model.Order;
import com.erel.eyalproject.model.Order;
import com.erel.eyalproject.model.User;
import com.erel.eyalproject.services.DatabaseService;


import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderAdapter extends RecyclerView.Adapter<com.erel.eyalproject.adapters.OrderAdapter.ViewHolder>{
    



        public interface OnOrderClickListener {
            void onOrderClick(Order order);
            void onLongOrderClick(Order order);
        }

        private  List<Order> orderList = new ArrayList<>();
        private  com.erel.eyalproject.adapters.OrderAdapter.OnOrderClickListener listener;


    public OrderAdapter(OnOrderClickListener listener) {
        this.listener = listener;
    }


    public OrderAdapter(  List<Order> orderList,  OnOrderClickListener listener) {
        this.orderList=orderList;
        this.listener = listener;
    }




    @NonNull
        @Override
        public com.erel.eyalproject.adapters.OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.oneorder, parent, false);
            return new com.erel.eyalproject.adapters.OrderAdapter.ViewHolder(view);
        }



    @Override
        public void onBindViewHolder(@NonNull com.erel.eyalproject.adapters.OrderAdapter.ViewHolder holder, int position) {
            Order order = orderList.get(position);
            Context context = holder.itemView.getContext();


            DatabaseService databaseService= DatabaseService.getInstance();

            if(order!=null) {

                holder.tvOrderTicket.setText(order.getTicket().getGame().getGameName());
                holder.tvOrderOwner.setText("מוכר - " + order.getTicket().getUser().getFullname());
                holder.tvOrderPrice.setText("מחיר - " + order.getTicket().getPrice());
                holder.tvOrderPhone.setText("טלפון מוכר - " + order.getTicket().getUser().getPhone());
                holder.tvOrderBuyerPhone.setText("טלפון קונה - " + order.getBuyer().getPhone());
                holder.tvOrderDate.setText("תאריך הזמנה - " + order.getDate());
                holder.tvOrderStatus.setText("סטטוס - " + order.getStatus());


                holder.itemView.setOnClickListener(v -> {
                    if (listener != null) listener.onOrderClick(order);
                });

                holder.itemView.setOnLongClickListener(v -> {
                    if (listener != null) listener.onLongOrderClick(order);
                    return true;
                });


            }
        }


        @Override
        public int getItemCount() {
            return orderList.size();
        }

        public void setOrderList(List<Order> orders) {
            orderList.clear();
            orderList.addAll(orders);
            notifyDataSetChanged();
        }

        public void updateOrder(Order updatedOrder) {
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getId().equals(updatedOrder.getId())) {
                    orderList.set(i, updatedOrder);
                    notifyItemChanged(i);
                    return;
                }
            }
        }

        public void removeOrderById(String orderId) {
            for (int i = 0; i < orderList.size(); i++) {
                if (orderList.get(i).getId().equals(orderId)) {
                    orderList.remove(i);
                    notifyItemRemoved(i);
                    return;
                }
            }
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvOrderTicket, tvOrderOwner, tvOrderPhone, tvOrderBuyerPhone, tvOrderPrice, tvOrderDate, tvOrderStatus;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvOrderTicket = itemView.findViewById(R.id.tvGameTicket);
                tvOrderOwner = itemView.findViewById(R.id.tvOrderOwner);
                tvOrderPhone = itemView.findViewById(R.id.tvOrderPhone);
                tvOrderBuyerPhone = itemView.findViewById(R.id.tvOrderBuyerPhone);
                tvOrderPrice = itemView.findViewById(R.id.tvOrderPrice);
                tvOrderDate = itemView.findViewById(R.id.tvOrderDate);
                tvOrderStatus = itemView.findViewById(R.id.tvOrderStatus);




            }
        }


    }

