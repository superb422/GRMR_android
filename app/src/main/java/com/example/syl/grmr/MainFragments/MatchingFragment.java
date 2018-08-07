package com.example.syl.grmr.MainFragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.syl.grmr.R;

public class MatchingFragment extends Fragment implements View.OnClickListener{

    private final int FragmentMatchingRequirement = 1;
    private final int FragmentChattingRoom = 2;



    TextView matchingTab,chattingTab;
    View view1,view2;


    public static MatchingFragment newInstance(){
        return new MatchingFragment();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_matching, container, false);


        matchingTab = (TextView)rootView.findViewById(R.id.m_button);
        chattingTab= (TextView)rootView.findViewById(R.id.ch_button);
        view1=(View)rootView.findViewById(R.id.view1);
        view2=(View)rootView.findViewById(R.id.view2);


        // 탭 버튼에 대한 리스너 연결
        matchingTab.setOnClickListener(this);
        chattingTab.setOnClickListener(this);

        // 임의로 액티비티 호출 시점에 어느 프레그먼트를 프레임레이아웃에 띄울 것인지를 정함
        setChildFragment(com.example.syl.grmr.Main.FragmentMatchingRequirement.newInstance());






        Log.e("Frag", "MatchingFragment");

        return rootView;
    }


    @Override
    public void onClick(View view) {

        Fragment fg;
        switch (view.getId()) {
            case R.id.m_button:
                chattingTab.setTextColor(getResources().getColor(R.color.colorHint));
                matchingTab.setTextColor(getResources().getColor(R.color.colorPrimary));
                view1.setBackgroundColor(Color.parseColor("#92b6d5"));
                view2.setBackgroundColor(Color.parseColor("#ffffff"));
                fg = com.example.syl.grmr.Main.FragmentMatchingRequirement.newInstance();
                setChildFragment(fg);
                break;
            case R.id.ch_button:
                chattingTab.setTextColor(getResources().getColor(R.color.colorPrimary));
                matchingTab.setTextColor(getResources().getColor(R.color.colorHint));
                view1.setBackgroundColor(Color.parseColor("#ffffff"));
                view2.setBackgroundColor(Color.parseColor("#92b6d5"));
                fg = com.example.syl.grmr.Main.FragmentChattingRoom.newInstance();
                setChildFragment(fg);
                break;
        }
    }

    private void setChildFragment(Fragment child) {
        FragmentTransaction childFt = getChildFragmentManager().beginTransaction();


        if (!child.isAdded()) {
            childFt.replace(R.id.child_fragment_container, child);
            childFt.addToBackStack(null);
            childFt.commit();
        }
    }




}
