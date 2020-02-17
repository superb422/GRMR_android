package com.example.dongh.grmr.Matching_register.Constructor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.dongh.grmr.MainActivity;
import com.example.dongh.grmr.Matching_register.region_search;
import com.example.dongh.grmr.R;


public class matching_check extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching_check);

        findViewById(R.id.complete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(matching_check.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        });

        findViewById(R.id.search_backbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(matching_check.this,MainActivity.class);
                startActivity(it);
                finish();
            }
        });

        findViewById(R.id.re_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(matching_check.this, region_search.class);
                startActivity(it);
                finish();
            }
        });
    }
}
