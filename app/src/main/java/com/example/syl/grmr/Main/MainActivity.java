package com.example.syl.grmr.Main;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.syl.grmr.MainFragments.HomeFragment;
import com.example.syl.grmr.MainFragments.MatchingFragment;
import com.example.syl.grmr.MainFragments.SettingFragment;
import com.example.syl.grmr.MainFragments.StoreFragment;
import com.example.syl.grmr.MainFragments.ThemeFragment;
import com.example.syl.grmr.MainFragments.TravelFragment;
import com.example.syl.grmr.NavigationMenu.Alarmsetting;
import com.example.syl.grmr.NavigationMenu.FriendMenu;
import com.example.syl.grmr.NavigationMenu.accountsetting;
import com.example.syl.grmr.R;
import com.example.syl.grmr.Setup.Profile;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private final int FragmentHome = 1;
    private final int FragmentAddTravel = 2;
    private final int FragmentMatching = 3;
    private final int FragmentTheme = 4;
    private final int FragmentStore = 5;

    ImageButton homeTab, travelTab, matchingTab, themeTab;
    TextView homeText, travelText, matchingText, themeText;
    private DrawerLayout mDrawerLayout;
    LinearLayout storelayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.various_fragment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        /* 열쇠 클릭 */
        storelayout = (LinearLayout)findViewById(R.id.keystore);
        storelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(FragmentStore);
            }
        });

        /* 네비게이션 */
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.navigation_item_profile:
                        Intent profileIntent = new Intent(MainActivity.this,Profile.class);
                        profileIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(profileIntent);
                        break;

                    case R.id.navigation_item_friend:
                        Intent friendIntent = new Intent(MainActivity.this,FriendMenu.class);
                        friendIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(friendIntent);
                        break;

                    case R.id.navigation_item_alarm:
                        Intent alarmIntent = new Intent(MainActivity.this,Alarmsetting.class);
                        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(alarmIntent);
                        break;

                    case R.id.navigation_item_store:

                        break;

                    case R.id.navigation_item_setting:
                        Intent accountIntent = new Intent(MainActivity.this,accountsetting.class);
                        accountIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(accountIntent);
                        break;
                    case R.id.navigation_item_qna:

                        break;

                }

                return true;
            }
        });

        /* 하단 탭 */
        homeTab = (ImageButton) findViewById(R.id.homeButton);
        homeText = (TextView) findViewById(R.id.homeText);

        travelTab = (ImageButton) findViewById(R.id.travelButton);
        travelText = (TextView) findViewById(R.id.travelText);

        matchingTab = (ImageButton) findViewById(R.id.matchingButton);
        matchingText = (TextView) findViewById(R.id.matchingText);

        themeTab = (ImageButton) findViewById(R.id.themeButton);
        themeText = (TextView) findViewById(R.id.themeText);


        homeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        // 탭 버튼에 대한 리스너 연결
        homeTab.setOnClickListener(this);
        travelTab.setOnClickListener(this);
        matchingTab.setOnClickListener(this);
        themeTab.setOnClickListener(this);

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

                callFragment(FragmentTheme);
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
                StoreFragment fragmentStore = new StoreFragment();
                transaction.replace(R.id.child2_fragment_container, fragmentStore);
                transaction.commit();
                break;

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}





