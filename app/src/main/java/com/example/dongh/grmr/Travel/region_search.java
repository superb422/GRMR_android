package com.example.dongh.grmr.Travel;

import android.app.Service;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dongh.grmr.Login.SoftKeyboard;
import com.example.dongh.grmr.R;

public class region_search extends AppCompatActivity {

    private Button search_btn;
    private EditText search_text;
    private SoftKeyboard softKeyboard;
    private LinearLayout search_layout,child_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_search);

        child_layout=findViewById(R.id.search_child_layout);
        search_btn=findViewById(R.id.travel_search_btn);
        search_text=findViewById(R.id.travel_search_text);
        search_text.setVisibility(View.INVISIBLE);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_text.setVisibility(View.VISIBLE);
                search_text.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.ic_nick_delete,0);
                search_text.setHint("여행지를 검색해주세요");
                search_btn.setVisibility(View.GONE);
                child_layout.setBackgroundColor(Color.rgb(127, 127, 127));
            }
        });

        InputMethodManager controlManager = (InputMethodManager)getSystemService(Service.INPUT_METHOD_SERVICE);
        search_layout = findViewById(R.id.search_layout);
        softKeyboard = new SoftKeyboard(search_layout, controlManager);
        softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged() {
            @Override
            public void onSoftKeyboardHide() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // mainicon.setVisibility(View.VISIBLE);
                    }
                });
            }

            @Override
            public void onSoftKeyboardShow() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        //mainicon.setVisibility(View.INVISIBLE);
                    }
                });
            }
        });
    }
}
