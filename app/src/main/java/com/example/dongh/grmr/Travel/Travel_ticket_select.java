package com.example.dongh.grmr.Travel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dongh.grmr.R;
import com.example.dongh.grmr.Travel.Adapter.TicketDataAdapter;
import com.example.dongh.grmr.Travel.Constructor.Ticket;

import java.util.ArrayList;
import java.util.List;

public class Travel_ticket_select extends AppCompatActivity {

    private TicketDataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_ticket_select);

        setUserDataAdapter();
        setupRecyclerView();
    }

    private void setUserDataAdapter() {
        List<Ticket> News_ac = new ArrayList<>();

        Ticket news_data1 = new Ticket();
        news_data1.setCity("오사카");
        news_data1.setNation("일본");
        News_ac.add(news_data1);

        Ticket news_data2 = new Ticket();
        news_data2.setCity("제주도");
        news_data2.setNation("한국");
        News_ac.add(news_data2);

        mAdapter = new TicketDataAdapter(News_ac);
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_ticket);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

}
