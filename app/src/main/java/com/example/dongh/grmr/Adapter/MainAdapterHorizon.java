package com.example.dongh.grmr.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.dongh.grmr.R;

import java.util.List;

public class MainAdapterHorizon extends RecyclerView.Adapter<MainAdapterHorizon.MainViewHolder> {
    public List<MainListHorizon> main_lists;

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView image;

        public MainViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            image = (ImageView)view.findViewById(R.id.main_ad);
        }

        @Override
        public void onClick(View view) {
//            Intent intent = new Intent(view.getContext() , alarm.class);
//            view.getContext().startActivity(intent);
        }
    }

    public MainAdapterHorizon(List<MainListHorizon> main_lists) {
        this.main_lists = main_lists;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_horizon, parent, false);

        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        MainListHorizon mainListHorizon = main_lists.get(position);
        holder.image.setImageResource(mainListHorizon.getImage());
    }

    @Override
    public int getItemCount() {
        return main_lists.size();
    }
}
