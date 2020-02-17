package com.example.dongh.grmr.Matching_register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dongh.grmr.Matching_register.Adapter.Search_RecyclerAdapter;
import com.example.dongh.grmr.Matching_register.Constructor.RegionData;
import com.example.dongh.grmr.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class region_search extends AppCompatActivity {

    private Button search_btn;
    private EditText search_text;
    private RelativeLayout search_layout;
    private LinearLayout child_layout;
    private Button pick1,pick2,pick3,pick4,pick5,pick6;
    private Search_RecyclerAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_search);

        recyclerView=findViewById(R.id.recycler_region);
        search_layout=findViewById(R.id.search_layout);
        child_layout=findViewById(R.id.search_child_layout);
        search_text=findViewById(R.id.travel_search_text);
        pick1=findViewById(R.id.pick1);pick2=findViewById(R.id.pick2);pick3=findViewById(R.id.pick3);pick4=findViewById(R.id.pick4);pick5=findViewById(R.id.pick5);pick6=findViewById(R.id.pick6);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);

        //recyclerView.setVisibility(View.INVISIBLE);

        findViewById(R.id.search_backbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(region_search.this,Travel_Schedule.class);
                startActivity(it);
            }
        });


        search_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Toast.makeText(getApplicationContext(),"하하",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                int txt_length = search_text.getText().length();

                if(txt_length<=0) {
                    recyclerView.setVisibility(View.INVISIBLE);
//                    child_layout.setVisibility(View.VISIBLE);
                }
                else {
                    recyclerView.setVisibility(View.VISIBLE);
//                    child_layout.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = search_text.getText().toString()
                        .toLowerCase(Locale.getDefault());
                mAdapter.filter(text);

            }
        });

        setUserDataAdapter();

       /* InputMethodManager controlManager = (InputMethodManager)getSystemService(Service.INPUT_METHOD_SERVICE);
        search_layout = findViewById(R.id.search_layout);
        softKeyboard = new SoftKeyboard(search_layout, controlManager);
        softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged() {
            @Override
            public void onSoftKeyboardHide() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // mainicon.setVisibility(View.VISIBLE);
                        child_layout.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            public void onSoftKeyboardShow() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        //mainicon.setVisibility(View.INVISIBLE);
                        child_layout.setVisibility(View.VISIBLE);
                    }
                });
            }
        });*/
    }

    private void setUserDataAdapter() {
        List<RegionData> regionList = new ArrayList<RegionData>();
        RegionData test_region1= new RegionData("Tokyo, Japan, 일본");
        regionList.add(test_region1);

        RegionData test1= new RegionData("Tokyo Station, Tokyo, Japan, 일본");
        regionList.add(test1);

        RegionData test2= new RegionData("TokyoShinjuku-ku");
        regionList.add(test2);

        RegionData test3= new RegionData("Tokyo Dome, Bunkyo, Tokyo, Japan, 일본");
        regionList.add(test3);

        RegionData test_region2= new RegionData("Osaka-Namba Station, Japan, 일본");
        regionList.add(test_region2);

        RegionData test_region3= new RegionData("Shinjuku Station, Shinjuku, Tokyo, Japan, 일본");
        regionList.add(test_region3);

        RegionData test_region4= new RegionData("Ueno Station, Taito, Tokyo, Japan, 일본");
        regionList.add(test_region4);

        mAdapter = new Search_RecyclerAdapter(this,regionList);
        recyclerView.setAdapter(mAdapter);
    }

}
