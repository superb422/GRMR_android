package com.example.dongh.grmr.Matching_register.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dongh.grmr.Matching_register.Constructor.RegionData;
import com.example.dongh.grmr.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class Search_RecyclerAdapter extends RecyclerView.Adapter<Search_RecyclerAdapter.ViewHolder> {
    private Context mCtx;

    private List<RegionData> items= null;
    private ArrayList<RegionData> arrayList;

    public Search_RecyclerAdapter(Context context, List<RegionData> items) {
        this.mCtx=context;
        this.items=items;
        arrayList = new ArrayList<RegionData>();
        arrayList.addAll(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list,null);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final RegionData item=items.get(position);

        holder.tv_address.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        items.clear();
        if (charText.length() == 0) {
            items.addAll(arrayList);
        } else {
            for (RegionData RegionData : arrayList) {
                String name = RegionData.getName();
                if (name.toLowerCase().contains(charText)) {
                    items.add(RegionData);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //ImageView iv_icon;
        TextView tv_address;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_address= (TextView) itemView.findViewById(R.id.region_location);
            //iv_icon = (ImageView) itemView.findViewById(R.id.search_icon);

        }


    }
}
