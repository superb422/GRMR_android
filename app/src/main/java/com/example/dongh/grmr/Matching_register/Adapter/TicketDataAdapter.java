package com.example.dongh.grmr.Matching_register.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dongh.grmr.Matching_register.Constructor.Ticket;
import com.example.dongh.grmr.Matching_register.Travel_ticket_select;
import com.example.dongh.grmr.R;

import java.util.List;

public class TicketDataAdapter extends RecyclerView.Adapter<TicketDataAdapter.TicketViewHolder> {
    public List<Ticket> tickets;

    public class TicketViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView city,nation;

        public TicketViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            city = (TextView) view.findViewById(R.id.ticket_city);
            nation = (TextView) view.findViewById(R.id.ticket_nation);
            //Image = (ImageView)view.findViewById(R.id.User_image);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext() , Travel_ticket_select.class);
            view.getContext().startActivity(intent);
        }
    }

    public TicketDataAdapter(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_ticket, parent, false);

        return new TicketViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TicketViewHolder holder, int position) {
        Ticket Ticket = tickets.get(position);
        holder.city.setText(Ticket.getCity());
        holder.nation.setText(Ticket.getNation());
       // holder.Image.setImageResource(Ticket.getImage());
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }
}
