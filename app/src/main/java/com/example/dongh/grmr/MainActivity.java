package com.example.dongh.grmr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.dongh.grmr.Fragment.f_chatting;
import com.example.dongh.grmr.Fragment.f_home;
import com.example.dongh.grmr.Fragment.f_matching;
import com.example.dongh.grmr.Fragment.f_matchinglist;
import com.example.dongh.grmr.Fragment.f_mypage;

import java.lang.reflect.Field;


public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private f_home menu_home = new f_home();
    private f_matchinglist menu_matchlist = new f_matchinglist();
    private f_matching menu_match = new f_matching();
    private f_chatting menu_chat = new f_chatting();
    private f_mypage menu_mypage = new f_mypage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // BottomNavigationView 메뉴를 선택할 때마다 위치가 변하지 않도록
        BottomNavigationHelper.disableShiftMode(bottomNavigationView);


        // 첫 화면 지정
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_layout, menu_home).commitAllowingStateLoss();

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.menu_home: {
                        transaction.replace(R.id.main_layout, menu_home).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.menu_matchinglist: {
                        transaction.replace(R.id.main_layout, menu_matchlist).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.menu_matching: {
                        transaction.replace(R.id.main_layout, menu_match).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.menu_chatting: {
                        transaction.replace(R.id.main_layout, menu_chat).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.menu_mypage: {
                        transaction.replace(R.id.main_layout, menu_mypage).commitAllowingStateLoss();
                        break;
                    }
                }

                return true;
            }
        });

    }

}

class BottomNavigationHelper {
    @SuppressLint("RestrictedApi")
    static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);

                item.setShiftingMode(false);
                // set once again checked value, so view will be updated

                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }
}
