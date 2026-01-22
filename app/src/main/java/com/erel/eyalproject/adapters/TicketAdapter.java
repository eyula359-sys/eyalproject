package com.erel.eyalproject.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erel.eyalproject.R;
import com.erel.eyalproject.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketAdapter   extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    public interface OnTicketClickListener {
        void onTicketClick(Ticket ticket);
        void onLongTicketClick(Ticket ticket);
    }

    private final List<Ticket> ticketList = new ArrayList<>();
    private final TicketAdapter.OnTicketClickListener listener;

    public TicketAdapter(TicketAdapter.OnTicketClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public TicketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.one_ticket, parent, false);
        return new TicketAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.ViewHolder holder, int position) {
        Ticket ticket = ticketList.get(position);

        holder.tvTicketName.setText(ticket.getGame().getGameName()+"" );
        holder.tvTicketPrice.setText(ticket.getPrice()+"");
        holder.tvTicketSeat.setText(ticket.getSeat());

        holder.tvTicketSection.setText(ticket.getSection());
        holder.tvTicketRow.setText(ticket.getRow());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onTicketClick(ticket);
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) listener.onLongTicketClick(ticket);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public void setTicketList(List<Ticket> tickets) {
        ticketList.clear();
        ticketList.addAll(tickets);
        notifyDataSetChanged();
    }

    public void updateTicket(Ticket updatedTicket) {
        for (int i = 0; i < ticketList.size(); i++) {
            if (ticketList.get(i).getTicketId().equals(updatedTicket.getTicketId())) {
                ticketList.set(i, updatedTicket);
                notifyItemChanged(i);
                return;
            }
        }
    }

    public void removeTicketById(String ticketId) {
        for (int i = 0; i < ticketList.size(); i++) {
            if (ticketList.get(i).getTicketId().equals(ticketId)) {
                ticketList.remove(i);
                notifyItemRemoved(i);
                return;
            }
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTicketName, tvTicketSeat, tvTicketRow, tvTicketPrice, tvTicketSection;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTicketName = itemView.findViewById(R.id.tvTicketName);
            tvTicketSeat = itemView.findViewById(R.id.tvTicketSeat);
            tvTicketRow = itemView.findViewById(R.id.tvTicketRow);
            tvTicketPrice = itemView.findViewById(R.id.tvTicketPrice);
            tvTicketSection = itemView.findViewById(R.id.tvTicketSection);
        }
    }
}
