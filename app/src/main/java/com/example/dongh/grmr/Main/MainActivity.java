package com.example.dongh.grmr.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.text.TextUtils;
import android.util.Log;

import com.example.dongh.grmr.R;
import com.example.dongh.grmr.Travel.NoTicket;
import com.example.dongh.grmr.Travel.Travel_ticket_select;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    Button test,test2,test3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseInstanceId.getInstance().getToken();
        if(FirebaseInstanceId.getInstance().getToken() != null) {
            Log.d(TAG, "token = " + FirebaseInstanceId.getInstance().getToken());
        }
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
                Intent it2 = new Intent(MainActivity.this, NoTicket.class);
                startActivity(it2);
            }
        });

        test3 = (Button)findViewById(R.id.testtest3);
        test3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it3 = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(it3);
            }
        });

    }


}
