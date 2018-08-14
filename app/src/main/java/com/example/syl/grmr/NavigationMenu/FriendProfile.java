package com.example.syl.grmr.NavigationMenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.syl.grmr.R;

public class FriendProfile extends AppCompatActivity {
    TextView reporttext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        reporttext = (TextView)findViewById(R.id.report_text);
        reporttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reportIntent = new Intent(FriendProfile.this,Report.class);
                startActivity(reportIntent);
            }
        });
    }


}
