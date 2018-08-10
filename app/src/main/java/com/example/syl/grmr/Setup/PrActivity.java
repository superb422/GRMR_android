package com.example.syl.grmr.Setup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.syl.grmr.R;

public class PrActivity extends AppCompatActivity {
    Button pr_prev,pr_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pr);

        pr_prev = (Button)findViewById(R.id.pr_cancel);
        pr_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Printent = new Intent(PrActivity.this,Profile.class);
                Printent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Printent);
            }
        });

        pr_next = (Button)findViewById(R.id.pr_register);
        pr_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Printent = new Intent(PrActivity.this,Profile.class);
                Printent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(Printent);
            }
        });
    }
}
