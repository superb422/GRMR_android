package com.example.dongh.grmr.etc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dongh.grmr.R;
import com.example.dongh.grmr.etc.Adapter.AlarmAdapter;
import com.example.dongh.grmr.etc.Adapter.AlarmList;

import java.util.ArrayList;
import java.util.List;

public class alarm extends AppCompatActivity {

    private AlarmAdapter mAdapter;
    private String name,region,message_type;
    private int alarm_day,start_day,end_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        setUserDataAdapter();
        setupRecyclerView();
    }

    private void setUserDataAdapter() {
        List<AlarmList> alarmLists = new ArrayList<>();

        AlarmList alarm_data1 = new AlarmList();
        name = "최준용"; region="파리 여행"; message_type="동행 신청을 수락";
        start_day=11; end_day=12;
        alarm_data1.setString(name+"님의\n"+"'"+start_day+"~"+end_day+" "+region+"'"+message_type+"하였습니다.");

        alarm_day=3;
        alarm_data1.setDay(alarm_day+"일 전");
        alarm_data1.setImage(R.drawable.ic_messageaccept);
        alarmLists.add(alarm_data1);

        AlarmList alarm_data2 = new AlarmList();
        name = "최준용"; region="파리 여행"; message_type="동행을 신청";
        start_day=11; end_day=12;
        alarm_data2.setString(name+"님이 회원님의\n"+"'"+start_day+"~"+end_day+" "+region+"'"+message_type+"하였습니다.");

        alarm_day=4;
        alarm_data2.setDay(alarm_day+"일 전");
        alarm_data2.setImage(R.drawable.ic_messagereceive);
        alarmLists.add(alarm_data2);

        AlarmList alarm_data3 = new AlarmList();
        name = "최준용"; message_type="친구 추가 요청을 수락";
        alarm_data3.setString(name+"님의\n"+message_type+"하였습니다.");

        alarm_day=7;
        alarm_data3.setDay(alarm_day+"일 전");
        alarm_data3.setImage(R.drawable.ic_friendaccept);
        alarmLists.add(alarm_data3);

        mAdapter = new AlarmAdapter(alarmLists);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_alarm);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }
}
