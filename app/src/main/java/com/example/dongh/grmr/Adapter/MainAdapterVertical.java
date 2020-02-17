package com.example.dongh.grmr.Adapter;

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
        private TextView region,country,keyword1,keyword2,keyword3,keyword4,keyword5,keyword6;

        public MainViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            image = (ImageView)view.findViewById(R.id.main_bg);
            region = (TextView)view.findViewById(R.id.main_region);
            country = (TextView)view.findViewById(R.id.main_country);
            keyword1 = (TextView)view.findViewById(R.id.keyword1);
            keyword2 = (TextView)view.findViewById(R.id.keyword2);
            keyword3 = (TextView)view.findViewById(R.id.keyword3);
            keyword4 = (TextView)view.findViewById(R.id.keyword4);
            keyword5 = (TextView)view.findViewById(R.id.keyword5);
            keyword6 = (TextView)view.findViewById(R.id.keyword6);
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
        holder.keyword1.setText("#"+mainListVertical.getKeyword1());
        holder.keyword2.setText("#"+mainListVertical.getKeyword2());
        holder.keyword3.setText("#"+mainListVertical.getKeyword3());
        holder.keyword4.setText("#"+mainListVertical.getKeyword4());
        holder.keyword5.setText("#"+mainListVertical.getKeyword5());
        holder.keyword6.setText("#"+mainListVertical.getKeyword6());
    }

    @Override
    public int getItemCount() {
        return main_lists.size();
    }
}
