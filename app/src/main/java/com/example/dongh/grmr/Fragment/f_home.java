package com.example.dongh.grmr.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dongh.grmr.Adapter.MainAdapterHorizon;
import com.example.dongh.grmr.Adapter.MainAdapterVertical;
import com.example.dongh.grmr.Adapter.MainListHorizon;
import com.example.dongh.grmr.Adapter.MainListVertical;
import com.example.dongh.grmr.Matching_register.Travel_Schedule;
import com.example.dongh.grmr.R;

import java.util.ArrayList;
import java.util.List;

public class f_home extends Fragment implements View.OnClickListener{

    private MainAdapterHorizon mAdapter1;
    private MainAdapterVertical mAdapter2;
    private RecyclerView recycler_horizon,recycler_vertical;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        rootView.findViewById(R.id.travel_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity(), Travel_Schedule.class);
                startActivity(it);
            }
        });

        setHorizonDataAdapter();
        setVerticalDataAdapter();
        setupRecyclerView(rootView);

        // Inflate the layout for this fragment
        return rootView;

    }



    @Override
    public void onClick(View view) {

    }

    private void setHorizonDataAdapter() {
        List<MainListHorizon> mainListHorizons = new ArrayList<>();

        MainListHorizon ad = new MainListHorizon();
        ad.setImage(R.drawable.ic_main_ad);
        mainListHorizons.add(ad);

        MainListHorizon ad2 = new MainListHorizon();
        ad2.setImage(R.drawable.ic_main_ad);
        mainListHorizons.add(ad2);

        MainListHorizon ad3 = new MainListHorizon();
        ad3.setImage(R.drawable.ic_main_ad);
        mainListHorizons.add(ad3);

        mAdapter1 = new MainAdapterHorizon(mainListHorizons);
    }

    private void setVerticalDataAdapter() {
        List<MainListVertical> mainListVerticals = new ArrayList<>();

        MainListVertical data = new MainListVertical();
        data.setImage(R.drawable.ic_bg_gangwon);
        data.setRegion("강원도");
        data.setCountry("대한민국");
        data.setKeyword1("대관령양떼목장");
        data.setKeyword2("양떼목장");
        data.setKeyword3("강릉여행");
        data.setKeyword4("휘바핀란드");
        data.setKeyword5("안목해변");
        data.setKeyword6("경포대");
        mainListVerticals.add(data);

        MainListVertical data2 = new MainListVertical();
        data2.setImage(R.drawable.ic_bg_jeju);
        data2.setRegion("제주도");
        data2.setCountry("대한민국");
        data2.setKeyword1("제주도여행");
        data2.setKeyword2("애월");
        data2.setKeyword3("함덕");
        data2.setKeyword4("서귀포");
        data2.setKeyword5("새별오름");
        data2.setKeyword6("협재");
        mainListVerticals.add(data2);

        MainListVertical data3 = new MainListVertical();
        data3.setImage(R.drawable.ic_bg_busan);
        data3.setRegion("부산");
        data3.setCountry("대한민국");
        mainListVerticals.add(data3);

        MainListVertical data4 = new MainListVertical();
        data4.setImage(R.drawable.ic_bg_seoul);
        data4.setRegion("서울");
        data4.setCountry("대한민국");
        mainListVerticals.add(data4);

        MainListVertical data5 = new MainListVertical();
        data5.setImage(R.drawable.ic_bg_hanoi);
        data5.setRegion("하노이");
        data5.setCountry("베트남");
        mainListVerticals.add(data5);

        mAdapter2 = new MainAdapterVertical(mainListVerticals);
    }

    private void setupRecyclerView(ViewGroup view) {

        recycler_horizon = (RecyclerView) view.findViewById(R.id.recycler_main_horizon);
        recycler_vertical = (RecyclerView) view.findViewById(R.id.recycler_main_vertical);

        recycler_horizon.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recycler_horizon.setHasFixedSize(true);

        recycler_vertical.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycler_vertical.setHasFixedSize(true);

        recycler_horizon.setAdapter(mAdapter1);
        recycler_vertical.setAdapter(mAdapter2);

    }
}
