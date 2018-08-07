package com.example.syl.grmr.Main;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syl.grmr.MainFragments.HomeFragment;
import com.example.syl.grmr.MainFragments.MatchingFragment;
import com.example.syl.grmr.MainFragments.SettingFragment;
import com.example.syl.grmr.MainFragments.ThemeFragment;
import com.example.syl.grmr.MainFragments.TravelFragment;
import com.example.syl.grmr.R;
import com.example.syl.grmr.Setup.Profile;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout SettingButton;

    private final int FragmentHome = 1;
    private final int FragmentAddTravel = 2;
    private final int FragmentMatching = 3;
    private final int FragmentTheme = 4;
    private final int FragmentSetting = 5;


    ImageButton homeTab, travelTab, matchingTab, themeTab, settingTab;
    TextView homeText, travelText, matchingText, themeText, settingText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.various_fragment);

        homeTab = (ImageButton) findViewById(R.id.homeButton);
        homeText = (TextView) findViewById(R.id.homeText);

        travelTab = (ImageButton) findViewById(R.id.travelButton);
        travelText = (TextView) findViewById(R.id.travelText);

        matchingTab = (ImageButton) findViewById(R.id.matchingButton);
        matchingText = (TextView) findViewById(R.id.matchingText);

        themeTab = (ImageButton) findViewById(R.id.themeButton);
        themeText = (TextView) findViewById(R.id.themeText);

        settingTab = (ImageButton) findViewById(R.id.settingButton);
        settingText = (TextView) findViewById(R.id.settingText);

        homeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        /*****************setup button*********************/

        SettingButton = (LinearLayout) findViewById(R.id.SettingLayout);
        SettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, Profile.class);
                startActivity(it);
            }
        });


        // 탭 버튼에 대한 리스너 연결
        homeTab.setOnClickListener(this);
        travelTab.setOnClickListener(this);
        matchingTab.setOnClickListener(this);
        themeTab.setOnClickListener(this);
        settingTab.setOnClickListener(this);

        // 임의로 액티비티 호출 시점에 어느 프레그먼트를 프레임레이아웃에 띄울 것인지를 정함

        if (TravelFragment.startedAddTravel == true) //여행 등록 중 x버튼을 누르면 FragmentTravel 띄움
        {

            homeTab.setImageResource(R.drawable.ic_travel_off);
            homeText.setTextColor(getResources().getColor(R.color.colorHint));

            travelTab.setImageResource(R.drawable.ic_travel_on);
            travelText.setTextColor(getResources().getColor(R.color.colorPrimary));

            matchingTab.setImageResource(R.drawable.ic_matching_off);
            matchingText.setTextColor(getResources().getColor(R.color.colorHint));

            themeTab.setImageResource(R.drawable.ic_theme_off);
            themeText.setTextColor(getResources().getColor(R.color.colorHint));

            settingTab.setImageResource(R.drawable.ic_setting_off);
            settingText.setTextColor(getResources().getColor(R.color.colorHint));

            callFragment(FragmentAddTravel);
        } else //HomeFragment 띄움
            callFragment(FragmentHome);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeButton:
                // 'homeButton' 클릭 시 'HomeFragment' 호출
                homeTab.setImageResource(R.drawable.ic_travel_on);
                homeText.setTextColor(getResources().getColor(R.color.colorPrimary));

                travelTab.setImageResource(R.drawable.ic_travel_off);
                travelText.setTextColor(getResources().getColor(R.color.colorHint));

                matchingTab.setImageResource(R.drawable.ic_matching_off);
                matchingText.setTextColor(getResources().getColor(R.color.colorHint));

                themeTab.setImageResource(R.drawable.ic_theme_off);
                themeText.setTextColor(getResources().getColor(R.color.colorHint));

                settingTab.setImageResource(R.drawable.ic_setting_off);
                settingText.setTextColor(getResources().getColor(R.color.colorHint));

                callFragment(FragmentHome);
                break;

            case R.id.travelButton:
                // 'traveleButton' 클릭 시 'TravelnFragment' 호출
                homeTab.setImageResource(R.drawable.ic_travel_off);
                homeText.setTextColor(getResources().getColor(R.color.colorHint));

                travelTab.setImageResource(R.drawable.ic_travel_on);
                travelText.setTextColor(getResources().getColor(R.color.colorPrimary));

                matchingTab.setImageResource(R.drawable.ic_matching_off);
                matchingText.setTextColor(getResources().getColor(R.color.colorHint));

                themeTab.setImageResource(R.drawable.ic_theme_off);
                themeText.setTextColor(getResources().getColor(R.color.colorHint));

                settingTab.setImageResource(R.drawable.ic_setting_off);
                settingText.setTextColor(getResources().getColor(R.color.colorHint));

                callFragment(FragmentAddTravel);
                break;

            case R.id.matchingButton:
                // 'matchingButton' 클릭 시 'MatchingFragment' 호출
                homeTab.setImageResource(R.drawable.ic_travel_off);
                homeText.setTextColor(getResources().getColor(R.color.colorHint));

                travelTab.setImageResource(R.drawable.ic_travel_off);
                travelText.setTextColor(getResources().getColor(R.color.colorHint));

                matchingTab.setImageResource(R.drawable.ic_matching_on);
                matchingText.setTextColor(getResources().getColor(R.color.colorPrimary));

                themeTab.setImageResource(R.drawable.ic_theme_off);
                themeText.setTextColor(getResources().getColor(R.color.colorHint));

                settingTab.setImageResource(R.drawable.ic_setting_off);
                settingText.setTextColor(getResources().getColor(R.color.colorHint));

                callFragment(FragmentMatching);
                break;

            case R.id.themeButton:
                // 'themeButton' 클릭 시 'ThemeFragment' 호출
                homeTab.setImageResource(R.drawable.ic_travel_off);
                homeText.setTextColor(getResources().getColor(R.color.colorHint));

                travelTab.setImageResource(R.drawable.ic_travel_off);
                travelText.setTextColor(getResources().getColor(R.color.colorHint));

                matchingTab.setImageResource(R.drawable.ic_matching_off);
                matchingText.setTextColor(getResources().getColor(R.color.colorHint));

                themeTab.setImageResource(R.drawable.ic_theme_on);
                themeText.setTextColor(getResources().getColor(R.color.colorPrimary));

                settingTab.setImageResource(R.drawable.ic_setting_off);
                settingText.setTextColor(getResources().getColor(R.color.colorHint));

                callFragment(FragmentTheme);
                break;

            case R.id.settingButton:
                // 'settingButton' 클릭 시 'SettingFragment' 호출
                homeTab.setImageResource(R.drawable.ic_travel_off);
                homeText.setTextColor(getResources().getColor(R.color.colorHint));

                travelTab.setImageResource(R.drawable.ic_travel_off);
                travelText.setTextColor(getResources().getColor(R.color.colorHint));

                matchingTab.setImageResource(R.drawable.ic_matching_off);
                matchingText.setTextColor(getResources().getColor(R.color.colorHint));

                themeTab.setImageResource(R.drawable.ic_theme_off);
                themeText.setTextColor(getResources().getColor(R.color.colorHint));

                settingTab.setImageResource(R.drawable.ic_setting_on);
                settingText.setTextColor(getResources().getColor(R.color.colorPrimary));

                callFragment(FragmentSetting);
                break;
        }
    }

    private void callFragment(int frament_no) {

        // 프래그먼트 사용을 위해
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (frament_no) {
            case 1:
                // 'MainFragment' 호출
                HomeFragment fragmentHome = new HomeFragment();
                transaction.replace(R.id.child_fragment_container, fragmentHome);
                transaction.commit();
                break;

            case 2:
                // 'TravelFragment' 호출
                TravelFragment fragmentTravel = new TravelFragment();
                transaction.replace(R.id.child_fragment_container, fragmentTravel);
                transaction.commit();
                break;

            case 3:
                // 'MatchingFragment' 호출
                // MatchingFragment fragmentMatching = new MatchingFragment.newInstance();
                transaction.replace(R.id.child_fragment_container, MatchingFragment.newInstance());
                transaction.commit();
                break;

            case 4:
                // 'ThemeFragment' 호출
                ThemeFragment fragmentTheme = new ThemeFragment();
                transaction.replace(R.id.child_fragment_container, fragmentTheme);
                transaction.commit();
                break;

            case 5:
                // 'MatchingFragment' 호출
                SettingFragment fragmentSetting = new SettingFragment();
                transaction.replace(R.id.child_fragment_container, fragmentSetting);
                transaction.commit();
                break;
        }

    }
}





