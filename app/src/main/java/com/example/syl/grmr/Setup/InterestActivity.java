package com.example.syl.grmr.Setup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.syl.grmr.R;

public class InterestActivity extends AppCompatActivity {
    Button select_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest);

        select_btn = (Button)findViewById(R.id.selectbutton3);
        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Interestintent = new Intent(InterestActivity.this,Profile.class);
                Interestintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Interestintent);
            }
        });
    }
}
