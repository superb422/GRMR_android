package com.example.dongh.grmr.etc.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dongh.grmr.R;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {
    public List<AlarmList> alarm_lists;

    public class AlarmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView string,day;
        private ImageView type;

        public AlarmViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            string = (TextView) view.findViewById(R.id.alarm_string);
            day = (TextView) view.findViewById(R.id.alarm_day);
            type = (ImageView)view.findViewById(R.id.alarm_type);
        }

        @Override
        public void onClick(View view) {
//            Intent intent = new Intent(view.getContext() , alarm.class);
//            view.getContext().startActivity(intent);
        }
    }

    public AlarmAdapter(List<AlarmList> alarm_lists) {
        this.alarm_lists = alarm_lists;
    }

    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alarm_list, parent, false);

        return new AlarmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        AlarmList alarmList = alarm_lists.get(position);
        holder.string.setText(alarmList.getString());
        holder.day.setText(alarmList.getDay());
        holder.type.setImageResource(alarmList.getImage());
    }

    @Override
    public int getItemCount() {
        return alarm_lists.size();
    }
}
