package com.example.dongh.grmr.Travel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dongh.grmr.R;

public class NoTicket extends AppCompatActivity {
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_ticket);

        register=(Button)findViewById(R.id.travel_register_btn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(NoTicket.this,Travel_Schedule.class);
                startActivity(register);
            }
        });
    }
}
