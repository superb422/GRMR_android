package com.example.dongh.grmr.Main.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dongh.grmr.R;

import java.util.List;

public class MainAdapterVertical extends RecyclerView.Adapter<MainAdapterVertical.MainViewHolder> {
    public List<MainListVertical> main_lists;

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView image;
        private TextView region,country;

        public MainViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            image = (ImageView)view.findViewById(R.id.main_bg);
            region = (TextView)view.findViewById(R.id.main_region);
            country = (TextView)view.findViewById(R.id.main_country);
        }

        @Override
        public void onClick(View view) {
//            Intent intent = new Intent(view.getContext() , alarm.class);
//            view.getContext().startActivity(intent);
        }
    }

    public MainAdapterVertical(List<MainListVertical> main_lists) {
        this.main_lists = main_lists;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_vertical, parent, false);

        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        MainListVertical mainListVertical = main_lists.get(position);
        holder.image.setImageResource(mainListVertical.getImage());
        holder.region.setText(mainListVertical.getRegion());
        holder.country.setText(mainListVertical.getCountry());
    }

    @Override
    public int getItemCount() {
        return main_lists.size();
    }
}
