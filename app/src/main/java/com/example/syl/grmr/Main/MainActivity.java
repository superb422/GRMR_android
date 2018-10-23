package com.example.syl.grmr.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.syl.grmr.R;
import com.example.syl.grmr.Travel.NoTicket;
import com.example.syl.grmr.Travel.Travel_ticket_select;

public class MainActivity extends AppCompatActivity {
    Button test,test2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = (Button)findViewById(R.id.testtest);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, Travel_ticket_select.class);
                startActivity(it);
            }
        });

        test2 = (Button)findViewById(R.id.testtest2);
        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, NoTicket.class);
                startActivity(it);
            }
        });
    }


}
