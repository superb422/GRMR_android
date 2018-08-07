package com.example.syl.grmr.MainFragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.syl.grmr.Constructor.InformationOfTravel;
import com.example.syl.grmr.Login.LoginActivity;
import com.example.syl.grmr.R;
import com.example.syl.grmr.addTravel.addTravelActivity;
import com.example.syl.grmr.ui.MainRecylerAdapter;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class TravelFragment extends Fragment {

    private static RecyclerView lecyclerView; //카드 리스트 바탕

    static public boolean startedAddTravel = false; //여행등록 과정을 시작한 후,
                                                    // x버튼을 누르면 fragment의 홈화면으로 돌아가는 것을 방지하기 위함.
                                                    //MaingActivity 와 addTravleActivity 에서 사용할 것임.

    public TravelFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_travel, container, false);

        lecyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        initData();

        Button Logout= (Button) rootView.findViewById(R.id.LogoutButton);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        Log.e("로그아웃", "먹힘");
                        Intent LogoutIntent = new Intent(getActivity(), LoginActivity.class);

                        LogoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        LogoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getActivity().startActivity(LogoutIntent);
                    }
                });
            }
        });

        Button addTravelButton = (Button) rootView.findViewById(R.id.addTravelButton);
        addTravelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("여행추가", "먹힘");
                Intent addTravelIntent = new Intent(getActivity(), addTravelActivity.class);
                getActivity().startActivity(addTravelIntent);
            }
        });

        Log.e("Frag", "TravelFragment");


        return rootView;
    }



    /** 데이터 초기화*/
    private void initData(){


        /*
        *
        * 나중에 디비에 저장된 사용자가 등록해 놓았던 여행 정보 불러오는 것으로 수정하셈 여러 개 넣으면 카드 여러개 출력 될거임.
        * 지금은 일단 세개만 임의로 넣어둠.
        * 그리고 애뮬레이터로 확인 시, 마우스 말고 트랙패드로 스크롤 할 것.
        *
        * */
        String []city = {"Danang", "Osaka", "Bangkok", "Seoul"};
        String []country = {"베트남", "일본", "태국", "대한민국"};
        String []peopleNum = {"4명","2명","1명", "4명"};
        String []departure = {"2018-07-12","2018-08-13", "2018-08-14","2018-07-14"};
        String []homecoming = {"2018-07-14","2018-07-15","2018-07-16","2018-07-17"};


        List<InformationOfTravel> travelList = new ArrayList<InformationOfTravel>();


        for(int i = 0; i < city.length; i++){

            InformationOfTravel informationOfTravel = new InformationOfTravel();

            informationOfTravel.setCity(city[i]);
            informationOfTravel.setDepartureDate(departure[i]);
            informationOfTravel.setHomeComingDate(homecoming[i]);
            informationOfTravel.setCountry(country[i]);
            informationOfTravel.setPeopleNum(peopleNum[i]);

            travelList.add(informationOfTravel);
        }

        lecyclerView.setAdapter(new MainRecylerAdapter(travelList, R.layout.card_list));
        lecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        lecyclerView.setItemAnimator(new DefaultItemAnimator());

    }

}
