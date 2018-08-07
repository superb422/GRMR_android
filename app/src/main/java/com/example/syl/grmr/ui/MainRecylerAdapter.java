package com.example.syl.grmr.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.syl.grmr.R;
import com.example.syl.grmr.Constructor.InformationOfTravel;

import java.util.List;

public class MainRecylerAdapter extends RecyclerView.Adapter<MainRecylerAdapter.ViewHolder> {

    private List<InformationOfTravel> travelList;
    private int itemLayout;

    /**
     * 생성자
     * @param items
     * @param itemLayout
     */
    public MainRecylerAdapter(List<InformationOfTravel> items, int itemLayout){
        this.travelList = items;
        this.itemLayout = itemLayout;
    }

    /**
     * 레이아웃을 만들어서 Holer에 저장
     * @param viewGroup
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout,viewGroup,false);

        return new ViewHolder(view);
    }

    /**
     * listView getView 를 대체
     * 넘겨 받은 데이터를 화면에 출력하는 역할
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        InformationOfTravel item = travelList.get(position);
        viewHolder.textCity.setText(item.getCity());
        viewHolder.textDepartureDate.setText(item.getDepartureDate());
        viewHolder.textHomeComingDate.setText(item.getHomeComingDate());
        viewHolder.textPeopleText.setText(item.getPeopleNum());
        viewHolder.textCountry.setText(item.getCountry());
        viewHolder.itemView.setTag(item);

    }

    @Override
    public int getItemCount() {
        return travelList.size();
    }

    /** 뷰 재활용을 위한 viewHolder*/
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textCity;
        public TextView textDepartureDate;
        public TextView textHomeComingDate;
        public TextView textPeopleText;
        public TextView textCountry;

        public ViewHolder(View itemView){
            super(itemView);
            textCity = (TextView) itemView.findViewById(R.id.cityText);
            textDepartureDate = (TextView) itemView.findViewById(R.id.departureText);
            textHomeComingDate = (TextView) itemView.findViewById(R.id.homeComingText);
            textPeopleText = (TextView) itemView.findViewById(R.id.numOfPeopleText);
            textCountry = (TextView) itemView.findViewById(R.id.countryText);
        }
    }
}

