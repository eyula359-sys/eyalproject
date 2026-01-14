package com.erel.eyalproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.erel.eyalproject.R;
import com.erel.eyalproject.model.User;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    public interface OnUserClickListener {
        void onUserClick(User user);
        void onLongUserClick(User user);
    }

    private final List<User> userList = new ArrayList<>();
    private final OnUserClickListener listener;

    public UserAdapter(OnUserClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);

        holder.tvName.setText(user.getFname() + " " + user.getLname());
        holder.tvEmail.setText(user.getEmail());
        holder.tvPhone.setText(user.getPhone());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onUserClick(user);
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) listener.onLongUserClick(user);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setUserList(List<User> users) {
        userList.clear();
        userList.addAll(users);
        notifyDataSetChanged();
    }

    public void updateUser(User updatedUser) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(updatedUser.getId())) {
                userList.set(i, updatedUser);
                notifyItemChanged(i);
                return;
            }
        }
    }

    public void removeUserById(String userId) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId().equals(userId)) {
                userList.remove(i);
                notifyItemRemoved(i);
                return;
            }
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvEmail, tvPhone;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.etEmail);
            tvPhone = itemView.findViewById(R.id.etPhone);
        }
    }
}