package com.example.syl.grmr.Main;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.syl.grmr.Constructor.RecyclerItem;
import com.example.syl.grmr.R;

import java.util.ArrayList;


public class FragmentChattingRoom extends Fragment {

    /*
    나중에 디비에서 불러올 것
     */
    private String []city = {"오사카", "육사카", "칠사카"};
    private String []departure = {"2018-07-12","2018-08-13", "2018-08-14"};
    private String []homecoming = {"2018-07-14","2018-07-15","2018-07-16"};



    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<RecyclerItem> list = new ArrayList<>();




    public static FragmentChattingRoom newInstance(){
        // Required empty public constructor
        return new FragmentChattingRoom();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_chatting_room, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.chatting_recycler);


        recyclerView.setHasFixedSize(true);
        adapter = new Matching_Chatting_Adapter(getActivity(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        Log.e("Frag", "chattingFragment");

        setData();
        return rootView;
    }


    private void setData(){
        list.clear();
// RecyclerView 에 들어갈 데이터를 추가합니다.
        for(int i =0 ; i<city.length; i++){
            list.add(new RecyclerItem(city[i],departure[i],homecoming[i]));
        }
// 데이터 추가가 완료되었으면 notifyDataSetChanged() 메서드를 호출해 데이터 변경 체크를 실행합니다.
        adapter.notifyDataSetChanged();
    }


}
